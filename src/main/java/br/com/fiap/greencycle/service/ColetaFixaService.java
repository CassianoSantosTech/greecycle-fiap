package br.com.fiap.greencycle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fiap.greencycle.model.ColetaFixa;
import br.com.fiap.greencycle.repository.ColetaFixaRepository;

@Service
public class ColetaFixaService {
	
	@Autowired
	private ColetaFixaRepository coletaFixaRepository;
	
	// GET
	public List<ColetaFixa> listarTodasAsColetas(){
		var listaColetas = coletaFixaRepository.findAll();
		return listaColetas;
	}

	public ColetaFixa buscarColetaPeloId(String idColetaE) {
		var coletaOptional = coletaFixaRepository.findById(idColetaE);
		if(coletaOptional.isPresent()) {
			return coletaOptional.get();
		} else {
			throw new RuntimeException("Coleta não encontrada");
		}
	}
	
	// POST
	public ColetaFixa salvar(ColetaFixa coletaExtra) {
        System.out.println("Salvando: " + coletaExtra);
		return coletaFixaRepository.save(coletaExtra);
	}
	
	// PUT
	public ColetaFixa atualizar(ColetaFixa coletaExtra) {
		return coletaFixaRepository.save(coletaExtra);
	}
	
	// DELETE
	public void excluir(String idColetaE){
		coletaFixaRepository.deleteById(idColetaE);
    }
	
}
