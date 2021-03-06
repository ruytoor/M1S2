package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.table.DefaultTableModel;

import main.JTunes;
import structureDeDonnees.StructureMusique;

/**
 * Classe permettant d'ajouter des morceaux de la table Bibliotheque a la table LectureDeFichier 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class AjouterListeAction extends AbstractAction{

	private static final long serialVersionUID = -8257274736048074318L;

	public AjouterListeAction(){
		super("Ajouter");
		this.setEnabled(false);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_J);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int min=JTunes.bibliotheque.getSelectionModel().getMinSelectionIndex();
		int max=JTunes.bibliotheque.getSelectionModel().getMaxSelectionIndex();
		DefaultTableModel model=(DefaultTableModel)(JTunes.ListeDeLecture.getModel());
		for(int i=min;i<=max;++i){
			StructureMusique s=(StructureMusique) JTunes.bibliotheque.getModel().getValueAt(i, 0);
			model.addRow(new Object[]{s.getMusique().getTitle(),s.getMusique().getAlbum(),s.getMusique().getArtist(),s.getMusique().getGenre(),s.getMusique().getYear(),s.getMusique().getDuration()});
		}
	}
	
}
