package controleur;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;

import structuredonne.IndiceTransferable;
import structuredonne.StructureMusique;

import main.JTunes;

public class DropTransferHandler extends TransferHandler {

	public boolean canImport(JComponent c, DataFlavor[] flavors) {
		return flavors[0].equals(DataFlavor.stringFlavor);
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
