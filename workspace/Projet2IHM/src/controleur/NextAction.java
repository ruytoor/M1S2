package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import vue.LectureDeFichier;
/**
 * Classe permettant de passer au morceau suivant. Affectation de la touche 'n' a cette action. 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */public class NextAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private ImageIcon nextIcon;//=new ImageIcon("next-icon.png");
	private LectureDeFichier lec;
	public NextAction(LectureDeFichier lec){
		super("next");
		nextIcon=new ImageIcon(getClass().getClassLoader().getResource("next-icon.png"));
		this.putValue(SMALL_ICON, nextIcon);
		this.putValue(LARGE_ICON_KEY, nextIcon);
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
