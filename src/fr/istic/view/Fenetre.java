package fr.istic.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame{

	public JPanel jPan ;
	
	public Fenetre(Graphique graphique) {
		this.setTitle("Ma première fenêtre Java");
	    this.setSize(1200, 1200);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	    this.setVisible(true);
	    add(graphique) ;
	 
	}
	
	public void ajouterComposant(Component comp)
	{
		jPan.add(comp) ;
	}
}
