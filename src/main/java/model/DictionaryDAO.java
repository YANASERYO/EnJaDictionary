package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryDAO {
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/dictionary";
//	private final String JDBC_URL = System.getenv("DB_URL");
	private final String DB_USER = "postgres";
//	private final String DB_USER = System.getenv("DB_USER");
	private final String DB_PASS = "psql";
//	private final String DB_PASS = System.getenv("DB_PASS");
	public List<EjDict> findByWord(String word, String mean,int maxCount) {
		List<EjDict> dictList = new ArrayList<>();

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("ドライバを込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT id, word, explanation FROM ejdict "
					+ "WHERE LOWER(word) LIKE LOWER(?) AND explanation LIKE ? ORDER BY word LIMIT ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, word + "%");
			pStmt.setString(2, "%" + mean + "%");
			pStmt.setInt(3, maxCount);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String resultWord = rs.getString("word");
				String explanation = rs.getString("explanation");
				EjDict dict = new EjDict(id, resultWord, explanation);
				dictList.add(dict);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return dictList;
	}
}