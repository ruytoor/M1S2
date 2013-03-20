package Controleur;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Model.Model;


/*
 * cette classe n'est pas utiliser et ne sera pas commenté
 * elle est uniquement présente pour représenté le temps qu'on a mit sur le projet
 * 
 * 
 * fonctione mais peu mieux faire
 */
@Deprecated
public class ColorControleurHSV  extends JPanel implements Observer{

	Model m;
	static final Random random=new Random();
	 JSlider saturation;
	  JSlider couleur;
	
	public ColorControleurHSV(final Model m){
		this.m=m;
		couleur=new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
		saturation=new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		
		this.setLayout(new GridLayout(4, 1));
		this.add(new Label("couleur"));
		this.add(couleur);
		this.add(new Label("saturation"));
		this.add(saturation);
		
		couleur.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				m.setTSV((couleur.getValue()/360.f), saturation.getValue()/100.0f);
			}
		});
		
		saturation.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				m.setTSV((couleur.getValue()/360.f), saturation.getValue()/100.0f);
			}
		});
	}

	public void randomColor() {
		// TODO Auto-generated method stub
		saturation.setValue(80);
		couleur.setValue((int)(random.nextFloat()*100));
		m.setTSV(couleur.getValue()/360.f, saturation.getValue()/100.f);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		Color c= (Color)(arg1);
		float []t=new float[3];
		Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), t);
		System.out.println(t[0]+" "+t[1]+" "+t[2]);
		saturation.setValue((int)(t[1]*100));
	}
}

