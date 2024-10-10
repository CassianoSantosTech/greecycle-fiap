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

import br.com.fiap.greencycle.model.ColetaFixa;
import br.com.fiap.greencycle.service.ColetaFixaService;

@RestController
@RequestMapping("/api/coletafixa")
public class ColetaFixaController {
	
	@Autowired
	private ColetaFixaService coletaFixaService;
	
	// Endpoint GET por ID
	@GetMapping("/{idColetaF}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ColetaFixa> buscarPeloId(@PathVariable String idColetaF) {
		return ResponseEntity.ok(coletaFixaService.buscarColetaPeloId(idColetaF));
	}

	// Endpoint GET All
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ColetaFixa>> listarTodos() {
		var listaColetas = coletaFixaService.listarTodasAsColetas();
		return ResponseEntity.ok(listaColetas);
	}
	
	// Endpoint POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ColetaFixa salvar(@RequestBody ColetaFixa coletaFixa) {
		System.out.println("Recebido para salvar: " + coletaFixa);
		return coletaFixaService.salvar(coletaFixa);
	}
	
	// Endpoint PUT
	@PutMapping("/{idColetaF}")
	@ResponseStatus(HttpStatus.OK)
	public ColetaFixa atualizar(@PathVariable String idColetaF, @RequestBody ColetaFixa coletaFixa) {
		ColetaFixa coletaFixaExistente = coletaFixaService.buscarColetaPeloId(idColetaF);

		if (coletaFixaExistente != null) {
			coletaFixaExistente.setDataColeta(coletaFixa.getDataColeta());
			coletaFixaExistente.setStatus(coletaFixa.getStatus());

			return coletaFixaService.salvar(coletaFixaExistente);
		} else {
			return null;
		}
	}
		
	// Endpoint DELETE
	@DeleteMapping("/{idColetaF}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable String idColetaF) {
		coletaFixaService.excluir(idColetaF);

	}	

}
