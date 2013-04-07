package vue;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bibliotheque extends JPanel {

	private JTable table;
	private String[] columnNames;

	public Bibliotheque() throws SQLException{
		ResultSet rs =connectionBase();
	 
		this.columnNames = new String[]{"album","artist", "title","genre","year","duration"};
		this.table = new JTable(rs.getRow(),rs.getFetchSize());
		System.out.println(rs.getRow()+" , "+rs.getFetchSize());
		int i = 1;
		while(rs.next()){
			
			// read the result set
			System.out.println(i + " " + rs.getString("album") + " " + rs.getString("artist") + " " +
					rs.getString("title") + " " + rs.getString("genre") + " " + rs.getString("year") + " " + rs.getString("duration"));
			i++;
		}

		this.table.setName("Bibliothèque");
		

	}

	private ResultSet connectionBase(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection connection = null;
		try
		{
			// create a database connection
			
			connection = DriverManager.getConnection("jdbc:sqlite:mp3database.sqlite");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.

			return statement.executeQuery("select * from songs");
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
