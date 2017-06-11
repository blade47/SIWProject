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
    public String send(Authentication authentication, HttpSession session, @RequestParam("to") String to, @RequestParam("subject") String subject) {

        UserModel user = (UserModel) session.getAttribute("userLogged");
    	
		if(user == null){
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			
			if(userDetails == null)
				return "redirect:/";
			
			UserModel userDB = userService.findByUsername(userDetails.getUsername());
			
			session.setAttribute("userLogged", userDB);
			
			user = userDB;
		}		
		
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(user.getEmail());
        message.setTo(to);
        message.setSubject(subject);
        javaMailSender.send(message);
        
        return "successEmail";
		
    }

}
