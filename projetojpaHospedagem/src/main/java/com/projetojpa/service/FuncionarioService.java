package com.projetojpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.entities.Funcionario;
import com.projetojpa.repository.FuncionarioRepository;

@Service
	public class FuncionarioService {
	
		private final FuncionarioRepository funcionarioRepository;

		@Autowired
		public FuncionarioService(FuncionarioRepository funcionarioRepository) {
			this.funcionarioRepository = funcionarioRepository;
		}
		public List<Funcionario> buscaTodosFuncionarios(){
			return funcionarioRepository.findAll();
		}
		
		public  Funcionario  buscaFuncionarioId(int codigo) {
			Optional <Funcionario> funcionario = funcionarioRepository.findById(codigo);
			return funcionario.orElse(null);
		}
		
		public Funcionario salvaFuncionario(Funcionario funcionario) {
			return funcionarioRepository.save(funcionario);
		}
		
		public Funcionario alterarFuncionario(int codigo, Funcionario alterarFunc) {
			Optional <Funcionario> existeFuncionario = funcionarioRepository.findById(codigo);
			if(existeFuncionario.isPresent()) {
				alterarFunc.setCodigo(codigo);
				return funcionarioRepository.save(alterarFunc);
			}
			return null;
		}
		public boolean apagaFunc(int codigo) {
			Optional <Funcionario> existeFuncionario = funcionarioRepository.findById(codigo);
			if(existeFuncionario.isPresent()) {
				funcionarioRepository.deleteById(codigo);
				return true;
			}
			return false;
		}

}

	
