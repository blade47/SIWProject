package it.uniroma3.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.uniroma3.model.LoginModel;
import it.uniroma3.model.PhotoModel;
import it.uniroma3.model.UserModel;
import it.uniroma3.service.ServiceManager;

@Controller
public class LoginController {

	@Autowired
	private ServiceManager<UserModel> userService;
	
	@Autowired
	private ServiceManager<PhotoModel> photoService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "Login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, @Valid @ModelAttribute("login") LoginModel login, BindingResult result) {	

		if (result.hasErrors())
		{
	          return new ModelAndView("Login");
	    }

		UserModel user = userService.isValidUser(login.getUsername(), login.getPassword());
		
		if(user != null){
			
			request.getSession().setAttribute("userLogged", user);
			
			List<PhotoModel> photos = photoService.findByProprietarioID(user.getId());
			
			return new ModelAndView("personalPage", "photos", photos);
		}

		return new ModelAndView("Login", "erroreDatiImmessi", "Dati non corretti");
	}
	
	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		
		session.invalidate();

		return "redirect:/";
	}
}
