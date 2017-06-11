package it.uniroma3.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.RuoloModel;
import it.uniroma3.repository.RuoloRepository;

@Service
public class RuoloService {
	
	@Resource
	private RuoloRepository ruoloRepository;

	@Transactional
	public RuoloModel findById(int id) {
		return ruoloRepository.findOne(id);
	}

}
