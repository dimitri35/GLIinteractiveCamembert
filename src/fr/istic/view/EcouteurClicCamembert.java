package fr.istic.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.istic.model.Champ;

public class EcouteurClicCamembert implements MouseListener{

	private Graphique  graphique ;
	
	public EcouteurClicCamembert(Graphique graphique) {
		this.graphique = graphique ;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		/*
		int x = arg0.getX() ; int y = arg0.getY() ;
		champModifie = new Champ() ;
		boolean modif = false ;
		if(rectangleDescription.contains(x, y))
		{
			modif = true ;
			champModifie.setDescription(Dialogue.recupererDescription()) ;
		}
		else if(rectangleIntitule.contains(x,y))
		{
			modif = true ;
			champModifie.setIntitule(Dialogue.RecupererIntitule()) ;
		}
		else if(rectangleValeur.contains(x,y))
		{
			modif = true ;
			champModifie.setValeur(Dialogue.recupererValeur() );
		}
		else{
			obtenirArcClic(x,y);
		}
		
		if(modif)
		{
			commandeModification.execute();
		}
		*/
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	
}
