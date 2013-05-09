package structureDeDonnees;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Cette classe permet de transferer de la table Bibliotheque a la table LectureDeFichier, les indices des musiques selectionnee
 * par la drag and drop. 
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class IndiceTransferable implements Transferable {
	
	private int debut;
	private int fin;
	
	public IndiceTransferable(int debut,int fin){
		this.debut=debut;
		this.fin=fin;
	}
	
	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		return debut+"-"+fin;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[]{DataFlavor.stringFlavor};
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(DataFlavor.stringFlavor);
	}

}
