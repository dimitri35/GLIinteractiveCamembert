package fr.istic.command;

import fr.istic.controller.IController;

public class CommandSuivant {

private	IController controleur ;
	
	public CommandSuivant(IController controleur) {
		this.controleur = controleur;
	}
	
	public void execute()
	{
		controleur.suivant();
	}
}
