package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import vue.LectureDeFichier;

public class NextAction extends AbstractAction {

	private static final ImageIcon nextIcon=new ImageIcon("next-icon.png");
	private LectureDeFichier lec;
	public NextAction(LectureDeFichier lec){
		super("next",nextIcon);
		this.setEnabled(false);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_N);
		this.lec=lec;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		lec.next();
	}

}
