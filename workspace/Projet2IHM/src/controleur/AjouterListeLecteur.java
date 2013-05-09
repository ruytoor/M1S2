package controleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.table.DefaultTableModel;

import main.JTunes;
import structuredonne.StructureMusique;

public class AjouterListeLecteur extends AbstractAction{

	private static final long serialVersionUID = -8257274736048074318L;

	public AjouterListeLecteur(){
		super("Ajouter");
		this.setEnabled(false);
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
