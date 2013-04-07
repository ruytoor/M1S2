package vue;

import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class jTunes {
	
	
	public static void main (String[] args)  throws SQLException, ClassNotFoundException{
		JFrame frame = new JFrame("jTunes");
		//JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(new LectureDeFichier()), new JScrollPane(new Bibliotheque()));
		//frame.add(split);
		
		frame.setPreferredSize(new Dimension(500,200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		
		
	}
	
}
