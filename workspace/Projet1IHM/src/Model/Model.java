package Model;

import java.awt.Color;
import java.util.Observable;

public class Model extends Observable{

	private int nivGris;
	private int r,g,b;
	private Color couleur;
	

	public Model(int numero,int nbMax){
		r=g=b=numero*255/(nbMax-1);
		nivGris=r;
		couleur=new Color(r,g,b);
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(int r,int g,int b) {
		this.couleur = new Color(r, g, b);
		setChanged();
		notifyObservers(couleur);
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
	
	public int getnivGris(){
		return nivGris;
	}
	public void setRouge(int value) {
		//NiveauGris = 0.3   Rouge + 0.59   Vert + 0.11   Bleu
		if(nivGris!=0){
			if(nivGris<value*0.3){
				setCouleur((int)(nivGris/0.3), 0, 0);
			}

			int tmp=nivGris-(int)(b*0.11);
			tmp-=(int)(value*0.3);
			if(tmp<0){
				setCouleur(value, 0, (int)(nivGris-(value*0.3)));
			}else{
				setCouleur(value, tmp, b);
			}
		}
	}
	public void setVert(int value) {
		//NiveauGris = 0.3   Rouge + 0.59   Vert + 0.11   Bleu
		if(nivGris!=0){
			if(nivGris<value*0.59){
				setCouleur(0, (int)(nivGris/0.59), 0);
			}

			int tmp=nivGris-(int)(r*0.3);
			tmp-=(int)(value*0.11);
			if(tmp<0){
				setCouleur((int)(nivGris-(value*0.59)), value,0 );
			}else{
				setCouleur(r, value, tmp);
			}
		}
	}
	public void setBleu(int value) {
		//NiveauGris = 0.3   Rouge + 0.59   Vert + 0.11   Bleu
		if(nivGris!=0){
			if(nivGris<value*0.11){
				setCouleur(0, 0, (int)(nivGris/0.11));
			}

			int tmp=nivGris-(int)(g*0.59);
			tmp-=(int)(value*0.59);
			if(tmp<0){
				setCouleur(0, (int)(nivGris-(value*0.11)), value);
			}else{
				setCouleur(tmp, g, value);
			}
		}
	}
}
