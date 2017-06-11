package it.uniroma3.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import it.uniroma3.model.RuoloModel;
import it.uniroma3.model.UserModel;
import it.uniroma3.service.RuoloService;
import it.uniroma3.service.UserService;

@Controller
@RequestMapping(value = "/registra")
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RuoloService ruoloService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewRegistration() {
		return "Registration";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processRegistration(@Valid @ModelAttribute("user") UserModel user, BindingResult result) {
		
		if (result.hasErrors())
			return new ModelAndView("Registration");

		if(userService.userExist(user.getUsername(), user.getEmail()))
			return new ModelAndView("Registration", "errore", "Utente o Email già registrata");
		
		RuoloModel ruolo = ruoloService.findById(1);
		
		user.setRuolo(ruolo);
		
		userService.create(user);
		
		

		return new ModelAndView("RegistrationSuccess", "user", user);
	}
}