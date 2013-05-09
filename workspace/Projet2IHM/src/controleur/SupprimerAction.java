package controleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.table.DefaultTableModel;

import main.JTunes;

/**
 * Classe contenant l'action de supprimer une ligne de la table de ListeDeLecture
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class SupprimerAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

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
