package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import vue.LectureDeFichier;

/**
 * change la musique suivante
 * @author Aurore Allart et Benjamin Ruytoor
 * @version 9 mai 2013
 */
public class ChangerAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private LectureDeFichier lec;
	
	public ChangerAction(LectureDeFichier lec){
		super("Changer la suivante");
		this.setEnabled(false);
		this.lec=lec;
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_C);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		lec.changerLaSuivante();
	}

}
