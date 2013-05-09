package main;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.dnd.DropTarget;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.DropMode;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import controleur.AjouterListeLecteur;
import controleur.AfficherPopUpMenuListener;
import controleur.DragTransferHandler;
import controleur.DropTransferHandler;

import vue.Bibliotheque;
import vue.LectureDeFichier;
import vue.Recherche;

public class JTunes {

	public static JTable bibliotheque;
	public static JTable ListeDeLecture;
	public static List<Action> listActionBibliotheque;

	public static void main (String[] args)  throws SQLException, ClassNotFoundException{
		final JFrame frame = new JFrame("jTunes");
		frame.setLayout(new BorderLayout());
		bibliotheque=Bibliotheque.makeMeOneBibliotheque("",false,false);
		listActionBibliotheque=Bibliotheque.getActions();
		JPopupMenu jpBibli=new JPopupMenu();
		JMenu menuBibli=new JMenu("Bibliotheque");
		for(Action a:listActionBibliotheque){
			jpBibli.add(a);
			menuBibli.add(a);
		}
		bibliotheque.addMouseListener(new AfficherPopUpMenuListener(jpBibli,bibliotheque));
		bibliotheque.add(jpBibli);
		bibliotheque.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		bibliotheque.setDragEnabled(true);
		bibliotheque.setTransferHandler(new DragTransferHandler());
		
		ListeDeLecture = new JTable(new DefaultTableModel(Bibliotheque.columnNames, 0));
		ListeDeLecture.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JPopupMenu jpListLecture=new JPopupMenu();
		JMenu menuListLecture=new JMenu("Play List");
		for(Action a:listActionBibliotheque){
			jpListLecture.add(a);
			menuListLecture.add(a);
		}
		
		JPanel recherche=new Recherche();
		frame.add(recherche,BorderLayout.NORTH);
		
	//	JMenu menuRecherche=new JMenu("Recherche");  voir pour un jcheckboxMenuItem
	//	menuRecherche.add((((Recherche) recherche).getActionArtist()));
	//	menuRecherche.add((((Recherche) recherche).getActionTitle()));
		
		JMenu menuLecture=new JMenu("Lecture");
		for(Action a:listActionBibliotheque){
			menuLecture.add(a);
		}
		JScrollPane jsp=new JScrollPane(ListeDeLecture);
		jsp.setTransferHandler(new DropTransferHandler());
		JPanel p1=new JPanel(new BorderLayout());
		p1.add(new JLabel("Play List"),BorderLayout.NORTH);
		p1.add(jsp,BorderLayout.CENTER);
		
		JPanel p2=new JPanel(new BorderLayout());
		p2.add(new JLabel("Bibliotheque"),BorderLayout.NORTH);
		p2.add(new JScrollPane(bibliotheque),BorderLayout.CENTER);
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,p1 , p2);
		split.setDividerLocation(300);
		frame.add(split,BorderLayout.CENTER);
		frame.add(new LectureDeFichier(),BorderLayout.SOUTH);
		
		
		JMenuBar menuBar=new JMenuBar();
		menuBar.add(menuLecture);
		menuBar.add(menuListLecture);
		menuBar.add(menuBibli);
	//	menuBar.add(menuRecherche);
		frame.setJMenuBar(menuBar);
		frame.setPreferredSize(new Dimension(800,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
