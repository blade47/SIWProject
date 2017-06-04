package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.PhotoModel;

public interface PhotoRepository extends CrudRepository<PhotoModel, Integer> {

	public List<PhotoModel> findByProprietarioId(int proprietario_id);
}
