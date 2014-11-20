package fr.istic.command;

import fr.istic.controller.IController;

public class CommandSelection implements Command{

	private IController controller ;
	
	public CommandSelection(IController iController)
	{
		controller = iController ;
	}
	
	@Override
	public void execute() {
		controller.selection(); 
	}

	
}
