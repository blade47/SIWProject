package it.uniroma3.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.model.UserModel;

public interface UserRepository extends CrudRepository<UserModel, Integer> {
	
	public UserModel findByUsername(String username);

	public UserModel findByEmail(String email);

}