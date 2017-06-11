package it.uniroma3.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import it.uniroma3.model.UserModel;
import it.uniroma3.service.UserService;


@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpSession session, @RequestParam(value = "error", required = false) boolean error) {

	  ModelAndView model = new ModelAndView();
	  
	  if (error) {
		model.addObject("error", "Dati non corretti!");
		model.setViewName("Login");
		return model;	
	  }

	  model.setViewName("Login");
	  return model;

	}
	
	@RequestMapping(value = "/successLogin", method = RequestMethod.GET)
	public ModelAndView loggedIn(Authentication authentication, HttpSession session){
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		if(userDetails == null)
			return new ModelAndView("redirect:/");
		
		UserModel user = userService.findByUsername(userDetails.getUsername());
		
		session.setAttribute("userLogged", user);
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "Login";
	}
}
