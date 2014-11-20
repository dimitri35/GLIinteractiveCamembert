package fr.istic.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class EcouteurDrag implements MouseMotionListener {

	private Graphique  graphique ;
	
	public EcouteurDrag(Graphique graphique) {
		this.graphique = graphique ;
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		int x = arg0.getX() ;
		int y = arg0.getY() ;
		//calcul de la distance en x par rapport au centre
		int centreRotationX  = graphique.xCentre + graphique.tailleArc/2 ;
		//calcul de la distance en y par rapport au centre
		int centreRotationY  = graphique.yCentre + graphique.tailleArc/2 ;
		
		//Tangente/Coté opposé = coté adjacent 
		//= adjacent * opposé  = tangente
		//angle = arcTan(adjacent * opposé )
		double angle = Math.atan(centreRotationX * centreRotationY ) ;
		//Math.cos(x-centreRotationX) ;
		graphique.debutAngle(angle) ;
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

}
