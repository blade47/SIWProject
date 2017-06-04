package it.uniroma3.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
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
public class AuthorController {
	
	@Autowired
	private ServiceManager<PhotoModel> photoService;
	
	@Autowired
	private ServiceManager<UserModel> userService;
	
	@RequestMapping(value = "/authorPage", method = RequestMethod.GET)
	public ModelAndView authorPage(@RequestParam("id") String id) {
		
		ModelAndView model = null;
		
		List<PhotoModel> photos = photoService.findByProprietarioID(Integer.parseInt(id));
		UserModel author = userService.findById(Integer.parseInt(id));
		
		model = new ModelAndView("authorPage", "photos", photos);
		model.addObject("author", author);
			
		return model;
	}	
	
	@RequestMapping(value = "/votePhoto", method = RequestMethod.GET)
	public ModelAndView votaFoto(HttpServletRequest request, HttpSession session, @RequestParam("photoID") String idPhoto) {
		
		UserModel user = (UserModel) session.getAttribute("userLogged");
		
		if(user == null)
			return new ModelAndView("redirect:/");
		
		ModelAndView model = null;	
		
		PhotoModel photo = photoService.findById(Integer.parseInt(idPhoto));
		
		user.getFotoVotate().add(photo);
		
	    String referer = request.getHeader("Referer");
		
		model = new ModelAndView("redirect:"+ referer, "author", photo.getProprietario());

		return model;
	}	
	
	@RequestMapping(value = "/removeVotePhoto", method = RequestMethod.GET)
	public ModelAndView togliVotoFoto(HttpServletRequest request, HttpSession session, @RequestParam("photoID") String idPhoto) {
		
		UserModel user = (UserModel) session.getAttribute("userLogged");
		
		if(user == null)
			return new ModelAndView("redirect:/");
		
		ModelAndView model = null;
		
		PhotoModel photo = photoService.findById(Integer.parseInt(idPhoto));

		user.getFotoVotate().remove(photo);
		
	    String referer = request.getHeader("Referer");
		
		model = new ModelAndView("redirect:"+ referer, "author", photo.getProprietario());

		return model;
	}	
}