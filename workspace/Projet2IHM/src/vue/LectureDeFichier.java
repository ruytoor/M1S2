package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import controleur.PlayAction;

/**
 * classe contenant les fonctions en rapport avec le lecteur de fichier 
 * @author Aurore Allart et Benjamin Ruytoor
 * @version 30 avril 2013
 */
public class LectureDeFichier extends JPanel{
	
	private JSlider slider;
	private JButton play;
	private JButton stop;
	
	public LectureDeFichier(){
		slider=new JSlider(JSlider.HORIZONTAL);
		this.setLayout(new BorderLayout());
		this.add(slider,BorderLayout.NORTH);
		JPanel South= new JPanel();
		South.setLayout(new FlowLayout(FlowLayout.CENTER));

		play=new JButton(new PlayAction());
		South.add(play);
		stop=new JButton();
		South.add(stop);
		this.add(South,BorderLayout.SOUTH);
	}
	
	
	public Action getPlayAction(){
		return play.getAction();
	}
}
