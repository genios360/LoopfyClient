package br.com.ezpet.nimbus21.domain.tipos;

public enum TipoAnimal {
	CACHORRO("Cachorro"), 
	GATO("Gato"), 
	AVE("Ave"), 
	PEIXE("Peixe"), 
	REPTIL("Réptil"), 
	ROEDOR("Roedor");
	
	private final String displayValue;
	
	private TipoAnimal(String displayValue) {
		this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
}
