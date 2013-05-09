package controleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class PlayAction extends AbstractAction {

	private boolean isPlay;
	
	private static final ImageIcon playIcon=new ImageIcon("play-icon.png");
	private static final ImageIcon pauseIcon=new ImageIcon("pause-icon.png");
	
	public PlayAction(){
		super("play",playIcon);
		isPlay=false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isPlay){
			this.putValue(NAME, "play");
			this.firePropertyChange(AbstractAction.NAME, "pause", "play");
		}	
		else{
			this.putValue(NAME, "pause");
			this.firePropertyChange(AbstractAction.NAME, "play", "pause");
		}
		
		isPlay=!isPlay;
		
	}
}
