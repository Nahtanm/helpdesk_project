package com.dev_santos.helpdesk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev_santos.helpdesk.domain.Tecnico;
import com.dev_santos.helpdesk.repositories.TecnicoRepository;
import com.dev_santos.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoServices {

	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
	}
	
}
