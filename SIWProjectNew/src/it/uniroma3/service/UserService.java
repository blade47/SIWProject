package it.uniroma3.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.model.UserModel;
import it.uniroma3.repository.UserRepository;

@Service
public class UserService implements ServiceManager<UserModel> {
	
	@Resource
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserModel create(UserModel user) {
		UserModel userCreated = user;
		return userRepository.save(userCreated);
	}
	
	@Override
	@Transactional
	public UserModel findById(int id) {
		return userRepository.findOne(id);
	}

	@Override
	@Transactional
	public void delete(int id){
		userRepository.delete(id);
	}

	@Override
	@Transactional
	public void updateAvatar(UserModel user, String avatar){
		UserModel userDB = userRepository.findOne(user.getId());
		userDB.setAvatar(avatar);
	}
	
	@Override
	@Transactional
	public List<UserModel> findAll() {
		return (List<UserModel>) userRepository.findAll();
	}

	@Override
	@Transactional
	public UserModel isValidUser(String username, String password){
		UserModel user = userRepository.findByUsername(username);
		if(user != null)
			if(user.getPassword().equals(password))
				return user;
		return null;
	}
	
	@Override
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

	@Override
	@Transactional
	public List<UserModel> findByProprietarioID(int id) {
		return null;
	}

	@Override
	@Transactional
	public UserModel update(UserModel user) {
		UserModel userCreated = user;
		return userRepository.save(userCreated);
	}

	@Override
	public UserModel updateUserPhoto(UserModel userModel, UserModel photo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserModel removePhotoUser(UserModel user, UserModel photo) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	@Transactional
//	public UserModel removePhotoUser(UserModel user, PhotoModel photo) {
//		
//		UserModel updatedUser = userRepository.findOne(user.getId());
//		
//		if (updatedUser == null)
//			return null;
//		
//		updatedUser.getFotoVotate().remove(photo);
//		
//		userRepository.save(updatedUser);
//		
//		return updatedUser;
//	}

//	@Override
//	@Transactional
//	public UserModel updateUserPhoto(UserModel userModel, PhotoModel photo){
//
//		UserModel updatedUser = userRepository.findOne(userModel.getId());
//		
//		if (updatedUser == null)
//			return null;
//		
//		updatedUser.getFotoVotate().add(photo);
//		
//		userRepository.save(updatedUser);
//		
//		return updatedUser;
//	}
	

}