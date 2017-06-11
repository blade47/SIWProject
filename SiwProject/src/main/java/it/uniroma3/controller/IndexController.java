package it.uniroma3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import it.uniroma3.model.PhotoModel;
import it.uniroma3.service.PhotoService;

@Controller
public class IndexController {

	@Autowired
	private PhotoService photoService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = null;
		
		List<PhotoModel> photos = photoService.findAll();
		
		model = new ModelAndView("index", "photos", photos);
		
		return model;
	}	
}
