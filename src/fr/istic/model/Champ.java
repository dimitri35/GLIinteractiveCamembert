package fr.istic.model;

public class Champ {

	private String intitule, description ;
	private Double valeur ;
	
	public Champ() {
		
	}
	public Champ(String intitule, String description, Double valeur)
	{
		this.intitule = intitule ;
		this.description = description ;
		this.valeur = valeur  ;
	}
	
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getValeur() {
		return valeur;
	}
	public void setValeur(Double valeur) {
		this.valeur = valeur ;
	}
	
	public void chargerChamp(Champ champ) 
	{
		if(champ!=null)
		{
			if(champ.getDescription() != null)
			{
				description = champ.getDescription() ;
			}
			
			if(champ.getIntitule() != null)
			{
				intitule = champ.getIntitule() ;
			}
			
			if(champ.getValeur() != null)
			{
				valeur = champ.getValeur() ;
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		 Champ champ = (Champ) obj ;
		 return  champ.getDescription().equals(description) && champ.getValeur().equals(valeur) && champ.getIntitule().equals(intitule) ;
	}
}
