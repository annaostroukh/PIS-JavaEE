package fit.pis.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fit.pis.crm.data.ClientDAO;
import fit.pis.crm.data.MeetingDAO;
import fit.pis.crm.data.UserAccDAO;
import fit.pis.crm.model.Brand;
import fit.pis.crm.model.Car;
import fit.pis.crm.model.Client;
import fit.pis.crm.model.Meeting;
import fit.pis.crm.model.UserAcc;

@Controller
@RequestMapping(value = "manager")
public class MeetingController {
	@Autowired
	private UserAccDAO userAccountDAO;
	
	@Autowired
	private MeetingDAO meetingDAO;
	
	@Autowired
	private ClientDAO clientDAO;
	
	private String home = "meetings";
	private String edit = "meeting_new";
	
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
	
	@RequestMapping(value = "meetings/new", method = RequestMethod.GET)
	public ModelAndView newMeetingGet() {
		ModelAndView mod = this.getModel();
		// mod.addObject("clients", clientDAO.findAllForManager(this.getCurrentUser()));
		mod.addObject("clients", getClients());
		mod.setViewName(edit);
		Meeting newMeeting = new Meeting ();
		mod.addObject("meeting", newMeeting);
		
		return mod;
	}
	@RequestMapping(value = "meetings/new", method = RequestMethod.POST)
	public ModelAndView newMeetingPost(@Valid @ModelAttribute("meeting") Meeting meeting, BindingResult result) {
		ModelAndView mod = this.getModel();
		if (!result.hasErrors()) {
			try {
				meetingDAO.register(meeting);
				mod.setViewName("redirect:/manager/meetings");
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
	
	@RequestMapping(value = "meetings/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView mod = this.getModel();
		mod.setViewName(edit);
		Meeting meeting = meetingDAO.findById(id);
		mod.addObject("meeting", meeting);
		return mod;
	}

	@RequestMapping(value = "meetings/edit/{id}", method = RequestMethod.POST)
	public ModelAndView updateMeeting(@Valid @ModelAttribute("meeting") Meeting meeting, BindingResult result) {
		ModelAndView mod = this.getModel();
		if (!result.hasErrors()) {
			try {
				meetingDAO.update(meeting);
				mod.setViewName("redirect:/manager/meetings");
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
	
	private Map<Long,String> getClients() {
		Map<Long,String> map_brands = new LinkedHashMap<Long,String>();
		List<Client> brands = clientDAO.findAllOrderedByName();
		for (int i = 0; i < brands.size(); i++) {
			map_brands.put(brands.get(i).getId(), brands.get(i).getName());
		}
		
		return map_brands;
	}
	
	@ModelAttribute("clients")
	public Map<Long,String> registerModels() {
	    return getClients();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
