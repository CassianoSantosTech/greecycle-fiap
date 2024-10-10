package br.com.fiap.greencycle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fiap.greencycle.model.Notificacoes;
import br.com.fiap.greencycle.repository.NotificacoesRepository;

@Service
public class NotificacoesService {
	
	@Autowired
	private NotificacoesRepository notificacoesRepository;
	
	// GET
	public List<Notificacoes> listarTodosAsNotificacoes(){
		var listaColetas = notificacoesRepository.findAll();
		return listaColetas;
	}
	
	// POST
	public Notificacoes salvar(Notificacoes notificacoes) {
        System.out.println("Salvando: " + notificacoes);
		return notificacoesRepository.save(notificacoes);
	}
	
	// PUT
	public Notificacoes atualizar(Notificacoes notificacoes) {
		return notificacoesRepository.save(notificacoes);
	}
	
	// DELETE
	public void excluir(String idNotificacao){
		notificacoesRepository.deleteById(idNotificacao);
    }
	
}
