package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class JToggleButtonAction extends AbstractAction {

	private static final ImageIcon stopIcon=new ImageIcon("shuffle-icon.png");
	private JToggleButton jb;

	public JToggleButtonAction(JToggleButton jb){
		super("Al\u00e9atoire",stopIcon);
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		this.jb=jb;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!arg0.getSource().equals(jb))
			jb.setSelected(!jb.isSelected());
	}

}
