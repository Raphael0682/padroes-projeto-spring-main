package edu.projeto.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.projeto.gof.model.Objeto;
import edu.projeto.gof.service.ObjetoService;

@RestController
@RequestMapping("objetos")
public class ObjetoRestController {

	@Autowired
	private ObjetoService objetoService;

	@GetMapping
	public ResponseEntity<Iterable<Objeto>> buscarTodos() {
		return ResponseEntity.ok(objetoService.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Objeto> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(objetoService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Objeto> inserir(@RequestBody Objeto objeto) {
		objetoService.inserir(objeto);
		return ResponseEntity.ok(objeto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Objeto> atualizar(@PathVariable Long id, @RequestBody Objeto objeto) {
		objetoService.atualizar(id, objeto);
		return ResponseEntity.ok(objeto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		objetoService.deletar(id);
		return ResponseEntity.ok().build();
	}
}
