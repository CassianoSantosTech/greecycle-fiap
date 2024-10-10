package br.com.fiap.greencycle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.fiap.greencycle.model.Notificacoes;

public interface NotificacoesRepository extends MongoRepository<Notificacoes, String> {

}
