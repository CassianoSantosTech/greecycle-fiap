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

import br.com.fiap.greencycle.model.Login;
import br.com.fiap.greencycle.service.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	// Endpoint GET
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Login>> listarTodos() {
		var lista = loginService.listarTodosOsLogins();
		return ResponseEntity.ok(lista);
	}
	
	// Endpoint POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Login salvar(@RequestBody Login login) {
		System.out.println("Recebido para salvar: " + login);
		return loginService.salvar(login);
	}
	
	// Endpoint PUT
	@PutMapping("/{idLogin}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Login> atualizar(@PathVariable String idLogin, @RequestBody Login login) {
		Login usuarioExistente = loginService.atualizar(login);

		if (usuarioExistente != null) {
			usuarioExistente.setEmailLogin(login.getEmailLogin());
			usuarioExistente.setSenhaLogin(login.getSenhaLogin());
			usuarioExistente.setRole(login.getRole());

			return ResponseEntity.ok(loginService.salvar(usuarioExistente));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// Endpoint DELETE
	@DeleteMapping("/{idLogin}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable String idLogin) {
		loginService.excluir(idLogin);
	}
	
}
