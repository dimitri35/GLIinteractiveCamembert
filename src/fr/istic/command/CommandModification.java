package fr.istic.command;

import fr.istic.controller.IController;

public class CommandModification implements Command{

	private IController controleur ;
	
	public CommandModification(IController controleur) {
		this.controleur = controleur ;
	}
	@Override
	public void execute() {
		controleur.modifierSelection();
	}
	
}
