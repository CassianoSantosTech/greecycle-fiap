package br.com.fiap.greencycle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.fiap.greencycle.model.Login;

public interface LoginRepository extends MongoRepository<Login, String> {

}
