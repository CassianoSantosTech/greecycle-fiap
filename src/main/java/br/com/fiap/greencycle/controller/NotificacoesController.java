package br.com.fiap.greencycle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.greencycle.model.Notificacoes;
import br.com.fiap.greencycle.service.NotificacoesService;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacoesController {
	
	@Autowired
	private NotificacoesService notificacoesService;
	
	// Endpoint GET
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Notificacoes>> listarTodos() {
		var lista = notificacoesService.listarTodosAsNotificacoes();
		return ResponseEntity.ok(lista);
	}
	
	// Endpoint POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Notificacoes salvar(@RequestBody Notificacoes notificacoes) {
		System.out.println("Recebido para salvar: " + notificacoes);
		return notificacoesService.salvar(notificacoes);
	}
	
	// Endpoint PUT
	@PutMapping("/{idNotificacao}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Notificacoes> atualizar(@PathVariable String idNotificacao, @RequestBody Notificacoes notificacoes) {
		Notificacoes usuarioExistente = notificacoesService.atualizar(notificacoes);

		if (usuarioExistente != null) {
			usuarioExistente.setUserEmail(notificacoes.getUserEmail());
			usuarioExistente.setTpNotificacao(notificacoes.getTpNotificacao());
			usuarioExistente.setMensagem(notificacoes.getMensagem());
			usuarioExistente.setDataEnvio(notificacoes.getDataEnvio());

			return ResponseEntity.ok(notificacoesService.salvar(usuarioExistente));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// Endpoint DELETE
	@DeleteMapping("/{idNotificacao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable String idNotificacao) {
		notificacoesService.excluir(idNotificacao);
	}

}
