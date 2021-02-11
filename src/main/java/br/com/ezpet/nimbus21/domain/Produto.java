package br.com.ezpet.nimbus21.domain;

import br.com.ezpet.nimbus21.domain.tipos.TipoAnimal;
import br.com.ezpet.nimbus21.domain.tipos.TipoFisico;
import br.com.ezpet.nimbus21.domain.tipos.TipoProduto;
import lombok.Data;

@Data
public class Produto {

	private Long codigo;
	private String foto;
	private String nome;
	private String descricao;
	private Double preco;
	private Double precoAntigo;
	private TipoProduto tipoProduto;
	private TipoAnimal tipoAnimal;
	private TipoFisico tipoFisico;
	private UsuarioComercial usuarioComercial;
	private Long codigoUsuarioComercial;

}
