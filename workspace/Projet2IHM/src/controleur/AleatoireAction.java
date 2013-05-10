package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import main.JTunes;


import vue.LectureDeFichier;
/**
 * lien entre les deux boutons permettant la lecture aleatoire. Affectation de la touche 'a' a cette action. 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class AleatoireAction extends AbstractAction{
	private static final long serialVersionUID = 1L;
	private ImageIcon aleaIcon;//=new ImageIcon("images/shuffle-icon.png");
	private JToggleButton jb;
	private LectureDeFichier lec;
	
	public AleatoireAction(JToggleButton jb,LectureDeFichier lec){
		super("Al\u00e9atoire");
		aleaIcon=new ImageIcon(getClass().getClassLoader().getResource("shuffle-icon.png"));
		this.putValue(SMALL_ICON, aleaIcon);
		this.putValue(LARGE_ICON_KEY, aleaIcon);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		this.jb=jb;
		this.lec=lec;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!arg0.getSource().equals(jb))
			jb.setSelected(!jb.isSelected());
		lec.changerButtomActive(jb.isSelected()&&JTunes.ListeDeLecture.getRowCount()!=0);
		
	}

}
