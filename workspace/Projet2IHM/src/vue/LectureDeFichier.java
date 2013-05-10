package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.JTunes;
import main.MyPlayer;
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
	private JSlider volume;
	private JButton play;
	private JButton stop;
	private JButton next;
	private JButton back;
	private JToggleButton aleatoire;
	private JLabel titleMusique;
	private JLabel nextTitleMusique;
	private JButton changer;
	private MyPlayer player;

	private Musique currentMusique;
	private Musique nextMusique;
	private int nextMusiqueIndex;

	private JLabel temps;
	private JLabel tempsRestant;

	private Runnable thread;
	private Thread tempoLecteure;
	private volatile boolean threadSuspended;

	private boolean nonLu; //pour savoir si le morceau est en cours de lecture ou non

	public LectureDeFichier(){

		player=new MyPlayer();
		JPanel pvolume=new JPanel();
		pvolume.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Volume"));
		volume=new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		volume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				player.setVolume(volume.getValue()/100.0f);
			}
		});
		pvolume.add(volume);
		JPanel tmp=new JPanel(new BorderLayout());
		slider=new JSlider(JSlider.HORIZONTAL,0,1,0);
		slider.addChangeListener(new ChangeListener() {
			int lastValue=0;
			public void stateChanged(ChangeEvent arg0) {
				if(lastValue!=slider.getValue()-1&&slider.getValue()>0)
					player.setPosition(slider.getValue());
				if(slider.getValue()==slider.getMaximum()){
					next();
				}
				temps.setText(slider.getValue()/60+":"+(slider.getValue()%60<10?"0":"")+slider.getValue()%60);
				tempsRestant.setText(slider.getMaximum()/60+":"+(slider.getMaximum()%60<10?"0":"")+slider.getMaximum()%60);
				lastValue=slider.getValue();
			}
		});
		this.setLayout(new BorderLayout());
		tmp.add(slider,BorderLayout.NORTH);
		tmp.add(temps=new JLabel("0"),BorderLayout.WEST);
		tmp.add(tempsRestant=new JLabel("0"),BorderLayout.EAST);
		this.add(tmp,BorderLayout.NORTH);
		JPanel centre= new JPanel();
		centre.setLayout(new FlowLayout(FlowLayout.CENTER));
		centre.add(pvolume);
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
		tmp=new JPanel(new BorderLayout());
		nextTitleMusique=new JLabel(" ");
		tmp.add(nextTitleMusique,BorderLayout.NORTH);
		changer=new JButton(new ChangerAction(this));
		tmp.add(changer,BorderLayout.SOUTH);
		south.add(tmp,BorderLayout.EAST);
		this.add(south,BorderLayout.SOUTH);
		tempoLecteure=new Thread(new Runnable() {	
			public void run() {
				try {
					thread=this;
					while(true){
						synchronized(this){
							while (threadSuspended){
								this.wait();
							}
						}
						slider.setValue(slider.getValue()+1);
						Thread.sleep(1000);
					}

				} catch (InterruptedException e) {
					System.err.println("erreur dans le tempo");
				}
			}
		});
		threadSuspended=true;
		tempoLecteure.start();
	}


	public void selectMusique(int index){
		boolean needNotify=!threadSuspended;
		this.nonLu = false;
		threadSuspended=true;
		player.Load(-1);
		currentMusique=((StructureMusique)(JTunes.ListeDeLecture.getModel().getValueAt(index, 0))).getMusique();
		slider.setValue(0);
		slider.setMaximum(currentMusique.getDuration().getSeconde());
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
		if(needNotify){
			threadSuspended=false;
			synchronized(thread){
				thread.notify();
			}
		}
	}

	public void changerButtomActive(boolean active){
		if(active)
			this.changerLaSuivante();
		else{
			int index=JTunes.ListeDeLecture.getSelectedRow();
			if(JTunes.ListeDeLecture.getModel().getRowCount()-1==index)
				nextMusiqueIndex=0;
			else
				nextMusiqueIndex=++index;
			nextMusique=((StructureMusique)(JTunes.ListeDeLecture.getModel().getValueAt(nextMusiqueIndex, 0))).getMusique();
			nextTitleMusique.setText("suivante :"+nextMusique.getTitle());
		}

		changer.getAction().setEnabled(active);
	}

	public void play(boolean isPlay){
		player.PlayPause();
		if (nonLu ==false){
			this.nonLu =true;
			Bibliotheque.addLecture(currentMusique);
		}
		/*if(isPlay)
			System.out.println("dans ma t\u00eate j'entends "+currentMusique.getTitle());
		else
			System.out.println("dans ma t\u00eate je n'entends plus "+currentMusique.getTitle());*/
		if(isPlay){
			threadSuspended=!isPlay;
			synchronized(thread){
				thread.notify();
			}
		}else{
			threadSuspended=isPlay;
		}
	}

	/*
	 * Pour Stop on a pris l'action du player à savoir de mettre la musique à zero seconde
	 * sans pour cela l'arreter (on ne peut pas savoir si le player est en play au en pause).
	 */
	public void stop(){
		player.Stop();
		if(!threadSuspended){
			this.play(false);
		}
		slider.setValue(0);
	}

	public void next(){
		this.play(false);
		JTunes.ListeDeLecture.getSelectionModel().addSelectionInterval(nextMusiqueIndex, nextMusiqueIndex);
		this.play(true);
	}

	public void back() {
		this.play(false);
		if(nextMusiqueIndex>2)
			JTunes.ListeDeLecture.getSelectionModel().addSelectionInterval(nextMusiqueIndex-2, nextMusiqueIndex-2);
		else
			JTunes.ListeDeLecture.getSelectionModel().addSelectionInterval(0, 0);
		this.play(true);
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
	 * retourne l'action 'changer'
	 * @return back
	 */
	public Action getChangerAction(){
		return changer.getAction();
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


	public void changerLaSuivante() {
		nextMusiqueIndex=random.nextInt(JTunes.ListeDeLecture.getRowCount());
		nextMusique=((StructureMusique)(JTunes.ListeDeLecture.getModel().getValueAt(nextMusiqueIndex, 0))).getMusique();
		nextTitleMusique.setText("suivante :"+nextMusique.getTitle());
	}

}
