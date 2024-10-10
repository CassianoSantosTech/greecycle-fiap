package br.com.fiap.greencycle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fiap.greencycle.model.Login;
import br.com.fiap.greencycle.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	// GET
	public List<Login> listarTodosOsLogins(){
		var listaColetas = loginRepository.findAll();
		return listaColetas;
	}
	
	// POST
	public Login salvar(Login login) {
        System.out.println("Salvando: " + login);
		return loginRepository.save(login);
	}
	
	// PUT
	public Login atualizar(Login idLogin) {
		return loginRepository.save(idLogin);
	}
	
	// DELETE
	public void excluir(String idLogin){
		loginRepository.deleteById(idLogin);
    }
	
}
