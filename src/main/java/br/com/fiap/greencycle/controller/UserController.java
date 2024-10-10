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
import br.com.fiap.greencycle.model.User;
import br.com.fiap.greencycle.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// Endpoint GET
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<User>> listarTodos(){
		var lista = userService.listarTodosOsUsuarios();
		return ResponseEntity.ok(lista);
	}
	
	// Endpoint POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User salvar(@RequestBody User user) {
        System.out.println("Recebido para salvar: " + user);
		return userService.salvar(user);
	}
	
	// Endpoint PUT
	@PutMapping("/{idUser}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<User> atualizar(@PathVariable String idUser, @RequestBody User user) {
	    User usuarioExistente = userService.buscarUserPeloId(idUser);

	    if (usuarioExistente != null) {
	        usuarioExistente.setNome(user.getNome());
	        usuarioExistente.setEmail(user.getEmail());
	        usuarioExistente.setSenha(user.getSenha());
	        usuarioExistente.setRole(user.getRole());

	        return ResponseEntity.ok(userService.salvar(usuarioExistente));
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	// Endpoint DELETE 
	@DeleteMapping("/{idUser}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable String idUser){
	    userService.excluir(idUser);
	}

}
