package it.uniroma3.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import it.uniroma3.model.UserModel;
import it.uniroma3.service.UserService;

@Controller
public class avatarController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/uploadAvatar", method = RequestMethod.GET)
	public String uploadAvatar(){
		return "uploadAvatar";
	}

	@RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
	public ModelAndView uploadAvatarHandler(
			@RequestParam("file") MultipartFile file, 
			@RequestParam("id") String id,
			HttpSession session,
			Authentication authentication)
	{
		
		UserModel user = (UserModel) session.getAttribute("userLogged");
	
		if(user == null){
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			
			if(userDetails == null)
				return new ModelAndView("redirect:/");
			
			UserModel userDB = userService.findByUsername(userDetails.getUsername());
			
			session.setAttribute("userLogged", userDB);
			
			user = userDB;
		}		

		if (!file.isEmpty()) 
		{
			try 
			{
				byte[] bytes = file.getBytes();

				ServletContext context = session.getServletContext();  

				String path = context.getRealPath("/WEB-INF/resources/images/");  
				String filename = file.getOriginalFilename();

				String contentType = file.getContentType();

				if (contentType.equals("image/pjpeg") || 
						contentType.equals("image/jpeg") || 
						contentType.equals("image/png") || 
						contentType.equals("image/gif") || 
						contentType.equals("image/bmp") || 
						contentType.equals("image/x-png")) 
				{
					if(filename.length() > 20)
						throw new Exception();

					File dir = new File(path+"/"+id);
					if (!dir.exists())
						dir.mkdir();

					BufferedOutputStream stream =
							new BufferedOutputStream(new FileOutputStream(  
									new File(path+"/"+id + File.separator + filename)));

					stream.write(bytes);  

					stream.flush();  
					stream.close();  				

					user.setAvatar(filename);
					userService.update(user);
					
					return new ModelAndView("successAvatar");
				}
				else
				{
					throw new Exception();
				}

			} 
			catch (Exception e) 
			{
				return new ModelAndView("uploadAvatar", "erroreAvatar", e.toString());
			}
		} 
		else 
		{
			return new ModelAndView("uploadAvatar", "errorFile", "File inserito vuoto");
		}
	}


}
