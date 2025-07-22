package com.dev_santos.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev_santos.helpdesk.domain.Tecnico;
import com.dev_santos.helpdesk.domain.dtos.TecnicoDTO;
import com.dev_santos.helpdesk.services.TecnicoServices;

import jakarta.validation.Valid;

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
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<TecnicoDTO> tecnicosList = services.findAll()
				.stream()
				.map(x -> new TecnicoDTO(x))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(tecnicosList);
	}
	
	@PostMapping
	public ResponseEntity<Tecnico> create(@Valid @RequestBody Tecnico tecnico){
		services.create(tecnico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(tecnico.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
} 
