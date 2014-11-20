package fr.istic.view;

import java.awt.event.MouseListener;

import fr.istic.command.Command;
import fr.istic.model.Champ;
import fr.istic.model.Observer;

public interface IGraphique extends Observer, MouseListener {
	
	public void precedent() ;
	public void suivant() ;
	
	public Champ  getChampModifie() ;	
	public void setCommandeSelection(Command commande) ;	
	public Champ selection() ;
	public void setCommandeModification(Command commande) ;
	public void setCommandeModificationTitre(Command commande) ;
	public String getTitre() ;
	
	public void debutAngle(double debut) ;
}
