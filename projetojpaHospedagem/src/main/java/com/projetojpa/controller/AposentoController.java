package com.projetojpa.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Aposento;
import com.projetojpa.service.AposentoService;

@RestController
	@RequestMapping("/aposentos")
	public class AposentoController {
	
     private final AposentoService aposentoService;

		@Autowired
		public AposentoController(AposentoService aposentoService) {
			this.aposentoService = aposentoService;
		}
		@GetMapping("/{codigo}")
		public ResponseEntity<Aposento> buscaAposentoControlCodigo(@PathVariable int codigo){
			Aposento Aposento = aposentoService.buscaAposento(codigo);
			if(Aposento != null) {
				return ResponseEntity.ok(Aposento);
			}
			else {
				return ResponseEntity.notFound().build();
			}

		}
		@GetMapping("/")
		public ResponseEntity<List<Aposento>> buscaTodosAposentoControl(){
			List<Aposento> aposento =  aposentoService.buscaTodosAposentos();
			return ResponseEntity.ok(aposento);
		}

		@PostMapping("/")
		public ResponseEntity<Aposento> salvaAposentoControl(@RequestBody Aposento aposento){
			Aposento salvaAposento = aposentoService.salvaAposento(aposento);
			return ResponseEntity.status(HttpStatus.CREATED).body(salvaAposento);
		}

		@PutMapping("/{codigo}")
		public ResponseEntity<Aposento> alteraAposentoControl(@PathVariable int codigo, @RequestBody Aposento aposento){
			Aposento alterarAposento = aposentoService.alterarAposento( codigo, aposento);
			if(alterarAposento != null) {
				return ResponseEntity.ok(aposento);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		@DeleteMapping("/{codigo}")
		public ResponseEntity<String> apagaAposentoControl(@PathVariable int codigo){
			boolean apagar = aposentoService.apagaAposento(codigo);
			if(apagar) {
				return ResponseEntity.ok().body("O  aposento foi excluído com sucesso");
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}


	}
