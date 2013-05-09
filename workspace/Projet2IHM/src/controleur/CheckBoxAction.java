package controleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;

public class CheckBoxAction extends AbstractAction{

	private static final long serialVersionUID = 1L;
	private JCheckBox jc;

	public CheckBoxAction(JCheckBox jc){
		super(jc.getText());
		this.jc = jc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!e.getSource().equals(jc))
			jc.setSelected(!jc.isSelected());
			
	}

	
}
