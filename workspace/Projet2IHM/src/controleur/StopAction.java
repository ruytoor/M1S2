package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import vue.LectureDeFichier;/**
 * Classe contenant l'action de stopper la lecture du morceau en cours. Affectation de la touche 's' à cette action  
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */public class StopAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private static final ImageIcon stopIcon=new ImageIcon("stop-icon.png");
	private LectureDeFichier lec;
	
	public StopAction( LectureDeFichier lec){
		super("Stop",stopIcon);
		this.setEnabled(false);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_S);
		this.lec=lec;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		lec.stop();
	}

}
