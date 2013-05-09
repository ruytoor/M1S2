package vue;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.StyledEditorKit.BoldAction;

import controleur.AjouterListeLecteur;
import controleur.AfficherPopUpMenuListener;

import structuredonne.Musique;
import structuredonne.StructureMusique;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;

import main.JTunes;

public class Bibliotheque {

	public static String[] columnNames= new String[]{"title","album","artist","genre","year","duration"};
	

	public static JTable makeMeOneBibliotheque(String recherche,boolean artistCheck,boolean titleCheck){
		//new DefaultTableModel(); voir plus tard
		
		final JTable bil= new JTable(Bibliotheque.recherche(recherche,artistCheck,titleCheck), Bibliotheque.columnNames){
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) { 
				return false; 
			}
		};
		bil.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				for(Action a:JTunes.listActionBibliotheque){
					if(a.isEnabled()!=!bil.getSelectionModel().isSelectionEmpty())
						a.setEnabled(!bil.getSelectionModel().isSelectionEmpty());
				}
			}
		});
		return bil;
	}

	/**
	 * 
	 * @param numColumn si -1 => tout, si 12 => artist et title, si 1 => artist, si 2 => title
	 * @param recherche le mot a recherche dans la base de donnees
	 * @return
	 */
	public static Object[][] recherche(String recherche, boolean artistCheck, boolean titleCheck){
		Connection connection =initConnection();
		try{
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			ResultSet rs;
			ArrayList<Object[]> l=new ArrayList<Object[]>();
			if(artistCheck&&titleCheck)
				rs = statement.executeQuery("select * from songs where artist like '%"+recherche+"%' or title like '%"+recherche+"%'");
			else if(artistCheck)
				rs = statement.executeQuery("select * from songs where artist like '%"+recherche+"%'");
			else if(titleCheck)
				rs = statement.executeQuery("select * from songs where title like '%"+recherche+"%'");
			else
				rs = statement.executeQuery("select * from songs");

			while(rs.next()){
				// read the result set
				ArrayList<StructureMusique> tmp=new ArrayList<StructureMusique>();
				Musique mus=new Musique(rs.getString("title"), rs.getString("album"), rs.getString("artist"), rs.getString("genre"), rs.getString("year"), rs.getString("duration"));
				tmp.add(mus.getTitle());
				tmp.add(mus.getAlbum());
				tmp.add(mus.getArtist());
				tmp.add(mus.getGenre());
				tmp.add(mus.getYear());
				tmp.add(mus.getDuration());
				l.add(tmp.toArray());
			}
			Object retour[][]=new Object[l.size()][];
			for(int i=0;i<l.size();++i)
				retour[i]=l.get(i);
			
			statement.close();
			connection.close();
			return retour;
		}catch(SQLException e){
			// if the error message is "out of memory", 
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}finally{
			try{
				if(connection != null)
					connection.close();
			}catch(SQLException e){
				// connection close failed.
				System.err.println(e);
			}
		}
		return null;
	}

	private static Connection initConnection() {
		// load the sqlite-JDBC driver using the current class loader
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			System.err.println("Install lib sqlite jar");
			System.exit(1);
		}
		// create a database connection
		String s = "mp3database.sqlite";
		File f=new File(s);
		try {
			return DriverManager.getConnection("jdbc:sqlite:"+f.getAbsolutePath());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * ici est définit les actions qu'on peut faire sur la bibliotheque
	 * */
	public static List<javax.swing.Action> getActions() {
		ArrayList<Action> retour=new ArrayList<Action>();
			retour.add(new AjouterListeLecteur());
		return retour;
	}
}
