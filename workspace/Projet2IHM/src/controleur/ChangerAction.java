package controleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import vue.LectureDeFichier;

public class ChangerAction extends AbstractAction {

	private LectureDeFichier lec;
	
	public ChangerAction(LectureDeFichier lec){
		super("changer la suivante");
		this.setEnabled(false);
		this.lec=lec;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		lec.changerLaSuivante();
	}

}
