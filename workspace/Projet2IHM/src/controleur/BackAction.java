package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import vue.LectureDeFichier;

public class BackAction extends AbstractAction {

	private static final ImageIcon backIcon=new ImageIcon("back-icon.png");
	private LectureDeFichier lec;
	public BackAction(LectureDeFichier lec){
		super("back",backIcon);
		this.setEnabled(false);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_B);
		this.lec=lec;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		lec.back();
	}

}
