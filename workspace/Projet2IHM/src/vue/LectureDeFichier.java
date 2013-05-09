package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

import main.JTunes;
import structureDeDonnees.Musique;
import structureDeDonnees.StructureMusique;
import controleur.BackAction;
import controleur.AleatoireAction;
import controleur.ChangerAction;
import controleur.NextAction;
import controleur.PlayAction;
import controleur.StopAction;

/**
 * classe contenant les fonctions en rapport avec le lecteur de fichier 
 * @author Aurore Allart et Benjamin Ruytoor
 * @version 30 avril 2013
 */
public class LectureDeFichier extends JPanel{

	private static final Random random=new Random();
	
	private static final long serialVersionUID = 1L;
	private JSlider slider;
	private JButton play;
	private JButton stop;
	private JButton next;
	private JButton back;
	private JToggleButton aleatoire;
	private JLabel titleMusique;
	private JLabel nextTitleMusique;
	private JButton changer;

	private Musique currentMusique;
	private Musique nextMusique;
	private int nextMusiqueIndex;

	
	
	
	public LectureDeFichier(){
		slider=new JSlider(JSlider.HORIZONTAL);
		slider.setValue(0);
		this.setLayout(new BorderLayout());
		this.add(slider,BorderLayout.NORTH);
		JPanel centre= new JPanel();
		centre.setLayout(new FlowLayout(FlowLayout.CENTER));
		back=new JButton(new BackAction(this));
		centre.add(back);
		play=new JButton(new PlayAction(this));
		centre.add(play);
		stop=new JButton(new StopAction(this));
		centre.add(stop);
		next=new JButton(new NextAction(this));
		centre.add(next);
		aleatoire=new JToggleButton();
		aleatoire.setAction(new AleatoireAction(aleatoire,this));
		centre.add(aleatoire);
		this.add(centre,BorderLayout.CENTER);
		JPanel south=new JPanel(new BorderLayout());
		titleMusique=new JLabel(" ");
		south.add(titleMusique,BorderLayout.WEST);
		JPanel tmp=new JPanel(new BorderLayout());
		nextTitleMusique=new JLabel(" ");
		tmp.add(nextTitleMusique,BorderLayout.NORTH);
		changer=new JButton(new ChangerAction(this));
		tmp.add(changer,BorderLayout.SOUTH);
		south.add(tmp,BorderLayout.EAST);
		this.add(south,BorderLayout.SOUTH);
	}


	public void selectMusique(int index){
		currentMusique=((StructureMusique)(JTunes.ListeDeLecture.getModel().getValueAt(index, 0))).getMusique();
		if(!aleatoire.isSelected()){
			if(JTunes.ListeDeLecture.getModel().getRowCount()-1==index)
				nextMusiqueIndex=0;
			else
				nextMusiqueIndex=++index;
		}else{
			nextMusiqueIndex=random.nextInt(JTunes.ListeDeLecture.getRowCount());
		}
		nextMusique=((StructureMusique)(JTunes.ListeDeLecture.getModel().getValueAt(nextMusiqueIndex, 0))).getMusique();
		titleMusique.setText("en cours :"+currentMusique.getTitle());
		nextTitleMusique.setText("suivante :"+nextMusique.getTitle());
	}

	public void changerButtomActive(boolean active){
		changer.getAction().setEnabled(active);
	}

	public void play(boolean isPlay){
		if(isPlay)
			System.out.println("dans ma tête j'entends "+currentMusique.getTitle());
		else
			System.out.println("dans ma tête je n'entends plus "+currentMusique.getTitle());
	}

	public void stop(){
		slider.setValue(0);
		this.play(false);
	}

	public void next(){
		JTunes.ListeDeLecture.getSelectionModel().addSelectionInterval(nextMusiqueIndex, nextMusiqueIndex);
	}

	public void back() {
		// TODO Auto-generated method stub
		if(nextMusiqueIndex>2)
			JTunes.ListeDeLecture.getSelectionModel().addSelectionInterval(nextMusiqueIndex-2, nextMusiqueIndex-2);
		else
			JTunes.ListeDeLecture.getSelectionModel().addSelectionInterval(0, 0);

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
		retour.add(changer.getAction());
		return retour;
	}


	public void changerLaSuivante() {
		nextMusiqueIndex=random.nextInt(JTunes.ListeDeLecture.getRowCount());
		nextMusique=((StructureMusique)(JTunes.ListeDeLecture.getModel().getValueAt(nextMusiqueIndex, 0))).getMusique();
		nextTitleMusique.setText("suivante :"+nextMusique.getTitle());
	}

}
