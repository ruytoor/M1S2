package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;

/**
 * Classe permettant d'afficher le pop up, par le clic droit, qui contient le menu contextuel de la table où se trouve la souris.
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class AfficherPopUpMenuListener implements MouseListener{

	private JPopupMenu popUpMenu;
	private JComponent compo;
	
	public AfficherPopUpMenuListener(JPopupMenu jp,JComponent c){
		this.popUpMenu=jp;
		this.compo=c;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON3){
			popUpMenu.show(compo, e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
}
