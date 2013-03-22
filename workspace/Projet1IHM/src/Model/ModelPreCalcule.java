package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

import tool.MyColor;

/**
 * classe qui contient toutes les couleurs et l'indice de la couleur selectionne.
 * Les couleurs sont triees par leur teinte de la représentation HSB
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 21 mars 2013
 */
public class ModelPreCalcule extends Observable{

	private ArrayList<MyColor>  couleurs;
	private int indice;
	
	public ModelPreCalcule(ArrayList<MyColor> couleurs){
		indice=0;
		this.couleurs=couleurs;
	}
	
	/**
	 * retourne la couleur de la couleur de l'indice (this)
	 * @return la couleur 
	 */
	public Color getCouleur() {
		return couleurs.get(indice);
	}
	
	/**
	 * modifie l'indice (this)
	 * @param i le nouvel indice
	 */
	public void setCouleur(int i) {
		this.indice = i;
		setChanged();
		notifyObservers(couleurs.get(indice));
	}
	
}
