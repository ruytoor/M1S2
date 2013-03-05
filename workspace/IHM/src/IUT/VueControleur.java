package IUT;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueControleur extends JPanel implements Observer  {
	private Model model;
	int r,v,b;
	JTextField rouge,vert,bleu;
	

	public VueControleur(Model m){
		model=m;
		model.addObserver(this);
		v=model.getV();
		b=model.getB();
		r=model.getR();
		rouge=new JTextField(""+r,3);
		vert=new JTextField(""+v,3);
		bleu=new JTextField(""+b,3);
		Label rougeLab=new Label("Rouge");
		Label bleuLab=new Label("Bleu");
		Label vertLab=new Label("Vert");
		this.add(rougeLab);
		this.add(rouge);
		this.add(vertLab);
		this.add(vert);
		this.add(bleuLab);
		this.add(bleu);
		TextAction al=new TextAction();
		rouge.addActionListener(al);
		vert.addActionListener(al);
		bleu.addActionListener(al);
		
		
	}
	public Dimension getPreferredSize() {
		return new Dimension(300, 30);
	}
	
	public void update(Observable ob, Object o){
		rouge.setText(""+model.getR());
		bleu.setText(""+model.getB());
		vert.setText(""+model.getV());
		//System.out.println("update VueC n°1");
	}
	
	
	class TextAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==rouge){
				model.set(0, Integer.parseInt(rouge.getText()));
			}
			if(e.getSource()==vert){
				model.set(1, Integer.parseInt(vert.getText()));
			}
			if(e.getSource()==bleu){
				model.set(2, Integer.parseInt(bleu.getText()));
			}
			
		}
		
	}
	
}
