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
	private ImageIcon backIcon;//=new ImageIcon("back-icon.png");
	private LectureDeFichier lec;
	
	public BackAction(LectureDeFichier lec){
		super("Back");
		backIcon=new ImageIcon(getClass().getClassLoader().getResource("back-icon.png"));
		this.putValue(SMALL_ICON, backIcon);
		this.putValue(LARGE_ICON_KEY, backIcon);
		this.setEnabled(false);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_B);
		this.lec=lec;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		lec.back();
	}

}
