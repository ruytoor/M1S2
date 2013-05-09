package vue;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import main.JTunes;

/**
 * classe permettant la recherche dans la Bibliotheque. De base, la recherche se fait dans toute la base de donn�es. Mais il est
 * possible de dire dans quelles colonnes doivent se faire la recherche. Ici, le titre et/ou le nom de l'artiste
 * @author Aurore Allart et Benjamin Ruytoor
 * @version 30 avril 2013
 */
public class Recherche extends JPanel{

	private static final long serialVersionUID = 1L;
	private JCheckBox title;
	private JCheckBox artist;
	private JLabel jLabel;
	private JTextField texte;

	public Recherche(){
		this.jLabel = new JLabel("Recherche :");
		this.title = new JCheckBox("titre");
		this.artist = new JCheckBox("artiste");
		texte = new JTextField(12);
		texte.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				if((arg0.getKeyCode()==KeyEvent.VK_ENTER)){ //si l'utilisateur appuye sur entree ou si il a attendu + de 10 sec entre chaque lettre
					if (texte.getText().length() > 0) {
						if(title.isSelected()){
							if(artist.isSelected()){
								//recherche dans artist et title
								JTunes.bibliotheque.setModel(new DefaultTableModel(Bibliotheque.recherche(texte.getText(), true, true), Bibliotheque.columnNames));
							}else{
								//recherche dans title
								JTunes.bibliotheque.setModel(new DefaultTableModel(Bibliotheque.recherche(texte.getText(), false, true), Bibliotheque.columnNames));
							}
						}else{
							if(artist.isSelected()){
								//recherche dans artist
								JTunes.bibliotheque.setModel(new DefaultTableModel(Bibliotheque.recherche(texte.getText(), true, false), Bibliotheque.columnNames));
							}else{
								//recherche dans tout
								JTunes.bibliotheque.setModel(new DefaultTableModel(Bibliotheque.recherche(texte.getText(), false, false), Bibliotheque.columnNames));
							}
						}
					} 
				}
			}
		});
		this.setLayout(new FlowLayout());
		this.add(jLabel,FlowLayout.LEFT);
		this.add(texte,FlowLayout.CENTER);
		this.add(artist,FlowLayout.RIGHT);
		this.add(title,FlowLayout.RIGHT);
	}
}
