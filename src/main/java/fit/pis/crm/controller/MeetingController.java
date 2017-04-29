package fit.pis.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fit.pis.crm.data.MeetingDAO;
import fit.pis.crm.data.UserAccDAO;
import fit.pis.crm.model.Meeting;
import fit.pis.crm.model.UserAcc;

@Controller
@RequestMapping(value = "manager")
public class MeetingController {
	@Autowired
	private UserAccDAO userAccountDAO;
	
	@Autowired
	private MeetingDAO meetingDAO;
	
	private String home = "meetings";
	
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
	
	@RequestMapping(value = "meetings", method = RequestMethod.GET)
	public ModelAndView showAllMeetings() {
 		ModelAndView mod = this.getModel();
 		mod.addObject("meetings", meetingDAO.findAllOrderedByDate(this.getCurrentUser()));
 		mod.setViewName(home);
 		return mod;
	}
	
	@RequestMapping(value = "meetings/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView mod = this.getModel();
		userAccountDAO.deleteById(id);
		mod.setViewName("redirect:/manager/meetings");
		return mod;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
