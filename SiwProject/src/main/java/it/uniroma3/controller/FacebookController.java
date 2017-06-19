package it.uniroma3.controller;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Random;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.model.RuoloModel;
import it.uniroma3.model.UserModel;
import it.uniroma3.service.RuoloService;
import it.uniroma3.service.UserService;

@Controller
@RequestMapping("/connect/fb")
public class FacebookController {

	@Autowired
	private UserService userService;

	@Autowired
	private RuoloService ruoloService;

	@Autowired
	private PasswordEncoder encoder;
	
	private ConnectionRepository connectionRepository;

	@Inject
	public FacebookController(ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}
	
	@GetMapping
	public String helloFacebook(Model model, HttpSession session) {
		
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return "redirect:/connect/facebook";
		}
		
		Facebook facebook = connectionRepository.findPrimaryConnection(Facebook.class).getApi();

		String [] fields = { "id", "email", "link", "first_name", "last_name" };
		User userProfile = facebook.fetchObject("me", User.class, fields);
		
		UserModel user = userService.findByEmail(userProfile.getEmail());

		if(user == null){

			user = userService.findByEmail(userProfile.getFirstName()+"."+userProfile.getLastName()+"@facebook.it");

			if(user == null){

				user = new UserModel();

				RuoloModel ruolo = ruoloService.findById(1);

				user.setRuolo(ruolo);
				user.setUsername(userProfile.getFirstName()+"_"+userProfile.getLastName());
				user.setFacebook(userProfile.getLink());
				
				String email = userProfile.getEmail();

				if(email == null)
					email = userProfile.getFirstName()+"."+userProfile.getLastName()+"@facebook.it";

				user.setEmail(email);

				String password = getRandomPassword(8);

				user.setPassword(encoder.encode(password));

				userService.create(user);
			}
		}

		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(auth);

		session.setAttribute("userLogged", user);

		connectionRepository.removeConnection(connectionRepository.findPrimaryConnection(Facebook.class).getKey());
		
		return "redirect:/";
	}

	private String getRandomPassword(int length) {
		final String CHARACTER_SET = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
		Random rnd = new Random();

		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < length; i++){
			builder.append(CHARACTER_SET.charAt(rnd.nextInt(CHARACTER_SET.length())));
		}
		return builder.toString();
	}

	private Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority grantedAuthority = new GrantedAuthority() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public String getAuthority() {
				return "User";
			}
		}; 
		grantedAuthorities.add(grantedAuthority);
		return grantedAuthorities;
	}

}