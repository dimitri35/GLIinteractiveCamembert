package fr.istic.model;

public interface Observable {

	public void addObserver(Observer obs) ;
	public void notifyObservers() ;
}
