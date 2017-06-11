package it.uniroma3.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.model.PhotoModel;
import it.uniroma3.repository.PhotoRepository;

@Service
public class PhotoService {

	@Resource
	private PhotoRepository photoRepository;
	
	@Transactional
	public PhotoModel create(PhotoModel photo) {
		PhotoModel photoCreated = photo;
		return photoRepository.save(photoCreated);
	}

	@Transactional
	public void delete(int id) {
		photoRepository.delete(id);
	}

	@Transactional
	public List<PhotoModel> findAll() {
		return (List<PhotoModel>) photoRepository.findAll();
	}

	@Transactional
	public PhotoModel findById(int id) {
		return photoRepository.findOne(id);
	}

	@Transactional
	public List<PhotoModel> findByProprietarioID(int id) {
		return photoRepository.findByProprietarioId(id);
	}


}