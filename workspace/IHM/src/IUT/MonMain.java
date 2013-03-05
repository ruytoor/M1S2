package IUT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class MonMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model m=new Model(33, 33, 33);
		//TextualView tview=new TextualView(m);
		//RandomControler rc=new RandomControler(m);
		JPanel gview=new GraphicalView(m, Color.RED, Color.GREEN, Color.BLUE);
		JFrame f=new JFrame("GraphicalView");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add(gview,BorderLayout.NORTH);
		VueControleur vc=new VueControleur(m);
		VueControleur2 vc2=new VueControleur2(m);
		f.getContentPane().add(vc,BorderLayout.CENTER);
		f.getContentPane().add(vc2,BorderLayout.SOUTH);
		f.setTitle("graphique et controleur vu");
		f.pack();
		f.setVisible(true);
		m.set(2, 30);
		//rc.go(30);
	}

}
