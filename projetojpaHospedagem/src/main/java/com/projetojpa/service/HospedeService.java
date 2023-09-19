package com.projetojpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.entities.Hospede;
import com.projetojpa.repository.HospedeRepository;

@Service
	public class HospedeService {
	
	
		private final HospedeRepository hospedeRepository;

		@Autowired
		public HospedeService(HospedeRepository hospedeRepository) {
			this.hospedeRepository = hospedeRepository;
		}
		public List<Hospede> buscaTodosHospedes(){
			return hospedeRepository.findAll();
		}
		
		public  Hospede  buscaHospede(int codigo) {
			Optional <Hospede> hospede = hospedeRepository.findById(codigo);
			return hospede.orElse(null);
		}
		
		public Hospede salvaHospede(Hospede hospede) {
			return hospedeRepository.save(hospede);
		}
		
		public Hospede alterarHospede(int codigo, Hospede alterarHospede) {
			Optional <Hospede> existeHospede = hospedeRepository.findById(codigo);
			if(existeHospede.isPresent()) {
				alterarHospede.setCodigo(codigo);
				return hospedeRepository.save(alterarHospede);
			}
			return null;
		}
		public boolean apagaHospede(int codigo) {
			Optional <Hospede> existeHospede =  hospedeRepository.findById(codigo);
			if(existeHospede.isPresent()) {
				hospedeRepository.deleteById(codigo);
				return true;
			}
			return false;
		}

}
