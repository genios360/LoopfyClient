package br.com.ezpet.nimbus21.domain;

import lombok.Data;

@Data
public class Foto {

	private Long codigo;
	private String nome;
	private String link;
	private String tipo;
	private byte[] fotoByte;

	public Foto(String nome, String tipo, byte[] fotoByte) {
		this.nome = nome;
		this.tipo = tipo;
		this.fotoByte = fotoByte;
	}
	
	
}
