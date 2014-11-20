package fr.istic.controller;

import java.awt.Button;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.istic.command.CommandModification;
import fr.istic.command.CommandSelection;
import fr.istic.command.CommandeTitre;
import fr.istic.model.Champ;
import fr.istic.model.IModele;
import fr.istic.view.Graphique;
import fr.istic.view.IGraphique;

public class Controller implements IController {

	private IGraphique graphique ;
	private IModele modele ;
	
	private Button boutonPrecedent; 
	private Button boutonSuivant ;
	
	private Champ selection ;
	
	public Controller(IGraphique graphique,IModele modele) {
		this.graphique = graphique ;
		//On ajoute toute les commandes nécessaires au graphique
		graphique.setCommandeSelection(new CommandSelection(this));
		graphique.setCommandeModification(new CommandModification(this));
		graphique.setCommandeModificationTitre(new CommandeTitre(this)) ;
		this.modele = modele ;
	}
	
	

	@Override
	public void suivant() {
		graphique.suivant();
	}

	@Override
	public void precedent() {
		graphique.precedent();
	}

	@Override
	public void selection() {
		selection = graphique.selection() ;
		System.out.println("Selection:"+selection.getIntitule());
	}

	public void modifierSelection()
	{
		//On récupère l'index de l'élément selectionné
		int indexModifie = modele.getChamps().indexOf(selection) ;
		//On récupère la liste de tous les champs
		ArrayList<Champ> champListe = new ArrayList(modele.getChamps()) ;
		
		//On crée un nouveau champ dont on charge les valeurs avec celui de l'élément modifié
		Champ unChamp = new Champ() ;
		unChamp.chargerChamp(selection);
		//On modifie les valeurs avec celui du champ modifié
		unChamp.chargerChamp(graphique.getChampModifie() );
		//On change les la liste des champs
		champListe.set(indexModifie,unChamp) ;
		//On modifie la liste des champs dans le modèle
		modele.setChamps(champListe);
		
		modele.mettreAJour(); 
		System.out.println("Modification:"+unChamp);
	}
	
	public void ajouter()
	{
		//On récupère la liste des champs
		ArrayList<Champ> champs = new ArrayList<Champ>(modele.getChamps()) ;
		//On ajoute un champ qui fait 10% 
		champs.add(new Champ("nouveau", "nouveau", modele.getTotal()/10)) ;
		//On change les valeurs des champs dans le modèle
		modele.setChamps(champs);
	}



	@Override
	public void modifierTitre() {
		//On modifie le titre du modèle
		modele.setTitre(graphique.getTitre());
	}

}
