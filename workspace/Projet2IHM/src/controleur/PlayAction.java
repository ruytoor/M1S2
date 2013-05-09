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
			this.putValue(SMALL_ICON, playIcon);
			this.firePropertyChange(SMALL_ICON, pauseIcon, playIcon);
			this.putValue(LARGE_ICON_KEY, playIcon);
			this.firePropertyChange(LARGE_ICON_KEY, pauseIcon, playIcon);
		}	
		else{
			this.putValue(NAME, "pause");
			this.firePropertyChange(AbstractAction.NAME, "play", "pause");
			this.putValue(SMALL_ICON, pauseIcon);
			this.firePropertyChange(SMALL_ICON, playIcon, pauseIcon);
			this.putValue(LARGE_ICON_KEY, pauseIcon);
			this.firePropertyChange(LARGE_ICON_KEY, playIcon, pauseIcon);
		}
		
		
		
		
		isPlay=!isPlay;		
	}
}
