package it.uniroma3.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private PasswordEncoder encoder;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RuoloService ruoloService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewRegistration() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", new UserModel()); 
		model.setViewName("Registration");
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processRegistration(@Valid @ModelAttribute("user") UserModel user, BindingResult result) {
		
		if (result.hasErrors())
			return new ModelAndView("Registration", "errore", "Errore nell'inserimento");
		
		if(user.getPassword().length()<4)
			return new ModelAndView("Registration", "errorPassword", "La password deve contenere almeno 4 caratteri");
		
		if(!user.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$"))
			return new ModelAndView("Registration", "errorPassword","La password deve contenere una lettera maiuscola, una minuscola e un numero");

		if(userService.userExist(user.getUsername(), user.getEmail()))
			return new ModelAndView("Registration", "errore", "Utente o Email già registrata");
		
		RuoloModel ruolo = ruoloService.findById(1);
		
		user.setRuolo(ruolo);
		
		String password = encoder.encode(user.getPassword());
		
		user.setPassword(password);
		
		userService.create(user);	

		return new ModelAndView("RegistrationSuccess", "user", user);
	}
}
