package com.dev_santos.helpdesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev_santos.helpdesk.domain.Chamado;
import com.dev_santos.helpdesk.repositories.ChamadoRepository;
import com.dev_santos.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoServices {

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public Chamado findById(Integer id) {
		return chamadoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}
	
	public List<Chamado> findAll(){
		return chamadoRepository.findAll();
	}
	
}
