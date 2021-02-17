package br.com.ezpet.nimbus21.domain;

import java.util.List;

import br.com.ezpet.nimbus21.domain.enums.Role;
import br.com.ezpet.nimbus21.domain.tipos.TipoUsuario;
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
	private String abertura;
	private String fechamento;
	private TipoUsuario tipoUsuario;
	private Role role = Role.ROLE_COMERCIAL;
	private String foto;
	private String cep;
	private String nomeContato;
	private String telefone;
	private List<Produto> produtos;
}
