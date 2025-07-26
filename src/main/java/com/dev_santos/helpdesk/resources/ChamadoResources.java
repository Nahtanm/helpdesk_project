package com.dev_santos.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev_santos.helpdesk.domain.dtos.ChamadoDTO;
import com.dev_santos.helpdesk.services.ChamadoServices;

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
	
	
	
}
