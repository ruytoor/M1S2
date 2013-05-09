package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * Classe permettant de retourner au morceau precedent. Affectation de la touche 'b' a cette action.
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class BackAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private static final ImageIcon backIcon=new ImageIcon("back-icon.png");
	
	public BackAction(){
		super("back",backIcon);
		this.setEnabled(false);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_B);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
