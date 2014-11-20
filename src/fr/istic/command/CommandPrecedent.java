package fr.istic.command;

import fr.istic.controller.Controller;
import fr.istic.controller.IController;

public class CommandPrecedent {

	private	IController controleur ;
	
	public CommandPrecedent(IController controleur) {
		this.controleur = controleur;
	}
	
	public void execute()
	{
		controleur.precedent();
	}
}
