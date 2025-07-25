package com.dev_santos.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.dev_santos.helpdesk.domain.dtos.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity(name = "tb_cliente")
public class Cliente extends Pessoa {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
	}
	
	public Cliente(ClienteDTO clienteDTO) {
		BeanUtils.copyProperties(clienteDTO, this);
		clienteDTO.getPerfis().stream().forEach(x -> addPerfil(x));
	}
	
	public void addChamados(Chamado chamado) {
		this.chamados.add(chamado);
	}
	
	public List<Chamado> getChamados(){
		return chamados;
	}
	
}
