package com.dev_santos.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.dev_santos.helpdesk.domain.dtos.TecnicoDTO;
import com.dev_santos.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity(name = "tb_tecnico")
public class Tecnico extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
	}
	
	public Tecnico(TecnicoDTO objDTO) {
		BeanUtils.copyProperties(objDTO, this);
		for(Perfil x : objDTO.getPerfis()) {
			addPerfil(x);
		}
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
	}

	public void addChamados(Chamado chamado) {
		this.chamados.add(chamado);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}
}
