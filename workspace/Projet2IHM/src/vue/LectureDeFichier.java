package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

import controleur.AleatoireAction;
import controleur.BackAction;
import controleur.NextAction;
import controleur.PlayAction;
import controleur.StopAction;

/**
 * classe contenant les fonctions en rapport avec le lecteur de fichier 
 * @author Aurore Allart et Benjamin Ruytoor
 * @version 30 avril 2013
 */
public class LectureDeFichier extends JPanel{
	
	private JSlider slider;
	private JButton play;
	private JButton stop;
	private JButton next;
	private JButton back;
	private JToggleButton aleatoire;
	
	public LectureDeFichier(){
		slider=new JSlider(JSlider.HORIZONTAL);
		this.setLayout(new BorderLayout());
		this.add(slider,BorderLayout.NORTH);
		JPanel south= new JPanel();
		south.setLayout(new FlowLayout(FlowLayout.CENTER));
		back=new JButton(new BackAction());
		south.add(back);
		play=new JButton(new PlayAction());
		south.add(play);
		stop=new JButton(new StopAction());
		south.add(stop);
		next=new JButton(new NextAction());
		south.add(next);
		aleatoire=new JToggleButton(new AleatoireAction());
		south.add(aleatoire);
		this.add(south,BorderLayout.SOUTH);
	}
	
	
	public Action getPlayAction(){
		return play.getAction();
	}
	
	public Action getStopAction(){
		return stop.getAction();
	}
	
	public Action getNextAction(){
		return next.getAction();
	}
	
	public Action getBackAction(){
		return back.getAction();
	}
	public Action getAleatoireAction(){
		return aleatoire.getAction();
	}
	
	public List<Action> getLecteurAction(){
		 ArrayList<Action> retour =new ArrayList<Action>();
		 retour.add(play.getAction());
		 retour.add(stop.getAction());
		 retour.add(next.getAction());
		 retour.add(back.getAction());
		 return retour;
	}
}
