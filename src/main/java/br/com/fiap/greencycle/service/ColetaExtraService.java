package br.com.fiap.greencycle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.greencycle.model.ColetaExtra;
import br.com.fiap.greencycle.repository.ColetaExtraRepository;

@Service
public class ColetaExtraService {
	
	@Autowired
	private ColetaExtraRepository greencycleRepository;
	
	// GET
	public List<ColetaExtra> listarTodasAsColetas(){
		var listaColetas = greencycleRepository.findAll();
		return listaColetas;
	}

	public ColetaExtra buscarColetaPeloId(String idColetaE) {
		var coletaOptional = greencycleRepository.findById(idColetaE);
		if(coletaOptional.isPresent()) {
			return coletaOptional.get();
		} else {
			throw new RuntimeException("Coleta não encontrada");
		}
	}
	
	// POST
	public ColetaExtra salvar(ColetaExtra coletaExtra) {
        System.out.println("Salvando: " + coletaExtra);
		return greencycleRepository.save(coletaExtra);
	}
	
	// PUT
	public ColetaExtra atualizar(ColetaExtra coletaExtra) {
		return greencycleRepository.save(coletaExtra);
	}
	
	// DELETE
	public void excluir(String idColetaE){
		greencycleRepository.deleteById(idColetaE);
    }
	
}
