package view;

import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.TemperatureModel;
import controller.TemperatureController;

public class TemperatureVueSlider implements Observer  {

	private String label;
	private TemperatureModel model;
	private TemperatureController controller;
	private JSlider slide;


	public TemperatureVueSlider(String label, TemperatureModel model) {
		this.label = label;
		this.model = model;
		JFrame f =new JFrame(label);
		Container c = f.getContentPane();
		slide=new JSlider(JSlider.VERTICAL,-20,100,(int)model.getC());
		slide.setPaintLabels(true);
		slide.setMajorTickSpacing(10);
		slide.setMinorTickSpacing(1);
		slide.setPaintTicks(true);
		slide.setPreferredSize(new Dimension(100, 200));
		model.addObserver(this);
		slide.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Stub de la méthode généré automatiquement
				TemperatureVueSlider.this.model.setC(((JSlider)(arg0.getSource())).getValue());
			}
		});
		

		c.add(slide);
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Stub de la méthode généré automatiquement
			slide.setValue((int)model.getC());
	}

}
