package com.dev_santos.helpdesk.domain.enums;

public enum Prioridade {

	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(3, "ALTA");

	private Integer codigo;
	private String descricao;
	
	private Prioridade(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Prioridade toEnum(Integer codigo) {
		for(Prioridade x : Prioridade.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Prioridade inválido");
	}
	
}
