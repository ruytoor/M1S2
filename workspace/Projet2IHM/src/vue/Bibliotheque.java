package vue;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.JTunes;
import structureDeDonnees.Musique;
import structureDeDonnees.StructureMusique;
import controleur.AjouterListeAction;

/**
 * gestion de la bibliotheque de musique
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class Bibliotheque {

	public static String[] columnNames= new String[]{"title","album","artist","genre"};


	/**
	 * creer la table contenant les morceaux de musique de la base de donnees suivant les criteres de recherche. Si il y a aucune
	 * recherche, la base retourne tout.
	 * @param recherche le mot a rechercher dans la base de donnees
	 * @param artistCheck si true, recherche le mot que dans les artistes
	 * @param titleCheck si true, recherche le mot que dans les titres
	 * @return la table des morceaux de musique
	 */
	public static JTable makeMeOneBibliotheque(String recherche, boolean artistCheck, boolean titleCheck){
		//new DefaultTableModel(); voir plus tard
		final JTable biblio= new JTable(Bibliotheque.recherche(recherche,artistCheck,titleCheck), Bibliotheque.columnNames){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) { 
				return false; 
			}
		};
		biblio.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				for(Action a:JTunes.listActionBibliotheque){
					if(a.isEnabled()!=!biblio.getSelectionModel().isSelectionEmpty())
						a.setEnabled(!biblio.getSelectionModel().isSelectionEmpty());
				}
			}
		});
		return biblio;
	}

	/**
	 * 
	 * @param recherche le mot a recherche dans la base de donnees
	 * @param artistCheck si true, recherche dans artist
	 * @param titleCheck si true, recherche dans title
	 * @return la base de donnee dans un tableau d'objet
	 */
	public synchronized static Object[][] recherche(String recherche, boolean artistCheck, boolean titleCheck){
		Connection connection =initConnection();
		try{
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rs;
			ArrayList<Object[]> l=new ArrayList<Object[]>();
			if(artistCheck&&titleCheck)
				rs = statement.executeQuery("select * from songs where artist like '%"+recherche+"%' or title like '%"+recherche+"%'");
			else if(artistCheck)
				rs = statement.executeQuery("select * from songs where artist like '%"+recherche+"%'");
			else if(titleCheck)
				rs = statement.executeQuery("select * from songs where title like '%"+recherche+"%'");
			else{
				if (recherche.length()==0)
					rs = statement.executeQuery("select * from songs");
				else{
					String requete = "select * from songs where ";
					for (int i= 0;i<columnNames.length-1;i++)
						requete +=columnNames[i]+" like '%"+recherche+"%' or ";
					requete += "duration like '%"+recherche+"%'";
					rs = statement.executeQuery(requete);
				}
			}


			while(rs.next()){
				ArrayList<StructureMusique> tmp=new ArrayList<StructureMusique>();
				Musique mus=new Musique(rs.getString("title"), rs.getString("album"), rs.getString("artist"), rs.getString("genre"), rs.getString("year"), rs.getString("duration"));
				tmp.add(mus.getTitle());
				tmp.add(mus.getAlbum());
				tmp.add(mus.getArtist());
				tmp.add(mus.getGenre());
				//tmp.add(mus.getYear());
				//tmp.add(mus.getDuration());
				l.add(tmp.toArray());
			}
			Object retour[][]=new Object[l.size()][];
			for(int i=0;i<l.size();++i)
				retour[i]=l.get(i);

			statement.close();
			connection.close();
			return retour;
		}catch(SQLException e){
			System.err.println(e.getMessage());
		}finally{
			try{
				if(connection != null)
					connection.close();
			}catch(SQLException e){
				System.err.println(e);
			}
		}
		return null;
	}

	private static Connection initConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			System.err.println("Install lib sqlite jar");
			System.exit(1);
		}
		String s = "mp3database.sqlite";
		File f=new File(s);
		try {
			return DriverManager.getConnection("jdbc:sqlite:"+f.getAbsolutePath());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * liste des actions que l'on peut effectuer sur la bibliotheque
	 * @return la liste des actions
	 */
	public static List<javax.swing.Action> getActions() {
		ArrayList<Action> retour=new ArrayList<Action>();
		retour.add(new AjouterListeAction());
		return retour;
	}
}
