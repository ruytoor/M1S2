package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import structureDeDonnees.Musique;
import structureDeDonnees.StructureMusique;

import main.JTunes;

/**
 * classe permettant de recuperer les informations du morceau voulu. Affectation de la touche 'i' a cette action. 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class InformationAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public InformationAction(){
		super("Information");
		this.setEnabled(false);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_I);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Musique mus=((StructureMusique)JTunes.ListeDeLecture.getModel().getValueAt(JTunes.ListeDeLecture.getSelectedRow(), 0)).getMusique();
		JOptionPane.showMessageDialog(JTunes.frame, "Information :\t\n Titre : "+mus.getTitle()+"\n Album : "+mus.getAlbum()+"\n Artiste : "+mus.getArtist()+"\n Genre : "+mus.getGenre()+"\n Ann\u00e9e : "+mus.getYear()+"\n Dur\u00e9e : "+mus.getDuration());
	}

}
