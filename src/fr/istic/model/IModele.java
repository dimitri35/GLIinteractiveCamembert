package fr.istic.model;

import java.util.List;

public interface IModele {

	public List<Champ> getChamps() ;
	public void setChamps(List<Champ> lesChamps) ;
	public String getTitre() ;
	public void setTitre(String titre) ;
	public Double getTotal() ;
	
	public void mettreAJour() ;
}
