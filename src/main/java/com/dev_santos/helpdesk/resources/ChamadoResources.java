package com.dev_santos.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev_santos.helpdesk.domain.Chamado;
import com.dev_santos.helpdesk.domain.dtos.ChamadoDTO;
import com.dev_santos.helpdesk.services.ChamadoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResources {

	@Autowired
	private ChamadoServices chamadoServices;
	
	@GetMapping(value = "/{id}")
	private ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(new ChamadoDTO(chamadoServices.findById(id)));
	}
	
	@GetMapping
	private ResponseEntity<List<ChamadoDTO>> findAll(){
		List<ChamadoDTO> chamadoDTOs = chamadoServices.findAll().stream()
				.map(x -> new ChamadoDTO(x))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(chamadoDTOs);
	}
	
	@PostMapping
	private ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDTO){
		Chamado obj = chamadoServices.create(chamadoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	@PutMapping(value = "/{id}")
	private ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO chamadoDTO){
		Chamado obj = chamadoServices.update(id, chamadoDTO);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}
	
	
	
}
