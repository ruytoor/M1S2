package tool;

import java.awt.Color;


/*
 * MyColor est comme la classe Color mais elle est comparable
 * la comparaison se fait sur la tainte ("hue" en anglais de la représentation HSB de la couleur)
 * 
 */
public class MyColor extends Color implements Comparable<MyColor>{

	public MyColor(int r, int g, int b) {
		super(r, g, b);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(MyColor o) {
		float t[]=new float[3];
		float t2[]=new float[3];
		Color.RGBtoHSB(this.getRed(), this.getGreen(), this.getBlue(), t);
		Color.RGBtoHSB(o.getRed(), o.getGreen(), o.getBlue(), t2);
		return (int) ((t[0]*3600)-(t2[0]*3600));
		//return (this.getRed()+this.getGreen()*100000+this.getBlue()*1000)-(o.getRed()+o.getGreen()*100000+o.getBlue()*1000);
	}

}
