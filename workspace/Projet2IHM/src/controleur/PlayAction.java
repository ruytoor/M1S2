package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import main.JTunes;
import structureDeDonnees.Musique;
import structureDeDonnees.StructureMusique;

/**
 * Classe permettant de lancer la lecture du morceau selectionne. Affectation de la touche 'p' à cette action. 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class PlayAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private boolean isPlay;
	
	private static final ImageIcon playIcon=new ImageIcon("play-icon.png");
	private static final ImageIcon pauseIcon=new ImageIcon("pause-icon.png");
	
	public PlayAction(){
		super("play",playIcon);
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
		
		Musique mus =((StructureMusique)(JTunes.ListeDeLecture.getModel().getValueAt(JTunes.ListeDeLecture.getSelectedRow(),0))).getMusique();	
		
		isPlay=!isPlay;		
	}
}
