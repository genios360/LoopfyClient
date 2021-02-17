package br.com.ezpet.nimbus21.domain;

import br.com.ezpet.nimbus21.domain.enums.Role;
import lombok.Data;

@Data
public class UsuarioAdmin {
	
	private Long codigo;
	private String nome;
	private String email;
	private String senha;
	private Role role = Role.ROLE_ADMIN;
	
	public UsuarioAdmin(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	
}
