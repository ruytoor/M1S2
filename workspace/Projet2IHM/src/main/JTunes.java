package main;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import controleur.AjouterListeLecteur;
import controleur.JTableListener;

import vue.Bibliotheque;
import vue.LectureDeFichier;
import vue.Recherche;

public class JTunes {
	
	public static JTable bibliotheque;
	public static JTable ListeDeLecture;
	public static List<Action> listActionBibliotheque;
	
	public static void main (String[] args)  throws SQLException, ClassNotFoundException{
		JPopupMenu jp=new JPopupMenu();

		
		JFrame frame = new JFrame("jTunes");
		frame.setLayout(new BorderLayout());
		bibliotheque=Bibliotheque.makeMeOneBibliotheque("",false,false);
		listActionBibliotheque=Bibliotheque.getActions();

		for(Action a:listActionBibliotheque)
			jp.add(a);
		bibliotheque.addMouseListener(new JTableListener(jp));
		bibliotheque.add(jp);
		
		ListeDeLecture = new JTable(new Object[0][0], Bibliotheque.columnNames);
		JPanel recherche=new Recherche();
		frame.add(recherche,BorderLayout.NORTH);
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(ListeDeLecture), new JScrollPane(bibliotheque));
		frame.add(split,BorderLayout.CENTER);
		
		frame.setPreferredSize(new Dimension(500,200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
