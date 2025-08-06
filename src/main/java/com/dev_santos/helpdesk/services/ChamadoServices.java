package com.dev_santos.helpdesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev_santos.helpdesk.domain.Chamado;
import com.dev_santos.helpdesk.domain.Cliente;
import com.dev_santos.helpdesk.domain.Tecnico;
import com.dev_santos.helpdesk.domain.dtos.ChamadoDTO;
import com.dev_santos.helpdesk.domain.enums.Prioridade;
import com.dev_santos.helpdesk.repositories.ChamadoRepository;
import com.dev_santos.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoServices {

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private TecnicoServices tecnicoServices;
	
	@Autowired
	private ClienteServices clienteServices;
	
	public Chamado findById(Integer id) {
		return chamadoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}
	
	public List<Chamado> findAll(){
		return chamadoRepository.findAll();
	}
	
	public Chamado create(ChamadoDTO chamadoDTO) {
		return chamadoRepository.save(createChamado(chamadoDTO));
	}
	
	public Chamado createChamado(ChamadoDTO chamadoDTO) {
		Tecnico tecnico = tecnicoServices.findById(chamadoDTO.getIdTecnico());
		Cliente cliente = clienteServices.findByid(chamadoDTO.getIdCliente());
		
		Chamado chamado = new Chamado();
		
		if(chamadoDTO.getId() != null) {
			chamado.setId(chamadoDTO.getId());
		}
		
		chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
		chamado.setStatus(chamadoDTO.getStatus());
		chamado.setTitulo(chamadoDTO.getTitulo());
		chamado.setObservacoes(chamadoDTO.getObservacoes());
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		
		return chamado;
	}
	
}
