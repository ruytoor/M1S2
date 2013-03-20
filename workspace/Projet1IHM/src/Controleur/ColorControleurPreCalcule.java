package Controleur;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Model.ModelPreCalcule;

public class ColorControleurPreCalcule extends JPanel {//implements Observer{

	ModelPreCalcule colorModel;
	JSlider slide;
	static Random r=new Random();
	private int nbMax;
	public ColorControleurPreCalcule(int nbMax,ModelPreCalcule m){
		this.nbMax=nbMax;
		colorModel = m;
		slide=new JSlider(JSlider.HORIZONTAL, 0, nbMax, 0);//m.getCouleur().getRed());
		slide.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				colorModel.setCouleur(slide.getValue());
			}
		});
		this.setLayout(new BorderLayout());
		this.add(new JLabel("couleur :"),BorderLayout.NORTH);
		this.add(slide,BorderLayout.CENTER);

	}
	public void setRandom(){
		slide.setValue(r.nextInt(nbMax));
		colorModel.setCouleur(slide.getValue());
	}
}
