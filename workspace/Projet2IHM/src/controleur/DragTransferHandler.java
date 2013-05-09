package controleur;

import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import main.JTunes;
import structureDeDonnees.IndiceTransferable;

/**
 * Classe permettant de faire le drag and drop
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class DragTransferHandler extends TransferHandler {

	private static final long serialVersionUID = 1L;

	/**
	 * retourne les indices a transferer
	 * @param component de depart
	 * @return les donnees à transferer
	 */
	protected Transferable createTransferable(JComponent c) {
		return new IndiceTransferable(JTunes.bibliotheque.getSelectionModel().getMinSelectionIndex(), JTunes.bibliotheque.getSelectionModel().getMaxSelectionIndex());
	}

	/**
	 * 
	 * @param component de depart
	 * @return l'indice COPY
	 */
	public int getSourceActions(JComponent c) {
		return COPY;
	}
}
