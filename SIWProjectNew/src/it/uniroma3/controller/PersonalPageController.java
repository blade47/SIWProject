package it.uniroma3.controller;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.uniroma3.model.PhotoModel;
import it.uniroma3.model.UserModel;
import it.uniroma3.service.ServiceManager;


@Controller
public class PersonalPageController {
	
	@Autowired
	private ServiceManager<PhotoModel> photoService;
	
	@RequestMapping(value = "/personalPage", method = RequestMethod.GET)
	public ModelAndView goToPersonalPage(HttpSession session) {

		UserModel user = (UserModel) session.getAttribute("userLogged");
		
		if(user == null)
			return new ModelAndView("redirect:/");
		
		List<PhotoModel> photos = photoService.findByProprietarioID(user.getId());
		
		ModelAndView model = new ModelAndView("personalPage", "photos", photos);

		return model;
	}	
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deletePhoto(HttpSession session, @RequestParam("id") String id){
		
		if(session.getAttribute("userLogged") == null)
			return new ModelAndView("index");
		
		photoService.delete(Integer.parseInt(id));
		
		UserModel user = (UserModel) session.getAttribute("userLogged");
		
		List<PhotoModel> photos = photoService.findByProprietarioID(user.getId());
		
		ModelAndView model = new ModelAndView("personalPage", "photos", photos);

		return model;
		
	}
}
