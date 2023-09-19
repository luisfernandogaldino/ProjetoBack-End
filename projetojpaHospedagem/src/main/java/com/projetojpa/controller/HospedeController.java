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

import com.projetojpa.entities.Hospede;
import com.projetojpa.service.HospedeService;

@RestController
	@RequestMapping("/hospedes")
	public class HospedeController {
	
     private final HospedeService hospedeService;

		@Autowired
		public HospedeController(HospedeService hospedeService) {
			this.hospedeService = hospedeService;
		}
		@GetMapping("/{codigo}")
		public ResponseEntity<Hospede> buscaHospedeControlCodigo(@PathVariable int codigo){
			Hospede hospede = hospedeService.buscaHospede(codigo);
			if(hospede != null) {
				return ResponseEntity.ok(hospede);
			}
			else {
				return ResponseEntity.notFound().build();
			}

		}
		@GetMapping("/")
		public ResponseEntity<List<Hospede>> buscaTodosHospedeControl(){
			List<Hospede> hospede =  hospedeService.buscaTodosHospedes();
			return ResponseEntity.ok(hospede);
		}

		@PostMapping("/")
		public ResponseEntity<Hospede> salvaHospedeControl(@RequestBody Hospede hospede){
			Hospede salvaHospede = hospedeService.salvaHospede(hospede);
			return ResponseEntity.status(HttpStatus.CREATED).body(salvaHospede);
		}

		@PutMapping("/{codigo}")
		public ResponseEntity<Hospede> alteraHospedeControl(@PathVariable int codigo, @RequestBody Hospede hospede){
			Hospede alterarHospede = hospedeService.alterarHospede( codigo, hospede);
			if(alterarHospede != null) {
				return ResponseEntity.ok(hospede);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		@DeleteMapping("/{codigo}")
		public ResponseEntity<String> apagaHospedeControl(@PathVariable int codigo){
			boolean apagar = hospedeService.apagaHospede(codigo);
			if(apagar) {
				return ResponseEntity.ok().body("O  hospede foi excluído com sucesso");
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}


	}
