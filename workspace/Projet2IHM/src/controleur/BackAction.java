package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import vue.LectureDeFichier;
/**
 * Classe permettant de retourner au morceau precedent. Affectation de la touche 'b' a cette action.
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */public class BackAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
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
