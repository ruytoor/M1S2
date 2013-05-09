package controleur;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import structuredonne.IndiceTransferable;

import main.JTunes;

public class DragTransferHandler extends TransferHandler {

	protected Transferable createTransferable(JComponent c) {
		return new IndiceTransferable(JTunes.bibliotheque.getSelectionModel().getMinSelectionIndex(), JTunes.bibliotheque.getSelectionModel().getMaxSelectionIndex());
	}

	public int getSourceActions(JComponent c) {
		return COPY;
	}
}
