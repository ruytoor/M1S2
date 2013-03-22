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
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * Premiere fenetre IHM : choix du nombre de couleurs
 * 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 21 mars 2013
 */
public class Launcher {
	private JFrame f;
	public Launcher(){
		f=new JFrame("Launcher");
		Container c=f.getContentPane();
		f.setPreferredSize(new Dimension(350, 150));
		f.setLayout(new GridLayout(3, 1, 10, 10));
		c.add(new JLabel("Combien de couleurs ?"));
		//choix de 2 a 10 couleurs par pas de 1 (commencant a 2)
		final JSpinner spinner = new JSpinner(new SpinnerNumberModel(2, 2, 10, 1));
		
		//style du spinner 
		((DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);
		((DefaultEditor)spinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.CENTER);
		((DefaultEditor)spinner.getEditor()).getTextField().setFont(((DefaultEditor)spinner.getEditor()).getTextField().getFont().deriveFont(30.0f));

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
