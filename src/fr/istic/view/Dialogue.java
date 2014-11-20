package fr.istic.view;

import javax.swing.JOptionPane;

import fr.istic.main.Main;

public  class Dialogue {

	
	static String RecupererIntitule()
	{
		return dialoguer("Entrez l'intitulé :") ;
	}
	
	static String recupererDescription()
	{
		return dialoguer("Entrez la description :") ;
	}
	
	static String recupererTitre() 
	{
		return dialoguer("Entrez le titre :") ;
	}
	
	static double recupererValeur() 
	{
		try{
			return Double.parseDouble(dialoguer("Entrez la valeur :")) ;
		}
		catch(Exception e)
		{
			return (Double) null ;
		}
	}
	
	/*
	 * Méthode qui permet d'afficher une fenêtre qui permet de récupérer une valeur
	 */
	static String dialoguer(String message)
	{
		String pseudo = JOptionPane.showInputDialog(
                Main.fenetre, message,  JOptionPane.OK_OPTION
        );
        //if (pseudo == null) System.exit(0);
        return pseudo ;
	}
	
	public static void info()
	{
		JOptionPane.showMessageDialog(Main.fenetre,"Cliquez sur les rectangles (après sélection d'un arc de cercle) et sur le cercle du milieu pour modifier les valeurs") ;
        //if (pseudo == null) System.exit(0);
	}
}
