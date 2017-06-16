package it.uniroma3.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.uniroma3.model.UserModel;
import it.uniroma3.service.UserService;

@Component
@Controller
public class MailController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public ModelAndView sendView(@RequestParam("to") String to){
		return new ModelAndView("sendMail", "to", to);
	}
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public ModelAndView send(Authentication authentication, HttpSession session, @RequestParam("to") String to, @RequestParam("message") String message) {

        UserModel user = (UserModel) session.getAttribute("userLogged");
    	
		if(user == null){
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			
			if(userDetails == null)
				return new ModelAndView("redirect:/");
			
			UserModel userDB = userService.findByUsername(userDetails.getUsername());
			
			session.setAttribute("userLogged", userDB);
			
			user = userDB;
		}		
		
		if(message.equals("") || message == null)
			return new ModelAndView("sendMail", "error", "Campo vuoto");
		
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        SimpleMailMessage ms = new SimpleMailMessage();
        ms.setFrom(user.getEmail());
        ms.setTo(to);
        ms.setText(message);
        javaMailSender.send(ms);
        
        return new ModelAndView("successEmail");
		
    }

}
