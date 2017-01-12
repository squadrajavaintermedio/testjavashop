package jdbc;

import java.sql.*;

public class DbConection {

	public static void main(String args[]) {
		Connection c = null;
		Statement stmt = null;
		
		String stmt1 =           "SELECT c.id id, c.nome nome, c.idautori idautori FROM autori a, console c     WHERE a.id = c.idautori"
						+ " UNION SELECT v.id id, v.nome nome, v.idautori idautori FROM autori a, videogiochi v WHERE a.id = v.idautori"
						+ " UNION SELECT l.id id, l.nome nome, l.idautori idautori FROM autori a, libri l       WHERE a.id = l.idautori"
						+ " UNION SELECT r.id id, r.nome nome, r.idautori idautori FROM autori a, riviste r     WHERE a.id = r.idautori;";
		
		String stmt2 = "";
		String stmt3 = "";
		String stmt4 = "";
		String stmt5 = "";
		String stmt6 = "";
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\corso1\\Downloads\\sqlitestudio-3.1.1\\SQLiteStudio\\negozio");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			
			stmt = c.createStatement();
			//ResultSet rs = stmt.executeQuery("SELECT count(name) as count FROM sqlite_temp_master WHERE type='table';");
			
			ResultSet rs = stmt.executeQuery(stmt1);
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				int idautori = rs.getInt("idautori");
				System.out.println("id = " + id + ", nome = " + nome + ", idautori = " + idautori);
			}
			
			rs.close();
			stmt.close();
			c.close();
				
			} catch (Exception e) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		System.out.println("Operation done successfully");
		
	}
	

}
