package it.uniroma3.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.model.PhotoModel;
import it.uniroma3.repository.PhotoRepository;

@Service
public class PhotoService implements ServiceManager<PhotoModel> {

	@Resource
	private PhotoRepository photoRepository;
	
	@Override
	@Transactional
	public PhotoModel create(PhotoModel photo) {
		PhotoModel photoCreated = photo;
		return photoRepository.save(photoCreated);
	}

	@Override
	@Transactional
	public void delete(int id) {
		photoRepository.delete(id);
	}

	@Override
	@Transactional
	public List<PhotoModel> findAll() {
		return (List<PhotoModel>) photoRepository.findAll();
	}

	@Override
	@Transactional
	public PhotoModel update(PhotoModel t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public PhotoModel findById(int id) {
		return photoRepository.findOne(id);
	}

	@Override
	@Transactional
	public PhotoModel isValidUser(String name, String password) {
		return null;
	}

	@Override
	@Transactional
	public List<PhotoModel> findByProprietarioID(int id) {
		return photoRepository.findByProprietarioId(id);
	}

	@Override
	public PhotoModel updateUserPhoto(PhotoModel userModel, PhotoModel photo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhotoModel removePhotoUser(PhotoModel user, PhotoModel photo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean userExist(String username, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateAvatar(PhotoModel t, String avatar) {
		// TODO Auto-generated method stub
	}


}