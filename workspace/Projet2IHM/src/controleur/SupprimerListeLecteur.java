package controleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.table.DefaultTableModel;

import main.JTunes;

public class SupprimerListeLecteur extends AbstractAction{


	public SupprimerListeLecteur(){
		super("Supprimer Tout");
		this.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel model=(DefaultTableModel) JTunes.ListeDeLecture.getModel();
		while(model.getRowCount()!=0)
			model.removeRow(0);
	}

}
