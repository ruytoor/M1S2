package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CouleurVue extends JPanel implements Observer{

	private JPanel chooser;
	private JPanel couleur;
	private int nivGris;
	
	public CouleurVue(int i,int nivGris){
		System.out.println(nivGris);
		this.setPreferredSize(new Dimension(600, 100));
		this.setLayout(new GridLayout(1, 4, 2, 2));
		JLabel label=new JLabel("couleur n°"+i);
		label.setPreferredSize(new Dimension(100, 100));
		add(label);
		chooser=new JPanel();
		chooser.setPreferredSize(new Dimension(300, 100));
		add(chooser);
		this.nivGris=nivGris;
		couleur=new JPanel();
		couleur.setPreferredSize(new Dimension(100, 100));
		JPanel vueGris=new JPanel();
		vueGris.setBackground(new Color(nivGris, nivGris, nivGris));
		vueGris.setPreferredSize(new Dimension(100, 100));
		add(couleur);
		add(vueGris);
		this.setVisible(true);
		System.out.println("test");
	}
	
	@Override
	public void update(Observable o, Object arg) {
		couleur.setBackground((Color)(arg));
	}


}
