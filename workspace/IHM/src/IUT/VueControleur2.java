package IUT;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VueControleur2 extends JPanel implements Observer{

	private Model model;
	JSlider bleu,rouge,vert;
	
	public VueControleur2(Model m){
		model=m;
		model.addObserver(this);
		bleu=new JSlider(0, 100, model.getB());
		rouge=new JSlider(0, 100, model.getR());
		vert=new JSlider(0,100,model.getV());
		Label rougeLab=new Label("Rouge");
		Label bleuLab=new Label("Bleu");
		Label vertLab=new Label("Vert");
		this.setLayout(new GridLayout(3,2));
		this.add(rougeLab);
		this.add(rouge);
		this.add(vertLab);
		this.add(vert);
		this.add(bleuLab);
		this.add(bleu);
		
		bleu.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				model.set(2,bleu.getValue());
				//System.out.println("ChangeListener Bleu   "+bleu.getValue());
				
			}
		});
		
		rouge.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
			model.set(0,rouge.getValue());	
			}
		});
		
		vert.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
			model.set(1,vert.getValue());	
			}
		});
	}
	
		
	
	public Dimension getPreferredSize() {
		return new Dimension(300, 70);
	}
	
	
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		vert.setValue(model.getV());
		bleu.setValue(model.getB());
		rouge.setValue(model.getR());
		
		//System.out.println("vueC n°2");
		
		
	}
	
	
	
}
