package tool;

import java.awt.Color;

/*
 * MyColor est comme la classe Color mais elle est comparable
 * la comparaison se fait sur la tainte ("hue" en anglais de la reprÃ©sentation HSB de la couleur)
 * 
 */
/**
 * classe qui extends Color et implémente Comparable
 * la comparaison se fait sur la teinte ("hue" en anglais, vient de la représentation HSB de la couleur)
 * 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 21 mars 2013
 */
public class MyColor extends Color implements Comparable<MyColor>{

	public MyColor(int r, int g, int b) {
		super(r, g, b);
	}


	/**
	 * compare la teinte de la couleur this et la couleur passee en parametre
	 * @param o la couleur a comparer
	 * @return la différence entre les 2 teintes
	 */
	@Override
	public int compareTo(MyColor o) {
		float t[]=new float[3];
		float t2[]=new float[3];
		Color.RGBtoHSB(this.getRed(), this.getGreen(), this.getBlue(), t);
		Color.RGBtoHSB(o.getRed(), o.getGreen(), o.getBlue(), t2);
		return (int) ((t[0]*3600+t[1]*100)-(t2[0]*3600+t2[1]*100));
	}

}
