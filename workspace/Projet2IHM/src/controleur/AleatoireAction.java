package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class AleatoireAction extends AbstractAction {

	private static final ImageIcon stopIcon=new ImageIcon("shuffle-icon.png");
	
	public AleatoireAction(){
		super("Al\u00e9atoire",stopIcon);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_A);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
