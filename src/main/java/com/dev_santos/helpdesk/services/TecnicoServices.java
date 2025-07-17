package com.dev_santos.helpdesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dev_santos.helpdesk.domain.Tecnico;
import com.dev_santos.helpdesk.repositories.TecnicoRepository;
import com.dev_santos.helpdesk.services.exceptions.ExistingObjectException;
import com.dev_santos.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoServices {

	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
	}
	
	public List<Tecnico> findAll(){
		return repository.findAll();
	}
	
	public Tecnico create(Tecnico tecnico) {
		
		try {
			return repository.save(tecnico);
		}catch(DataIntegrityViolationException e) {
			throw new ExistingObjectException("Objeto existente");
		}catch(IllegalArgumentException e) {
			throw new ExistingObjectException("Objeto existente");
		}
		
	}
	
}
