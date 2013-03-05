package IUT2;

import java.util.Observable;

public class CompteurModele extends Observable {
	
	// Lieu unique de stockage de la valeur du compteur
	protected int value;
	
	public CompteurModele(){set(0);}
	
	// Methodes pour modifier l'etat du modele, qui sont sollicitees par les controleurs
	public void inc(){ set(value+1);}
	public void dec(){ set(value-1);}
	
	// A chaque changement de valeur,
	// on met a vrai le drapeau "il y a eu du changement", et on previent les observateurs
	public void set(int i){this.value=i; this.setChanged(); this.notifyObservers();}

	// Methode pour lire l'etat du modele, sollicitee par la vue (a chaque "update")
	public int get(){return value;}
	public String toString(){return ""+value;}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CompteurModele m = new CompteurModele();
		System.out.println(m);
		m.inc();
		System.out.println(m);
		m.dec(); m.dec();
		System.out.println(m);
		m.set(5);
		System.out.println(m);
		
	}

}
