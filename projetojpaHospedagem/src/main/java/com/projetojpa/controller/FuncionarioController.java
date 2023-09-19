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

import com.projetojpa.entities.Funcionario;
import com.projetojpa.service.FuncionarioService;

@RestController
	@RequestMapping("/funcionarios")
	public class FuncionarioController {
	

		private final FuncionarioService funcionarioService;

		@Autowired
		public FuncionarioController(FuncionarioService funcionarioService) {
			this.funcionarioService = funcionarioService;
		}
		@GetMapping("/{codigo}")
		public ResponseEntity<Funcionario> buscaFuncControlCodigo(@PathVariable int codigo){
			Funcionario funcionario = funcionarioService.buscaFuncionarioId(codigo);
			if(funcionario != null) {
				return ResponseEntity.ok(funcionario);
			}
			else {
				return ResponseEntity.notFound().build();
			}

		}
		@GetMapping("/")
		public ResponseEntity<List<Funcionario>> buscaTodosFuncionarioControl(){
			List<Funcionario> funcionario =  funcionarioService.buscaTodosFuncionarios();
			return ResponseEntity.ok(funcionario);
		}

		@PostMapping("/")
		public ResponseEntity<Funcionario> salvaFuncionariosControl(@RequestBody Funcionario funcionario){
			Funcionario salvaFuncionario = funcionarioService.salvaFuncionario(funcionario);
			return ResponseEntity.status(HttpStatus.CREATED).body(salvaFuncionario);
		}

		@PutMapping("/{codigo}")
		public ResponseEntity<Funcionario> alterarFuncionarioControl(@PathVariable int codigo, @RequestBody Funcionario funcionario){
			Funcionario alterarFuncionario = funcionarioService.alterarFuncionario( codigo, funcionario);
			if(alterarFuncionario != null) {
				return ResponseEntity.ok(funcionario);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		@DeleteMapping("/{codigo}")
		public ResponseEntity<String> apagarFuncionarioControl(@PathVariable int codigo){
			boolean apagar = funcionarioService.apagaFunc(codigo);
			if(apagar) {
				return ResponseEntity.ok().body("O  funcionario foi excluído com sucesso");
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}


	}
