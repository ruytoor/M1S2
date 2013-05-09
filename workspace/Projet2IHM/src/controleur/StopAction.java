package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import vue.LectureDeFichier;

public class StopAction extends AbstractAction {

	
	private static final ImageIcon stopIcon=new ImageIcon("stop-icon.png");
	private LectureDeFichier lec;
	public StopAction( LectureDeFichier lec){
		super("stop",stopIcon);
		this.setEnabled(false);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_S);
		this.lec=lec;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		lec.stop();
	}

}
