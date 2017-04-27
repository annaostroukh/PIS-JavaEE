package fit.pis.crm.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import fit.pis.crm.data.BrandDAO;
import fit.pis.crm.data.CarDAO;
import fit.pis.crm.data.UserAccDAO;
import fit.pis.crm.model.Brand;
import fit.pis.crm.model.Car;
import fit.pis.crm.model.UserAcc;

@Controller
@RequestMapping(value = "admin/cars")
public class CarController {
	
	@Autowired
	private CarDAO carDAO;
	
	@Autowired
	private UserAccDAO userAccountDAO;
	
	@Autowired
	private BrandDAO brandDAO;
	
	private String cars = "cars";
	private String newCarStep1 = "cars_new_st1";
	private String edit = "car_edit";
	
	private Map<Long,String> getBrands() {
		Map<Long,String> map_brands = new LinkedHashMap<Long,String>();
		List<Brand> brands = brandDAO.findAllOrderedByName();
		for (int i = 0; i < brands.size(); i++) {
			map_brands.put(brands.get(i).getId(), brands.get(i).getName());
		}
		
		return map_brands;
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
	
	@RequestMapping(method = RequestMethod.GET)
	public String showAllCars(Model mod) {
		//ModelAndView mod = this.getModel();
		mod.addAttribute("cars", carDAO.findAllOrderedByBrand());
		//mod.addObject("cars", carDAO.findAllOrderedByBrand());
		//mod.setViewName(cars);
 		return "cars";
	}
	
	@RequestMapping(value = "add/step1", method = RequestMethod.GET)
	public ModelAndView newCarGet() {
		ModelAndView mod = this.getModel();
		mod.setViewName(newCarStep1);
		//Car newCar = new Car ();
		Brand newBrand = new Brand();
		//mod.addObject("brands", getBrands());
		mod.addObject("brand", newBrand);
		
		//mod.addObject("car", newCar);
		return mod;
	}
	
	@RequestMapping(value = "add/step1", method = RequestMethod.POST)
	public ModelAndView newCarPost(@Valid @ModelAttribute("brand") Brand brand, BindingResult result) {
		ModelAndView mod = this.getModel();
		if (!result.hasErrors()) {
			try {
				brandDAO.register(brand);
				//carDAO.register(car);
				mod.setViewName("redirect:/cars");
				return mod;
			} catch (UnexpectedRollbackException e) {
				mod.addObject("error", e.getCause().getCause());
				mod.setViewName("add/step1");
				return mod;
			}
		} else {
			mod.setViewName(newCarStep1);
			return mod;
		}
	}
	
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long id) {
		Car car = carDAO.findById(id);
		ModelAndView mod = this.getModel();
		mod.setViewName(edit);
		mod.addObject("brand", getBrands());
		mod.addObject("car", car);
		return mod;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView updateUserAccount(@Valid @ModelAttribute("car") Car car, BindingResult result) {
		ModelAndView mod = this.getModel();
		if (!result.hasErrors()) {
			try {
				carDAO.update(car);
				mod.setViewName("redirect:/admin/cars");
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
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView mod = this.getModel();
		carDAO.deleteById(id);
		mod.setViewName("redirect:/admin/cars");
		return mod;
	}

}
