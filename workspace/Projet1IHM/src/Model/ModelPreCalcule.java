package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

import tool.MyColor;


/*
 * Model utilisé par le programme il garde en mémoire tout les couleurs et l'indice de la couleur selectionné
 * les couleur sont trié par leur "hue" en anglais la teinte (0° - 360°) de la représentation HSB 
 */
public class ModelPreCalcule extends Observable{

	private ArrayList<MyColor>  couleurs;
	private int indice;
	
	public ModelPreCalcule(ArrayList<MyColor> couleurs){
		indice=0;
		this.couleurs=couleurs;
	}
	
	public Color getCouleur() {
		return couleurs.get(indice);
	}
	public void setCouleur(int i) {
		this.indice = i;
		setChanged();
		notifyObservers(couleurs.get(indice));
	}
	
}
