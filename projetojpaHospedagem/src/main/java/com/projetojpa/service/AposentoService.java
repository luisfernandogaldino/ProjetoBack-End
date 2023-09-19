package com.projetojpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.entities.Aposento;
import com.projetojpa.repository.AposentoRepository;

@Service
	public class AposentoService {
	
	
		private final AposentoRepository aposentoRepository;

		@Autowired
		public AposentoService(AposentoRepository aposentoRepository) {
			this.aposentoRepository = aposentoRepository;
		}
		public List<Aposento> buscaTodosAposentos(){
			return aposentoRepository.findAll();
		}
		
		public  Aposento  buscaAposento(int codigo) {
			Optional <Aposento> aposento = aposentoRepository.findById(codigo);
			return aposento.orElse(null);
		}
		
		public Aposento salvaAposento(Aposento aposento) {
			return aposentoRepository.save(aposento);
		}
		
		public Aposento alterarAposento(int codigo, Aposento alterarAposento) {
			Optional <Aposento> existeAposento = aposentoRepository.findById(codigo);
			if(existeAposento.isPresent()) {
				alterarAposento.setCodigo(codigo);
				return aposentoRepository.save(alterarAposento);
			}
			return null;
		}
		public boolean apagaAposento(int codigo) {
			Optional <Aposento> existeAposento =  aposentoRepository.findById(codigo);
			if(existeAposento.isPresent()) {
				aposentoRepository.deleteById(codigo);
				return true;
			}
			return false;
		}
		}

