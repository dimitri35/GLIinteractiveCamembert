package fr.istic.main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.istic.command.CommandAjout;
import fr.istic.command.CommandPrecedent;
import fr.istic.command.CommandSuivant;
import fr.istic.controller.Controller;
import fr.istic.model.Champ;
import fr.istic.model.IModele;
import fr.istic.model.Modele;
import fr.istic.model.Observable;
import fr.istic.view.Dialogue;
import fr.istic.view.Fenetre;
import fr.istic.view.Graphique;

public class Main {

	public static Fenetre fenetre ;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Champ> champs = new ArrayList<Champ>() ;
		champs.add(new Champ("Loyer","dépense moyen de loyer en euros",300.0)) ;
		champs.add(new Champ("Alimentation","dépense moyen d'alimentation en euros",200.0)) ;
		champs.add(new Champ("Cinema","dépense moyen pour le cinéma en euros",20.0)) ;
		champs.add(new Champ("Mutuelle","dépense moyen en mutuelle en euros",50.0)) ;
		IModele modele = new Modele("test",champs) ;
		
		Graphique graphique1 = new Graphique(modele) ;
		final Controller controller = new Controller(graphique1,modele) ;
		
		Observable obs = (Observable) modele  ;
		obs.addObserver(graphique1);
		
		fenetre = new Fenetre(graphique1) ;
		fenetre.add(graphique1,BorderLayout.CENTER) ;
		Button bouton = new Button("Précedent") ;
		bouton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				new CommandPrecedent(controller).execute() ;
			}
		});
		fenetre.add(bouton,BorderLayout.NORTH) ;
		
		
		Button bouton2 = new Button("Suivant") ;
		bouton2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new CommandSuivant(controller).execute() ;
			}
		}); 
		fenetre.add(bouton2,BorderLayout.SOUTH) ;
		
		Button boutonAdd = new Button("+") ;
		boutonAdd.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new CommandAjout(controller).execute(); 
			}
		});
		fenetre.add(boutonAdd,BorderLayout.EAST) ;
		
		Dialogue.info() ;
		/*
		JPanel jPan = new JPanel() ;
		jPan.add(graphique1) ;
		
		
		Button bouton = new Button("Précedent") ;
		Button bouton2 = new Button("Suivant") ;
		jPan.add(bouton,BorderLayout.NORTH) ;
		
		//On positionne maintenant ces trois lignes en colonne
		jPan.add(bouton2,BorderLayout.SOUTH);
		*/
		
		/*
		fenetre.getContentPane().add(graphique1,BorderLayout.CENTER) ;
		fenetre.getContentPane().add(bouton,BorderLayout.SOUTH) ;
		fenetre.getContentPane().add(bouton2,BorderLayout.SOUTH) ;
		*/
		
		//fenetre.add(bouton) ;
		//fenetre.ajouterComposant(graphique1);
		//fenetre.ajouterComposant(bouton);
		//fenetre.ajouterComposant(bouton2);
	}

}
