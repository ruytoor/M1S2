package controleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.table.DefaultTableModel;

import main.JTunes;

/**
 * classe qui comprend l'action de supprimer tout le contenu de la table ListeDeLecture
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class SupprimerListeAction extends AbstractAction{

	private static final long serialVersionUID = 1L;

	public SupprimerListeAction(){
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
