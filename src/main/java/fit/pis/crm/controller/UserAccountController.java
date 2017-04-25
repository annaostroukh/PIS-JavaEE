package fit.pis.crm.controller;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.apache.openjpa.persistence.RollbackException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fit.pis.crm.data.UserAccountDAO;
import fit.pis.crm.model.UserAccount;

@Controller
@RequestMapping(value = "/admin/users")
public class UserAccountController {
	
	@Autowired
	private UserAccountDAO userAccountDAO;
	
	private String edit = "user_edit";
	
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
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public ModelAndView newUserGet() {
		ModelAndView mod = this.getModel();
		mod.setViewName(edit);
		UserAccount newUser = new UserAccount();
		List<Role> roles = new LinkedList();
		for(Role role : Role.values()) {
			roles.add(role);
		}
		mod.addObject("roles", roles);
		mod.addObject("userAccount", newUser);
		return mod;
	}
	
	@RequestMapping(value = "new", method = RequestMethod.POST)
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
	
}
