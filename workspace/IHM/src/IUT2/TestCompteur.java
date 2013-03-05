package IUT2;

import javax.swing.JFrame;

public class TestCompteur {
	public static void main(String[] args) {
		JFrame f1 = new JFrame("Test compteur vue 1");
		CompteurModele m = new CompteurModele();
		f1.getContentPane().add(new CompteurVueControleur(m));
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.pack();
		f1.setVisible(true);
		
		JFrame f2 = new JFrame("Test compteur vue 2");
		f2.getContentPane().add(new CompteurVueControleur(m));
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.pack();
		f2.setVisible(true);
	}

}
