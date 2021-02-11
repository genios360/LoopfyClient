package br.com.ezpet.nimbus21.domain;

import br.com.ezpet.nimbus21.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioComercial {

	private Long codigo;
	private String nome;
	private String cnpj;
	private String senha;
	private String email;
	private Role role = Role.ROLE_COMERCIAL;
}
