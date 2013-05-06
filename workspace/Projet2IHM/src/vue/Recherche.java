package vue;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 * classe permettant la recherche dans la Bibliotheque. De base, la recherche se fait dans toute la base de données. Mais il est
 * possible de dire dans quelles colonnes doivent se faire la recherche. Ici, le titre et/ou le nom de l'artiste
 * @author Aurore Allart et Benjamin Ruytoor
 * @version 30 avril 2013
 */
public class Recherche {

	private JCheckBox title;
	private JCheckBox artist;
	private String nom;
	private JTextField texte;
	
	public Recherche(){
		this.nom = "Recherche";
		this.title = new JCheckBox("titre");
		this.artist = new JCheckBox("artiste");
		texte = new JTextField();
		texte.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if((arg0.getKeyCode()==KeyEvent.VK_ENTER)||(1==1)){ //si l'utilisateur appuye sur entree ou si il a attendu + de 10 sec entre chaque lettre
					if (texte.getText().length() > 0) {
						if(title.isSelected()){
							if(artist.isSelected()){
								//recherche dans artist et title
							}else{
								//recherche dans title
							}
						}else{
							if(artist.isSelected()){
								//recherche dans artist
							}else{
								//recherche dans tout
							}
						}
					} 
				}
			}
		});
		
		
	}
	
}
