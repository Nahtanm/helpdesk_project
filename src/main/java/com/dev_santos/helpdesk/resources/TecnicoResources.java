package com.dev_santos.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev_santos.helpdesk.domain.Tecnico;
import com.dev_santos.helpdesk.domain.dtos.TecnicoDTO;
import com.dev_santos.helpdesk.services.TecnicoServices;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResources {

	@Autowired
	private TecnicoServices services;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		Tecnico entity = services.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(entity));
	}
	
}
