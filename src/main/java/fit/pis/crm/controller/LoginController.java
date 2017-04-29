package fit.pis.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import fit.pis.crm.data.UserAccDAO;
import fit.pis.crm.model.UserAcc;

@Controller
public class LoginController {

	@Autowired
	private UserAccDAO userAccountDAO;
	
	public UserAcc getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		return userAccountDAO.findByEmail(email);
	}
	
	@RequestMapping(value = {"", "/", "login"}, method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = "error",required = false) String error,
	@RequestParam(value = "logout",	required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials or disabled account.");
		}
		if (logout != null) {
			model.addObject("message", "Logged out from Elite Motors successfully.");
		}
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	
	@RequestMapping(value = "403", method = RequestMethod.GET)
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		model.addObject("msg", "You do not have permission to access this page!");
		model.setViewName("403");
		return model;
	}


	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/admin/users");
		return model;
	}
	
	@RequestMapping(value = "manager", method = RequestMethod.GET)
	public ModelAndView managerPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/manager/meetings");
		return model;
	}
	
	@RequestMapping(value = "supervisor", method = RequestMethod.GET)
	public ModelAndView supervisorPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/supervisor/dashboard");
		return model;
	}
}
