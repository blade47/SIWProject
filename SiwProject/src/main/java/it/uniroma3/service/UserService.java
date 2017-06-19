package it.uniroma3.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.model.UserModel;
import it.uniroma3.repository.UserRepository;

@Service
@Transactional
public class UserService{
	
	@Resource
	private UserRepository userRepository;
//	
//	@Autowired
//	private PasswordEncoder encoder;
	
	@Transactional
	public UserModel create(UserModel user) {
		return userRepository.save(user);
	}
	
	@Transactional
	public UserModel findById(int id) {
		return userRepository.findOne(id);
	}
	
	@Transactional
	public UserModel findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
//	@Transactional
//	public String Encode(String password){
//		return encoder.encode(password);
//	}

	@Transactional
	public void delete(int id){
		userRepository.delete(id);
	}

	@Transactional
	public void updateAvatar(UserModel user, String avatar){
		UserModel userDB = userRepository.findOne(user.getId());
		userDB.setAvatar(avatar);
	}
	
	@Transactional
	public List<UserModel> findAll() {
		return (List<UserModel>) userRepository.findAll();
	}

	@Transactional
	public UserModel isValidUser(String username, String password){
		UserModel user = userRepository.findByUsername(username);
		if(user != null)
			if(user.getPassword().equals(password))
				return user;
		return null;
	}
	
	@Transactional
	public boolean userExist(String username, String email){
		UserModel user = userRepository.findByUsername(username);
		if(user != null)
			return true;
		UserModel mail = userRepository.findByEmail(email);
		if(mail != null)
			return true;
		return false;
	}

	@Transactional
	public List<UserModel> findByProprietarioID(int id) {
		return null;
	}
	
	@Transactional
	public UserModel update(UserModel user) {
		UserModel userCreated = user;
		return userRepository.save(userCreated);
	}

	@Transactional
	public UserModel findByUsername(String username) {
		return userRepository.findByUsername(username);
	}	

}