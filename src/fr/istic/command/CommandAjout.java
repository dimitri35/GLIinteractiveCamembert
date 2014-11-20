package fr.istic.command;

import fr.istic.controller.IController;

public class CommandAjout implements Command{

	private IController controleur ;
	
	public CommandAjout(IController controleur) {
		this.controleur = controleur ;
	}
	@Override
	public void execute() {
		controleur.ajouter();
	}

	
}
