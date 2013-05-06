

import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import vue.Bibliotheque;
import vue.LectureDeFichier;

public class jTunes {
	
	public static void main (String[] args)  throws SQLException, ClassNotFoundException{
		JFrame frame = new JFrame("jTunes");
		JTable bibliotheque=Bibliotheque.makeMeOneBibliotheque(12,"Stop");
		JTable lectureDeFichier = null;
		JPanel recherche;
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(lectureDeFichier), new JScrollPane(bibliotheque));
		frame.add(split);
		
		frame.setPreferredSize(new Dimension(500,200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		
		
	}
	
}
