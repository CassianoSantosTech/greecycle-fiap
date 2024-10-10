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

import br.com.fiap.greencycle.model.ColetaExtra;
import br.com.fiap.greencycle.service.ColetaExtraService;

@RestController
@RequestMapping("/api/coletaextra")
public class ColetaExtraController {
	
	@Autowired
	private ColetaExtraService coletaExtraService;
	
	// Endpoint GET por Id
	@GetMapping("/{idColetaE}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ColetaExtra> buscarPeloId(@PathVariable String idColetaE) {
		return ResponseEntity.ok(coletaExtraService.buscarColetaPeloId(idColetaE));
	}
	
	// Endpoint GET All
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ColetaExtra>> listarTodos(){
		var listaColetas = coletaExtraService.listarTodasAsColetas();
		return ResponseEntity.ok(listaColetas);
	}
	
	// Endpoint POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ColetaExtra salvar(@RequestBody ColetaExtra coletaExtra) {
        System.out.println("Recebido para salvar: " + coletaExtra);
		return coletaExtraService.salvar(coletaExtra);
	}
	
	// Endpoint PUT
	@PutMapping("/{idColetaE}")
    @ResponseStatus(HttpStatus.OK)
    public ColetaExtra atualizar(@PathVariable String idColetaE, @RequestBody ColetaExtra coletaExtra) {
        ColetaExtra coletaExtraExistente = coletaExtraService.buscarColetaPeloId(idColetaE);
        
        if (coletaExtraExistente != null) {
            coletaExtraExistente.setDataColeta(coletaExtra.getDataColeta());
            coletaExtraExistente.setStatus(coletaExtra.getStatus());
            
            return coletaExtraService.salvar(coletaExtraExistente);
        } else {
            return null;
        }
    }
	
	// Endpoint DELETE
	@DeleteMapping("/{idColetaE}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable String idColetaE){
		coletaExtraService.excluir(idColetaE);
        
	}

}
