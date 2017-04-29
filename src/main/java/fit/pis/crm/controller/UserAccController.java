package fit.pis.crm.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.RollbackException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fit.pis.crm.data.UserAccDAO;
import fit.pis.crm.model.UserAcc;

@Controller
public class UserAccController {
	
	@Autowired
	private UserAccDAO userAccountDAO;
	
	private String edit = "user_edit";
	private String admin = "admin";
	
	public enum Role {
		ROLE_ADMIN,
		ROLE_MANAGER,
		ROLE_SUPERVISOR
	}
	
	private Map<Role,String> getRoles() {
		Map<Role,String> role = new LinkedHashMap<Role,String>();
		role.put(Role.ROLE_ADMIN, "Administrator");
		role.put(Role.ROLE_MANAGER,"Manager");
		role.put(Role.ROLE_SUPERVISOR,"Supervisor");
		
		return role;	
	}
	
	public UserAcc getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName(); // get logged in user name
		return userAccountDAO.findByEmail(email);
	}
	
	public ModelAndView getModel() {
		ModelAndView model = new ModelAndView();
		model.addObject("currentUser", this.getCurrentUser());
		return model;
	}
	
	@RequestMapping(value = "admin/users", method = RequestMethod.GET)
	public ModelAndView showAllUsers() {
 		ModelAndView mod = this.getModel();
 		mod.addObject("users", userAccountDAO.findAllOrderedByUserName());
 		mod.setViewName(admin);
 		return mod;
	}
	
	@RequestMapping(value = "admin/users/new", method = RequestMethod.GET)
	public ModelAndView newUserGet() {
		ModelAndView mod = this.getModel();
		mod.setViewName(edit);
		UserAcc newUser = new UserAcc();
		mod.addObject("role", getRoles());
		mod.addObject("userAccount", newUser);
		return mod;
	}
	
	@RequestMapping(value = "admin/users/new", method = RequestMethod.POST)
	public ModelAndView newUserPost(@Valid @ModelAttribute("userAccount") UserAcc userAccount, BindingResult result) {
		ModelAndView mod = this.getModel();
		if( !userAccount.getPassword().equals(userAccount.getConfirmPassword())) {
			mod.addObject("error", "Passwords don't match");
		} else if (!result.hasErrors()) {
			try {
				userAccountDAO.register(userAccount);
				mod.setViewName("redirect:/admin/users");
				return mod;
			} catch (JpaSystemException e) {
				e.printStackTrace();
				mod.addObject("error", e.getCause().getCause());
				mod.setViewName(edit);
				return mod;
			}
		}
		mod.setViewName(edit);
		return mod;
	}
	
	@RequestMapping(value = "admin/users/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long id) {
		UserAcc userAccout = userAccountDAO.findById(id);
		ModelAndView mod = this.getModel();
		mod.setViewName(edit);
		mod.addObject("role", getRoles());
		mod.addObject("userAccount", userAccout);
		return mod;
	}

	@RequestMapping(value = "admin/users/edit/{id}", method = RequestMethod.POST)
	public ModelAndView updateUserAccount(@Valid @ModelAttribute("user") UserAcc userAccout, BindingResult result) {
		ModelAndView mod = this.getModel();
		if (!result.hasErrors()) {
			try {
				userAccountDAO.update(userAccout);
				mod.setViewName("redirect:/admin/users");
				return mod;
			} catch (UnexpectedRollbackException e) {
				mod.addObject("error", e.getCause().getCause());
				mod.setViewName(edit);
				return mod;
			}
		} else {
			mod.setViewName(edit);
			return mod;
		}
	}
	
	@RequestMapping(value = "admin/users/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView mod = this.getModel();
		userAccountDAO.deleteById(id);
		mod.setViewName("redirect:/admin/users");
		return mod;
	}
	
	@RequestMapping(value ={"admin/profile", "manager/profile", "supervisor/profile"}, method = RequestMethod.GET)
	public String displayCurrentUserProfile(Model mod) {
		UserAcc useraccount = getCurrentUser();
		mod.addAttribute("userProfile", useraccount);
		mod.addAttribute("role", getRoles());
		return "profile";
	}

	@RequestMapping(value = {"admin/profile", "manager/profile", "supervisor/profile"}, method = RequestMethod.POST)
	public String updateProfile(@Valid @ModelAttribute("userProfile") UserAcc userAccount, BindingResult result, Model mod) {
		if( !userAccount.getPassword().equals(userAccount.getConfirmPassword())) {
			mod.addAttribute("error", "Passwords don't match");
		} else if (!result.hasErrors()) {
			try {
				userAccountDAO.update(userAccount);
				mod.addAttribute("message", "Profile updated");
				return "profile";
			} catch (UnexpectedRollbackException e) {
				mod.addAttribute("error", e.getCause().getCause());
				return "profile";
			}
		} 

		return "profile";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
