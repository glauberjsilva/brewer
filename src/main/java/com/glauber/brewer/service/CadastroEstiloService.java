package com.glauber.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glauber.brewer.model.Estilo;
import com.glauber.brewer.repository.Estilos;
import com.glauber.brewer.service.exception.NomeEstiloCadastradoException;

@Service
public class CadastroEstiloService {
	
	@Autowired
	private Estilos estilos;

	@Transactional
	public void salvar(Estilo estilo) {
		Optional<Estilo> estiloOptional = estilos.findByNomeIgnoreCase(estilo.getNome());
		if(estiloOptional.isPresent()) {
			throw new NomeEstiloCadastradoException("Nome do estilo já cadastrado");
		}
		
		estilos.save(estilo);
	}
	
}
