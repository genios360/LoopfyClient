package br.com.ezpet.nimbus21.domain.tipos;

public enum TipoFisico {
	COMIDA("Comida"), 
	BRINQUEDO("Brinquedo"), 
	ACESSORIO("Acessório"), 
	LIMPEZA("Limpeza"), 
	ROUPA("Roupa"), 
	SAUDE("Saúde");
	
	private final String displayValue;
	
	private TipoFisico(String displayValue) {
		this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
}
