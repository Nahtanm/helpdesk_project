package com.dev_santos.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.dev_santos.helpdesk.domain.Chamado;
import com.dev_santos.helpdesk.domain.enums.Prioridade;
import com.dev_santos.helpdesk.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class ChamadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;
	
	@NotNull(message = "O campo PRIORIDADE é requerido")
	private Integer prioridade;
	@NotNull(message = "O campo STATUS é requerido")
	private Integer status;
	@NotNull(message = "O campo TÍTULO é requerido")
	private String titulo;
	@NotNull(message = "O campo OBSERVAÇÕES é requerido")
	private String observacoes;
	
	private Integer idCliente;
	private Integer idTecnico;
	private String nomeTecnico;
	private String nomeCliente;

	public ChamadoDTO() {
		super();
	}

	public ChamadoDTO(Chamado chamado) {
		this.id = chamado.getId();
		this.dataAbertura = chamado.getDataAbertura();
		this.dataFechamento = chamado.getDataFechamento();
		this.prioridade = chamado.getPrioridade().getCodigo();
		this.status = chamado.getStatus().getCodigo();
		this.titulo = chamado.getTitulo();
		this.observacoes = chamado.getObservacoes();
		this.idCliente = chamado.getCliente().getId();
		this.idTecnico = chamado.getTecnico().getId();
		this.nomeTecnico = chamado.getTecnico().getNome();
		this.nomeCliente = chamado.getCliente().getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Prioridade getPrioridade() {
		return Prioridade.toEnum(prioridade);
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Status getStatus() {
		return Status.toEnum(status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(Integer idTecnico) {
		this.idTecnico = idTecnico;
	}

	public String getNomeTecnico() {
		return nomeTecnico;
	}

	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

}
