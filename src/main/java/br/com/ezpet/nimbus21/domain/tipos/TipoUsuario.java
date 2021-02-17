package br.com.ezpet.nimbus21.domain.tipos;

public enum TipoUsuario {
	MASCOTE("Mascote"), 
	LOJA("Loja"), 
	VETERINARIO("Veterinário"), 
	SERVICO("Serviço");
	
	private final String displayValue;
	
	private TipoUsuario(String displayValue) {
		this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
}
