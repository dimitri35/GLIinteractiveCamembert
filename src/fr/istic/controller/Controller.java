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
		//graphique.addMouseListener(this);
		graphique.setCommandeSelection(new CommandSelection(this));
		graphique.setCommandeModification(new CommandModification(this));
		graphique.setCommandeModificationTitre(new CommandeTitre(this)) ;
		this.modele = modele ;
	}
	
	

	@Override
	public void suivant() {
		// TODO Auto-generated method stub
		graphique.suivant();
	}

	@Override
	public void precedent() {
		// TODO Auto-generated method stub
		graphique.precedent();
	}

	@Override
	public void selection() {
		selection = graphique.selection() ;
		System.out.println("Selection:"+selection.getIntitule());
	}

	public void modifierSelection()
	{
		int indexModifie = modele.getChamps().indexOf(selection) ;
		ArrayList<Champ> champListe = new ArrayList(modele.getChamps()) ;
		
		Champ unChamp = new Champ() ;
		unChamp.chargerChamp(selection);
		unChamp.chargerChamp(graphique.getChampModifie() );
		champListe.set(indexModifie,unChamp) ;
		
		modele.setChamps(champListe);
		
		modele.mettreAJour(); 
		System.out.println("Modification:"+unChamp);
	}
	
	public void ajouter()
	{
		ArrayList<Champ> champs = new ArrayList<Champ>(modele.getChamps()) ;
		champs.add(new Champ("nouveau", "nouveau", modele.getTotal()/10)) ;
		modele.setChamps(champs);
	}



	@Override
	public void modifierTitre() {
		modele.setTitre(graphique.getTitre());
	}

}
