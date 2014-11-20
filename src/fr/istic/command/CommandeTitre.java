package fr.istic.command;

import fr.istic.controller.Controller;
import fr.istic.controller.IController;

public class CommandeTitre implements Command{

	private IController controleur ;
	
	public CommandeTitre(IController control) {
		this.controleur = control ;
	}
	@Override
	public void execute() {
		controleur.modifierTitre();
	}

	
}
