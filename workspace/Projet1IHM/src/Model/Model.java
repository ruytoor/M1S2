package Model;

import java.awt.Color;
import java.util.Observable;

public class Model extends Observable{

	private int numero;
	private int nbMax;
	private Color couleur;
	
	public Model(int numero,int nbMax){
		this.numero=numero;
		this.nbMax=nbMax;
		couleur=new Color(numero*255/(nbMax-1),numero*255/(nbMax-1),numero*255/(nbMax-1));
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
		setChanged();
		notifyObservers(couleur);
	}

	protected void reNotifyObservers(){
		setChanged();
		notifyObservers(couleur);
	}
}
