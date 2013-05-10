package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import vue.LectureDeFichier;/**
 * Classe contenant l'action de stopper la lecture du morceau en cours. Affectation de la touche 's' � cette action  
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */public class StopAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private ImageIcon stopIcon;//=new ImageIcon("images/stop-icon.png");
	private LectureDeFichier lec;
	public StopAction( LectureDeFichier lec){
		super("stop");
		stopIcon=new ImageIcon(getClass().getClassLoader().getResource("stop-icon.png"));
		this.putValue(SMALL_ICON, stopIcon);
		this.putValue(LARGE_ICON_KEY, stopIcon);
		this.setEnabled(false);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_S);
		this.lec=lec;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		lec.stop();
	}

}
