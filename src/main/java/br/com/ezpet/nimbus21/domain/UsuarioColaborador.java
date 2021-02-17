package br.com.ezpet.nimbus21.domain;

import br.com.ezpet.nimbus21.domain.enums.Role;
import lombok.Data;

@Data
public class UsuarioColaborador {

	private Long codigo;
	private String nome;
	private String email;
	private String senha;
	private Role role = Role.ROLE_COLAB;
	
	public UsuarioColaborador(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
}
