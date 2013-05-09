package vue;

import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * classe contenant les fonctions en rapport avec le lecteur de fichier 
 * @author Aurore Allart et Benjamin Ruytoor
 * @version 30 avril 2013
 */
public class LectureDeFichier extends JPanel{
	
	private JSlider slider;
	
	public LectureDeFichier(){
		slider=new JSlider(JSlider.HORIZONTAL);
	}

}
