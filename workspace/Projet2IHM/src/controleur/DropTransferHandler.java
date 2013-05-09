package controleur;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;

import structureDeDonnees.StructureMusique;

import main.JTunes;

/**
 * Classe permettant de deposer les morceaux selectionnes
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class DropTransferHandler extends TransferHandler {

	private static final long serialVersionUID = 1L;

	/**
	 * retourne true si le component recevant les informations peut accepter le type d'information
	 * @param le component recevant
	 * @param les informations encapsulees
	 * @return true si le component recevant est ok, false sinon
	 */
	public boolean canImport(JComponent c, DataFlavor[] flavors) {
		return flavors[0].equals(DataFlavor.stringFlavor);
	}

	/**
	 * transfert les donnees si le component recevant peut
	 * @param le component recevant
	 * @param les donnees a transferer
	 * @return true
	 */
	public boolean importData(JComponent c, Transferable t){
		try {
			String tmpS=(String)t.getTransferData(DataFlavor.stringFlavor);
			String tabS[]=tmpS.split("-");
			int debut=Integer.parseInt(tabS[0]);
			int fin=Integer.parseInt(tabS[1]);
			
			DefaultTableModel model=(DefaultTableModel)(JTunes.ListeDeLecture.getModel());
			for(int i=debut;i<=fin;++i){
				StructureMusique s=(StructureMusique) JTunes.bibliotheque.getModel().getValueAt(i, 0);
				model.addRow(new Object[]{s.getMusique().getTitle(),s.getMusique().getAlbum(),s.getMusique().getArtist(),s.getMusique().getGenre(),s.getMusique().getYear(),s.getMusique().getDuration()});
			}
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
