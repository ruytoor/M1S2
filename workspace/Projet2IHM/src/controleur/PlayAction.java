package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import structuredonne.Musique;
import structuredonne.StructureMusique;
import vue.LectureDeFichier;

import main.JTunes;

public class PlayAction extends AbstractAction {

	private boolean isPlay;
	
	private static final ImageIcon playIcon=new ImageIcon("play-icon.png");
	private static final ImageIcon pauseIcon=new ImageIcon("pause-icon.png");
	private LectureDeFichier lec;
	public PlayAction(LectureDeFichier lec){
		super("play",playIcon);
		this.lec=lec;
		isPlay=false;
		this.setEnabled(false);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_P);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isPlay){
			this.putValue(NAME, "play");
			this.firePropertyChange(AbstractAction.NAME, "pause", "play");
			this.putValue(SMALL_ICON, playIcon);
			this.firePropertyChange(SMALL_ICON, pauseIcon, playIcon);
			this.putValue(LARGE_ICON_KEY, playIcon);
			this.firePropertyChange(LARGE_ICON_KEY, pauseIcon, playIcon);
		}	
		else{
			this.putValue(NAME, "pause");
			this.firePropertyChange(AbstractAction.NAME, "play", "pause");
			this.putValue(SMALL_ICON, pauseIcon);
			this.firePropertyChange(SMALL_ICON, playIcon, pauseIcon);
			this.putValue(LARGE_ICON_KEY, pauseIcon);
			this.firePropertyChange(LARGE_ICON_KEY, playIcon, pauseIcon);
		}
		lec.play(!isPlay);
		isPlay=!isPlay;		
	}
}
