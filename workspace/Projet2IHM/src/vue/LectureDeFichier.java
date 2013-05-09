package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

import structureDeDonnees.Musique;
import controleur.BackAction;
import controleur.JToggleButtonAction;
import controleur.NextAction;
import controleur.PlayAction;
import controleur.StopAction;

/**
 * classe contenant les fonctions en rapport avec le lecteur de fichier 
 * @author Aurore Allart et Benjamin Ruytoor
 * @version 30 avril 2013
 */
public class LectureDeFichier extends JPanel{
	
	private static final long serialVersionUID = 1L;
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
		aleatoire=new JToggleButton();
		aleatoire.setAction(new JToggleButtonAction(aleatoire));
		south.add(aleatoire);
		this.add(south,BorderLayout.CENTER);
	}
	
	
	/**
	 * lance la lecture du morceau de musique
	 * @param mus
	 */
	public void play(Musique mus){
	}
	
	/**
	 * retourne l'action 'play'
	 * @return play
	 */
	public Action getPlayAction(){
		return play.getAction();
	}
	
	/**
	 * retourne l'action 'stop'
	 * @return stop
	 */
	public Action getStopAction(){
		return stop.getAction();
	}
	
	/**
	 * retourne l'action 'next'
	 * @return next
	 */
	public Action getNextAction(){
		return next.getAction();
	}
	
	/**
	 * retourne l'action 'back'
	 * @return back
	 */
	public Action getBackAction(){
		return back.getAction();
	}
	
	/**
	 * retourne l'action 'aleatoire'
	 * @return aleatoire
	 */
	public Action getAleatoireAction(){
		return aleatoire.getAction();
	}
	
	/**
	 * retourne la liste des actions liees a cette classe
	 * @return liste des actions
	 */
	public List<Action> getLecteurAction(){
		 ArrayList<Action> retour =new ArrayList<Action>();
		 retour.add(play.getAction());
		 retour.add(stop.getAction());
		 retour.add(next.getAction());
		 retour.add(back.getAction());
		 return retour;
	}

}
