package br.com.fiap.greencycle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import br.com.fiap.greencycle.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	UserDetails findByEmail(String email);

}
