package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * Classe permettant de passer au morceau suivant. Affectation de la touche 'n' a cette action. 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class NextAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private static final ImageIcon nextIcon=new ImageIcon("next-icon.png");
	
	public NextAction(){
		super("next",nextIcon);
		this.setEnabled(false);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_N);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
