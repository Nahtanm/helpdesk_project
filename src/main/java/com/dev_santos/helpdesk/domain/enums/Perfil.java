package com.dev_santos.helpdesk.domain.enums;

public enum Perfil {

	ADMIN(1, "ADMIN"), CLIENTE(2, "CLIENTE"), TECNICO(3, "TECNICO");

	private Integer codigo;
	private String descricao;
	
	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer codigo) {
		for(Perfil x : Perfil.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Perfil inválido");
	}
	
}
