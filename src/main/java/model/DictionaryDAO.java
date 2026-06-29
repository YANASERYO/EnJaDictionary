package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryDAO {
	private final String JDBC_URL =
			"jdbc:h2:tcp://localhost/ /example";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "psql";
	
	public List<dictionary> findAll(){
		List<dictionary>empList = new ArrayList<>();
		try {
			Class.forName("ドライバー");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException(
					"ドライバを読めませんでした");
		}
		try(Connection conn = DriverManager.getConnection(
				JDBC_URL,DB_USER,DB_PASS)){
			
			String sql = "SELECT id,word,explanation FROM ejdict";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String word = rs.getString("word");
				String explanation = rs.getString("explanation");
				dictList.add(ejdict);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return dictList;
	}

}
