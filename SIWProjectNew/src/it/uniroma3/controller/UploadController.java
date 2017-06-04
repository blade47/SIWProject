package it.uniroma3.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import it.uniroma3.model.PhotoModel;
import it.uniroma3.model.UserModel;
import it.uniroma3.service.ServiceManager;

@Controller
public class UploadController{

	@Autowired
	private ServiceManager<PhotoModel> photoService;

	@RequestMapping(value = "/uploadPhoto", method = RequestMethod.GET)
	public String uploadFile(){
		return "addPhoto";
	}

	@RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
	public ModelAndView uploadFileHandler(
			@RequestParam("file") MultipartFile file, 
			@RequestParam("descrizione") String descrizione, 
			@RequestParam("id") String id,
			HttpSession session){

		UserModel user = (UserModel) session.getAttribute("userLogged");
		if(user == null)
			return new ModelAndView("redirect:/");

		if (descrizione.length() > 30)
			return new ModelAndView("addPhoto", "errore", "Descrizione troppo lunga, inserisci massimo 30 caratteri");

		if (descrizione.contains("<") || descrizione.contains("?") || descrizione.contains(">"))
			return new ModelAndView("addPhoto", "errore", "Caratteri non validi");

		if (!file.isEmpty()) 
		{
			try 
			{
				byte[] bytes = file.getBytes();

				ServletContext context = session.getServletContext();  

				String path = context.getRealPath("/WEB-INF/resources/books/");  
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

					PhotoModel photo = new PhotoModel();
					photo.setDescrizione(descrizione);
					photo.setLink(filename);

					photo.setProprietario(user);

					photoService.create(photo);

					return new ModelAndView("successPhoto");

				}
				else
				{
					throw new Exception();
				}
			}
			catch (Exception e) 
			{
				return new ModelAndView("addPhoto", "errore", "Errore nell'inserimento della foto, formato non valido");
			}
		} 
		else 
		{
			return new ModelAndView("addPhoto", "errorFile", "File inserito vuoto");
		}
	}




}