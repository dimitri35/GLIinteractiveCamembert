package fr.istic.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.lang.ProcessBuilder.Redirect;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import javax.sound.sampled.Line;
import javax.swing.JComponent;
import javax.swing.Box.Filler;
import javax.swing.JOptionPane;

import fr.istic.command.Command;
import fr.istic.main.Main;
import fr.istic.model.Champ;
import fr.istic.model.IModele;
import fr.istic.model.Observable;
import fr.istic.model.Observer;

public class Graphique extends JComponent implements IGraphique {

	private IModele modele ;
	
	private Command commandeSelection,commandeModification, commandeTitre ;
	
	private Champ champModifie ; 
	private String titre ;
	
	private Champ champSelectionne;
	
	private Rectangle2D.Double rectangleIntitule, rectangleDescription, rectangleValeur ;
	
	private HashMap<Champ,Arc2D.Double> listeArc ;
	private ArrayList<Arc2D.Double> tableauArc ;
	private Arc2D.Double  dernierArcSelectionne ;
	
	private int indiceElement = -1;
	
	
	public final int xCentre = 250,yCentre = 190,tailleArcSelection = 480, tailleArc = 400 ;
	public final int tailleLigneIntitule = 140;
	public final int hauteurRectangle = 20, largeurRectangle = 250 ;
	
	private double debutCamembertAngle = 0 ;
	
	public Graphique(IModele modele) {
		this.modele = modele ;
		listeArc = new HashMap<Champ,Arc2D.Double>() ;
		tableauArc = new ArrayList<Arc2D.Double>() ;
		addMouseListener(this);
		addMouseMotionListener(new EcouteurDrag(this));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//g.setColor(Color.blue);
		//g.fillOval(0, 0,400,400);	
		
		initialiserRectangle(g) ;
		//On récupère la valeur total de tous les champs
		double valeurTotal = modele.getTotal() ;
		
		Arc2D.Double monArc ;
		
		//La liste des couleurs a utiliser pour les arcs
		Color[] couleurs = {new Color(145, 164, 55),new Color(111, 37, 111),new Color(152, 51, 82),new Color(96, 151, 50)} ;
		double debut = debutCamembertAngle, debutRatio = 0, degreArc, ratio ;
		int i=0 ;
		//Pour chaque champs dans le modèle
		for(Champ unChamp: modele.getChamps())
		{
			//On change la couleur à utiliser pour l'arc 
			g.setColor(couleurs[i%couleurs.length]); 
			//calculer du ratio par rapport à 360 de l'arc
			ratio = (unChamp.getValeur()/valeurTotal) ;
			//calcul du degré de l'arc
			degreArc = ratio*360 ;
			//On essaye de récupérer l'arc si il a déjà été généré
			monArc = recupererArc(unChamp) ;
			
			//Si l'arc n'a pas été généré
			if(monArc == null)
			{
				//On crée l'arc en tant que non selectionné
				monArc = new Arc2D.Double(xCentre,yCentre,tailleArc,tailleArc,debut,degreArc,Arc2D.PIE) ;
				//On ajoute l'arc à la liste des arcs disponibles
				listeArc.put(unChamp,monArc) ;
				tableauArc.add(monArc) ;
			}
			//Si l'arc correspond au dernier arc mis en valeur
			if(monArc.equals(dernierArcSelectionne) )
			{
				//On récupère la valeur du champ correpondante
				champSelectionne = unChamp ;
			}
			//On redessinne l'arc
			((Graphics2D) g).fill(monArc);		
			
			//affichageLigneQuartier(g,debutRatio) ;
			afficheLigneIntitule(g, debut, degreArc,unChamp.getIntitule());
			
			//On augmente le début de l'arc
			debutRatio+=ratio ;
			System.out.println("debutRatio"+debutRatio);
			debut += degreArc ;
			i++ ;

		}
		
		//monArc = new Arc2D.Double(0,0,400,400,0,45,Arc2D.PIE) ;
		//((Graphics2D) g).fill(monArc);
		
		//Affichage du camembert sans les différents arcs correpondants aux champs
		affichageCamembertArrPlan(g, valeurTotal);
		
		if(champSelectionne!=null)
			afficherDescValeur(g,champSelectionne);
		
		super.paintComponent(g);

	}
	
	/*
	private void affichageLigneQuartier(Graphics g,double debut)
	{
		Line2D.Double ligne ;
		g.setColor(Color.black);
		ligne = new Line2D.Double(xCentre+tailleArc/2,yCentre+tailleArc/2,xCentre+tailleArc/2+Math.cos(2.0*Math.PI*debut)*220,yCentre+tailleArc/2+Math.sin(2.0*Math.PI*debut)*220) ;
		((Graphics2D) g).draw(ligne);
	}
	*/
	
	/*
	 *  Affiche l'intutilé du champ
	 */
	private void afficheLigneIntitule(Graphics g, double debut, double degreArc,String intitule)
	{
		//affichage du trait 
		Arc2D.Double monArc = new Arc2D.Double(xCentre-tailleLigneIntitule/2,yCentre-tailleLigneIntitule/2,tailleArc+tailleLigneIntitule,tailleArc+tailleLigneIntitule,debut,1,Arc2D.PIE) ;
		int centreX = tailleArc/2+xCentre ;
		int centreY = tailleArc/2+yCentre ;
		
		//Calcul des coordonnées des rectangles à afficher
		float ratio = (float) (tailleArc+tailleLigneIntitule)/(float) tailleArc ;
		double debutRadian = debut/180.0*Math.PI ;
		int x = (int) (Math.cos(debutRadian)*ratio*tailleArc/2) ;
		int y = (int) (Math.sin(debutRadian)*ratio*tailleArc/2) ;
		
		((Graphics2D) g).fill(monArc);	
		
		//calcul des cosinus et sinus des angles
		int hauteurRect = 25, longueurRect = 150 ;
		double cos = Math.cos(debutRadian) ;
		double sin = Math.sin(debutRadian) ;
		
		//On change le coté du rectangle en fonction de l'angle
		if(sin>0)
			y-=hauteurRect-5 ;

		if(cos<0)
			x-=longueurRect ;
			
		//On mets une couleur plus sombre pour le rectangle
		g.setColor(g.getColor().darker());
		g.fill3DRect(centreX+x, centreY-y-20,longueurRect,hauteurRect, true);
		
		g.setColor(Color.white);
		//affichage de l'intitulé
		g.drawString(intitule, centreX+x, centreY-y);
		
	}
	
	/*
	 * Permet d'afficher les ovales d'arrière plan du camembert 
	 */
	private void affichageCamembertArrPlan(Graphics g, double valeurTotal)
	{
		g.setColor(Color.white);
		g.fillOval(xCentre+50,50+yCentre,300,300);
		g.setColor(Color.gray);
		g.fillOval(100+xCentre,100+yCentre, 200, 200);
		
		g.setColor(Color.white);
		g.drawString(modele.getTitre()+":"+valeurTotal,tailleArc/2+xCentre,tailleArc/2+yCentre);
	}
	/* 
	 * Permet d'afficher la description et la valeur du champ mis en valeur
	 */
	private void afficherDescValeur(Graphics g,Champ unChamp)
	{
		g.setColor(Color.BLACK) ;
		

		
		Graphics2D g2d = (Graphics2D) g ;
		
		//Affichage de l'intitulé 
		g.drawString(unChamp.getIntitule(),100+10,20+hauteurRectangle);
		
		//Affichage de la description
		g.drawString(unChamp.getDescription(),400+10,20+hauteurRectangle);
		
		//Affichage de la valeur
		g.drawString("Valeur:"+unChamp.getValeur(),700+10,20+hauteurRectangle);
	}
	
	private void obtenirArcClic(int x, int y)
	{
	  Set<Entry<Champ, Arc2D.Double>> entree = listeArc.entrySet() ;
	  //Pour chaque arc
	  for(Entry<Champ,Arc2D.Double> uneEntree :entree)
	  {	  
		  Arc2D.Double arc = uneEntree.getValue() ;
		  //Si l'arc contient les coordonnées
		  if(arc.contains(x, y))
		  {
			  //On récupère l'indice de l'arc dans la hashMap
			  indiceElement = tableauArc.indexOf(arc) ;
			  
			  /*
			  if(dernierArcSelectionne!=null)
			  {
				  dernierArcSelectionne.width = tailleArc ;
				  dernierArcSelectionne.height = tailleArc ;
				  dernierArcSelectionne.x = xCentre ;
				  dernierArcSelectionne.y = yCentre ;
			  }
			  arc.width = tailleArcSelection ;
			  arc.height = tailleArcSelection ;
			  int decalage = tailleArcSelection-tailleArc ;
			  arc.x = xCentre- decalage/2;
			  arc.y = yCentre - decalage/2;
		  
			  dernierArcSelectionne = arc ;
			  
			  repaint();
			  */
			  
			  //On met en valeur l'arc correspondant
			  selectionnerChamp(arc);
			  
		  }
		  
	  }
	  
	  
	}
	
	public void suivant() 
	{
		//On incrémente l'indice du dernier arc selectionné modulo le nombre d'arcs
		indiceElement = (indiceElement+1)%tableauArc.size() ;
		System.out.println("indiceElement:"+indiceElement) ;
		//On met en valeur l'arc correspondant
		selectionnerChamp(tableauArc.get(indiceElement)) ;
	}
	
	public void precedent() 
	{
		//On décrémente l'indice du dernier arc selectionné modulo le nombre d'arcs
		indiceElement = (indiceElement-1)%tableauArc.size() ;
		if(indiceElement<0)
			indiceElement = tableauArc.size()-1 ;
		System.out.println("indiceElement:"+indiceElement) ;
		//On met en valeur l'arc correspondant
		selectionnerChamp(tableauArc.get(indiceElement)) ;
	}
	
	private void selectionnerChamp(Arc2D.Double arc)
	{
		 //On ne mets plus en valeur l'arc qui a été selectionné en avant l'arc que l'on veut mettre en valeur
		 if(dernierArcSelectionne!=null)
		  {
			  dernierArcSelectionne.width = tailleArc ;
			  dernierArcSelectionne.height = tailleArc ;
			  dernierArcSelectionne.x = xCentre ;
			  dernierArcSelectionne.y = yCentre ;
		  }
		 
		  //On met en valeur l'arc selectionné en agrandissant sa taille
		  arc.width = tailleArcSelection ;
		  arc.height = tailleArcSelection ;
		  int decalage = tailleArcSelection-tailleArc ;
		  arc.x = xCentre- decalage/2;
		  arc.y = yCentre - decalage/2;
	  
		  dernierArcSelectionne = arc ;		
		  
		  //On dit au controleur qu'on a sélectionné
		  commandeSelection.execute();
		  //On demande à  redessiner le graphique
		  repaint();
	}
	
	public  Arc2D.Double recupererArc(Champ unChamp)
	{	
		return listeArc.get(unChamp);
	}
	
	@Override
	public void update(Observable obs) {
		System.out.println("mise à jour");
		reInit(); 
		repaint() ;
	}
	
	private void initialiserRectangle(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g ;
		
		//Rectnagle pour l'affichage de l'intitulé 
		rectangleIntitule = new Rectangle2D.Double(100,20,largeurRectangle,hauteurRectangle) ;
		//g.drawRect(100,20,largeurRectangle,hauteurRectangle); 
		g2d.draw(rectangleIntitule);	
		
		//Rectangle pour l'affichage de la description
		rectangleDescription = new Rectangle2D.Double(400,20,largeurRectangle,hauteurRectangle) ;
		//g.drawRect(400,20,largeurRectangle,hauteurRectangle);
		g2d.draw(rectangleDescription);	
		
		//Rectangle pour l'affichage de la valeur
		rectangleValeur = new Rectangle2D.Double(700,20,largeurRectangle,hauteurRectangle) ;
		//g.drawRect(700,20,largeurRectangle,hauteurRectangle);
		g2d.draw(rectangleValeur);
		
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int x = arg0.getX() ; int y = arg0.getY() ;
		champModifie = new Champ() ;
		boolean modif = false ;
		//Si on clic dans le rectangle de la description
		if(rectangleDescription.contains(x, y))
		{
			modif = true ;
			champModifie.setDescription(Dialogue.recupererDescription()) ;
		}
		//Si on clic dans le rectangle de l'intitulé
		else if(rectangleIntitule.contains(x,y))
		{
			modif = true ;
			champModifie.setIntitule(Dialogue.RecupererIntitule()) ;
		}
		//Si on clic dans le rectangle de la valeur
		else if(rectangleValeur.contains(x,y))
		{
			modif = true ;
			champModifie.setValeur(Dialogue.recupererValeur() );
		}
		
		//calcul de la distance en x du centre du cercle
		int centreRotationX  = xCentre + tailleArc/2 ;
		//calcul de la distance en y du centre du cercle du centre
		int centreRotationY  = yCentre + tailleArc/2 ;
		int carre = (x-centreRotationX)*(x-centreRotationX)+(y-centreRotationY)*(y-centreRotationY)  ;
		//Si on clic dans le cercle du milieu
		if(carre < 150*150)
		{
			System.out.println("centre:"+carre);
			titre = Dialogue.recupererTitre() ;
			commandeTitre.execute(); 
		}
		else{
			obtenirArcClic(x,y);
		}
		
		//Si on a modifié un champ on éxécute la commande pour changer le champ
		if(modif)
		{
			commandeModification.execute();
		}
		
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
	
	public Champ selection()
	{
		for(Champ unChamp: modele.getChamps())
		{
			if(listeArc.get(unChamp) == dernierArcSelectionne)
			{
				return unChamp ;
			}
		}
		return null ;
	}
	
	public Champ  getChampModifie()
	{
		return champModifie;
		
	}
	
	public void setCommandeSelection(Command commande)
	{
		commandeSelection = commande ;
	}
	
	public void setCommandeModification(Command commande)
	{
		commandeModification = commande ;
	}
	
	public void debutAngle(double debut)
	{
		//modification de l'angle de début du camembert
		debutCamembertAngle += debut ;
		reInit() ;
		repaint() ;
	}
	
	private void reInit()
	{
		//Remise à zéro de la liste des arcs 
		listeArc = new HashMap<Champ,Arc2D.Double>() ;
		tableauArc = new ArrayList<Arc2D.Double>() ;
		indiceElement = -1 ;
		//Remise à zéro des champs sélectionné et des champs modifié
		champSelectionne = null ;
		champModifie = null ;
	}

	@Override
	public void setCommandeModificationTitre(Command commande) {
		commandeTitre = commande ;
	}

	@Override
	public String getTitre() {
		return titre ;
	}
}
