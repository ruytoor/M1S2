package vue;

import javax.swing.JTable;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bibliotheque {

	public static String[] columnNames= new String[]{"album","artist", "title","genre","year","duration"};


	public static JTable makeMeOneBibliotheque(int numColumn, String recherche){
		//new DefaultTableModel(); voir plus tard
		return new JTable(Bibliotheque.returnALL(numColumn,recherche), new String[]{"album","artist", "title","genre","year","duration"}){
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) { 
				return false; 
			}
		};
	}

	/**
	 * 
	 * @param numColumn si -1 => tout, si 12 => artist et title, si 1 => artist, si 2 => title
	 * @param recherche le mot a recherche dans la base de donnees
	 * @return
	 */
	public static Object[][] returnALL(int numColumn, String recherche){
		// load the sqlite-JDBC driver using the current class loader
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Connection connection = null;
		try{
			// create a database connection
			ArrayList<Object[]> l=new ArrayList<Object[]>();
			String s = "mp3database.sqlite";
			File f=new File(s);
			System.out.println(f.isFile()+"  "+f.getAbsolutePath());
			connection = DriverManager.getConnection("jdbc:sqlite:"+f.getAbsolutePath());
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			ResultSet rs;
			switch (numColumn) {
			case 12:
				rs = statement.executeQuery("select * from songs where artist like '%"+recherche+"%' or title like '%"+recherche+"%'");
				break;
			case 1:
				rs = statement.executeQuery("select * from songs where artist like '%"+recherche+"%'");
				break;
			case 2:
				rs = statement.executeQuery("select * from songs where title like '%"+recherche+"%'");
				break;
			default:
				rs = statement.executeQuery("select * from songs");
				break;
			}
			while(rs.next()){
				// read the result set
				ArrayList<String> tmp=new ArrayList<>();
				tmp.add(rs.getString("album"));
				tmp.add(rs.getString("artist"));
				tmp.add(rs.getString("title"));
				tmp.add(rs.getString("genre"));
				tmp.add(rs.getString("year"));
				tmp.add(rs.getString("duration"));
				l.add(tmp.toArray());
			}
			Object retour[][]=new Object[l.size()][];
			for(int i=0;i<l.size();++i)
				retour[i]=l.get(i);
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
}
