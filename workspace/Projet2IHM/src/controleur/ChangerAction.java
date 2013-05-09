package controleur;

import java.awt.event.ActionEvent;

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
		super("changer la suivante");
		this.setEnabled(false);
		this.lec=lec;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		lec.changerLaSuivante();
	}

}
