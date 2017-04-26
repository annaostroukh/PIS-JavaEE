package fit.pis.crm.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.RollbackException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fit.pis.crm.data.UserAccountDAO;
import fit.pis.crm.model.UserAccount;

@Controller
public class UserAccountController {
	
	@Autowired
	private UserAccountDAO userAccountDAO;
	
	private String edit = "user_edit";
	private String admin = "admin";
	
	public enum Role {
		ROLE_ADMIN,
		ROLE_MANAGER,
		ROLE_SUPERVISOR
	}
	
	public UserAccount getCurrentUser() {
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
		UserAccount newUser = new UserAccount();
		Map<Role,String> role = new LinkedHashMap<Role,String>();
		role.put(Role.ROLE_ADMIN, "Administrator");
		role.put(Role.ROLE_MANAGER,"Manager");
		role.put(Role.ROLE_SUPERVISOR,"Supervisor");
		mod.addObject("role", role);
		mod.addObject("userAccount", newUser);
		return mod;
	}
	
	@RequestMapping(value = "admin/users/new", method = RequestMethod.POST)
	public ModelAndView newUserPost(@Valid @ModelAttribute("userAccount") UserAccount userAccount, BindingResult result) {
		ModelAndView mod = this.getModel();
		if( !userAccount.getPassword().equals(userAccount.getConfirmPassword())) {
			mod.addObject("error", "Passwords don't match");
		} else if (!result.hasErrors()) {
			try {
				userAccountDAO.register(userAccount);
				mod.setViewName("redirect:/admin");
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
		UserAccount userAccout = userAccountDAO.findById(id);
		ModelAndView mod = this.getModel();
		mod.setViewName(edit);
		mod.addObject("user", userAccout);
		return mod;
	}

	@RequestMapping(value = "admin/users/edit/{id}", method = RequestMethod.POST)
	public ModelAndView updateUserAccount(@Valid @ModelAttribute("user") UserAccount userAccout, BindingResult result) {
		ModelAndView mod = this.getModel();
		if (!result.hasErrors()) {
			try {
				userAccountDAO.update(userAccout);
				mod.setViewName("redirect:/admin");
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
	
	@RequestMapping(value = "admin/users/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView mod = this.getModel();
		userAccountDAO.deleteById(id);
		mod.setViewName("redirect:/admin");
		return mod;
	}
	
	@RequestMapping(value ={"admin/profile", "manager/profile", "supervisor/profile"}, method = RequestMethod.GET)
	public String displayCurrentUserAccount(Model mod) {
		UserAccount useraccount = getCurrentUser();
		Map<Role,String> role = new LinkedHashMap<Role,String>();
		role.put(Role.ROLE_ADMIN, "Administrator");
		role.put(Role.ROLE_MANAGER,"Manager");
		role.put(Role.ROLE_SUPERVISOR,"Supervisor");
		mod.addAttribute("userProfile", useraccount);
		mod.addAttribute("role", role);
		return "profile";
	}

	@RequestMapping(value = {"admin/profile", "manager/profile", "supervisor/profile"}, method = RequestMethod.POST)
	public String updateProfile(@Valid @ModelAttribute("userProfile") UserAccount userAccount, BindingResult result, Model mod) {
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
	
}
