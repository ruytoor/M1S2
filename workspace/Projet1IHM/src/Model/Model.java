package Model;

import java.awt.Color;
import java.util.Observable;

public class Model extends Observable{

	private int numero;
	private int nbMax;
	
	public Model(int numero,int nbMax){
		this.numero=numero;
		this.nbMax=nbMax;
	}
	
	private Color couleur;

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	
	

}
