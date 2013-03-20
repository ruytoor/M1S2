package Controleur;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Model.Model;

public class ColorControleur extends JPanel implements Observer{

	private Model colorModel;
	
	private JSlider rouge;
	private JSlider bleu;
	private JSlider vert;
	
	public ColorControleur(Model m){
		
		colorModel=m;
		rouge=new JSlider(JSlider.HORIZONTAL, 0, (int)(m.getnivGris()/0.3), 0);//m.getCouleur().getRed());
		vert=new JSlider(JSlider.HORIZONTAL, 0,(int)(m.getnivGris()/0.59) , 0);//m.getCouleur().getGreen());
		bleu=new JSlider(JSlider.HORIZONTAL, 0, (int)(m.getnivGris()/0.11), 0);//m.getCouleur().getBlue());
		/*
		vert.setModel(new DefaultBoundedRangeModel(colorModel.getCouleur().getGreen(), 1, 0, 255));
		rouge.setModel(new DefaultBoundedRangeModel(colorModel.getCouleur().getRed(), 1, 0, 255));
		bleu.setModel(new DefaultBoundedRangeModel(colorModel.getCouleur().getBlue(), 1, 0, 255));
		*/
		this.setLayout(new GridLayout(3, 1));
		this.add(rouge);
		this.add(vert);
		this.add(bleu);

		rouge.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				colorModel.setRouge(rouge.getValue());
				
			}
		});

		vert.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				colorModel.setVert(vert.getValue());
				
			}
		});

		bleu.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				colorModel.setBleu(bleu.getValue());
				
			}
		});
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Color c=(Color)(arg1);
		System.out.println(c.getRed()+" "+c.getGreen()+" "+c.getBlue()+" ="+(int)((c.getGreen()*0.59)+(c.getBlue()*0.11)+(c.getRed()*0.3)));
		/*
		vert.getModel().setValue(c.getGreen());
		rouge.getModel().setValue(c.getRed());
		bleu.getModel().setValue(c.getBlue());
		*/
		/*
		rouge.setValue(c.getRed());
		vert.setValue(c.getGreen());
		bleu.setValue(c.getBlue());
		*/
	}
	
	
}
