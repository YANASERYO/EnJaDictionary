package model;

//1行単位で読むことにしました
//https://docs.oracle.com/javase/jp/8/docs/api/java/io/BufferedReader.html
//https://www.sejuku.net/blog/49981
import java.io.BufferedReader;
import java.io.IOException;
//ファイル読み込み用
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MakeEjDict {
	public static void main(String[] args) {
		String JDBC_URL = "jdbc:postgresql://localhost:5432/dictionary";
//		String JDBC_URL = System.getenv("DB_URL");
		String DB_USER = "postgres";
//		String DB_USER = System.getenv("DB_USER");
		String DB_PASS = "ryo19960925";
//		String DB_PASS = System.getenv("DB_PASS");
		Path path = Path.of("ejdic-hand-utf8.txt");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("ドライバを読めませんでした");
		}
		String sql = "INSERT INTO ejdict(word, explanation) VALUES(?, ?)";
		try (
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			PreparedStatement pStmt = conn.prepareStatement(sql);
			BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)
		) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split("\t", 2);
				if (parts.length < 2) {
					continue;
				}
				String word = parts[0];
				String explanation = parts[1];
				pStmt.setString(1, word);
				pStmt.setString(2, explanation);
				pStmt.executeUpdate();
			}
		} catch (IOException e) {
			System.out.println("ファイル読み込みでエラー");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB登録でエラー");
			e.printStackTrace();
		}
	}
}