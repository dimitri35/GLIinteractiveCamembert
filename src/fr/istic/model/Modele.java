package fr.istic.model;

import java.util.ArrayList;
import java.util.List;

public class Modele implements IModele, Observable
{
	private List<Observer> listeObservers ;
	
	private String titre ;
	private List<Champ> champs ;
	
	public Modele(String titre,List<Champ> champs) {
		this.titre = titre ;
		this.champs = champs ;
		this.listeObservers = new ArrayList<Observer>() ;
	}

	@Override
	public String getTitre() {
		return titre ;
	}

	@Override
	public void setTitre(String titre) {
		this.titre = titre ;
		notifyObservers();
	}

	@Override
	public List<Champ> getChamps() {
		return new ArrayList<Champ>(champs) ;	
	}
	
	@Override
	public void setChamps(List<Champ> lesChamps) {
		notifyObservers();
		this.champs = lesChamps ;
	}

	@Override
	public Double getTotal() {
		System.out.println("les champs:"+champs);
		Double resultat = 0.0 ;
		//Pour chaque champ on additionne la valeur des champs
		for(Champ unChamp : champs)
		{
			resultat = resultat+unChamp.getValeur() ;
		}
		
		return resultat ;
	}

	@Override
	public void addObserver(Observer obs) {
		listeObservers.add(obs) ;
	}

	@Override
	public void notifyObservers() {
		//Pour chaque observeur on notifie l'observeur
		for(Observer obs: listeObservers)
		{
			obs.update(this);
		}
	}

	@Override
	public void mettreAJour() {
		notifyObservers();
	}
	
	
	
}
