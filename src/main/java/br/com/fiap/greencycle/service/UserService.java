package br.com.fiap.greencycle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.fiap.greencycle.model.User;
import br.com.fiap.greencycle.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User gravar(User usuario) {
		
		String senhaCripto = new BCryptPasswordEncoder().encode(usuario.getPassword());
		usuario.setSenha(senhaCripto);
		return userRepository.save(usuario);
	}
	
	public User buscarUserPeloId(String idUser) {
		var userOptional = userRepository.findById(idUser);
		if(userOptional.isPresent()) {
			return userOptional.get();
		} else {
			throw new RuntimeException("Coleta não encontrada");
		}
	}
	
	// GET
	public List<User> listarTodosOsUsuarios(){
		var listaUsers = userRepository.findAll();
		return listaUsers;
	}
	
	// POST
	public User salvar(User user) {
        System.out.println("Salvando: " + user);
		return userRepository.save(user);
	}
	
	// PUT
	public User atualizar(User user) {
		return userRepository.save(user);
	}
	
	// DELETE
	public void excluir(String idUser){
		userRepository.deleteById(idUser);
    }

}
