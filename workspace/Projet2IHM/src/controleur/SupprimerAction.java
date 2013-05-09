package controleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.table.DefaultTableModel;

import main.JTunes;
import structuredonne.Musique;
import structuredonne.StructureMusique;

public class SupprimerAction extends AbstractAction {


	public SupprimerAction(){
		super("Supprimer");
		this.setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel model=(DefaultTableModel) JTunes.ListeDeLecture.getModel();
		model.removeRow(JTunes.ListeDeLecture.getSelectedRow());
	}
}
