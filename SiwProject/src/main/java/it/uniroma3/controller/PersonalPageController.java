package it.uniroma3.controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import it.uniroma3.model.PhotoModel;
import it.uniroma3.model.UserModel;
import it.uniroma3.service.PhotoService;
import it.uniroma3.service.UserService;


@Controller
public class PersonalPageController {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/personalPage", method = RequestMethod.GET)
	public ModelAndView goToPersonalPage(Authentication authentication, HttpSession session) {

		UserModel user = (UserModel) session.getAttribute("userLogged");
		
		if(user == null){
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			
			if(userDetails == null)
				return new ModelAndView("redirect:/");
			
			UserModel userDB = userService.findByUsername(userDetails.getUsername());
			
			session.setAttribute("userLogged", userDB);
			
			user = userDB;
		}		
		
		List<PhotoModel> photos = photoService.findByProprietarioID(user.getId());
		
		ModelAndView model = new ModelAndView("personalPage", "photos", photos);

		return model;
	}	
	
	@RequestMapping(value = "/deletePhoto", method = RequestMethod.GET)
	public ModelAndView deletePhoto(Authentication authentication, HttpSession session, @RequestParam("id") String id){
		
		UserModel user = (UserModel) session.getAttribute("userLogged");
		
		if(user == null){
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			
			if(userDetails == null)
				return new ModelAndView("redirect:/");
			
			UserModel userDB = userService.findByUsername(userDetails.getUsername());
			
			session.setAttribute("userLogged", userDB);
			
			user = userDB;
		}
		
		PhotoModel photo = photoService.findById(Integer.parseInt(id));
		
		if (user.getId() == photo.getProprietario().getId())
			photoService.delete(Integer.parseInt(id));
		else
			return new ModelAndView("redirect:/");
		
		List<PhotoModel> photos = photoService.findByProprietarioID(user.getId());
		
		ModelAndView model = new ModelAndView("personalPage", "photos", photos);

		return model;
		
	}
}
