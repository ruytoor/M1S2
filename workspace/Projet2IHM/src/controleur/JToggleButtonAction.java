package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

/**
 * lien entre les deux boutons permettant la lecture aleatoire. Affectation de la touche 'a' a cette action. 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class JToggleButtonAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private static final ImageIcon stopIcon=new ImageIcon("shuffle-icon.png");
	private JToggleButton jb;

	public JToggleButtonAction(JToggleButton jb){
		super("Al\u00e9atoire",stopIcon);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		this.jb=jb;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!arg0.getSource().equals(jb))
			jb.setSelected(!jb.isSelected());
	}

}
