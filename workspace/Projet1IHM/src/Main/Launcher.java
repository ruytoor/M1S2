package Main;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;


public class Launcher {
	private JFrame f;
	public Launcher(){
		f=new JFrame("Launcher");
		Container c=f.getContentPane();
		f.setPreferredSize(new Dimension(350, 150));
		f.setLayout(new GridLayout(3, 1, 10, 10));
		c.add(new JLabel("Combien de couleur ?"));
		final JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		((DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);
		JButton b= new JButton("ok");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				Main.start((Integer)(spinner.getModel().getValue()));
			}
		});
		c.add(spinner);		
		c.add(b);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
}
