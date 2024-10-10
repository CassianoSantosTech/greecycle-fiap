package br.com.fiap.greencycle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.fiap.greencycle.model.ColetaExtra;

public interface ColetaExtraRepository extends MongoRepository<ColetaExtra, String> {

}
