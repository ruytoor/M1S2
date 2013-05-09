package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import structuredonne.Musique;
import structuredonne.StructureMusique;

import main.JTunes;

public class InformationAction extends AbstractAction {

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
