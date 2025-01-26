package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PointsDBSaver {

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load H2 driver", e);
        }
    }

    private final String JDBC_URL = "jdbc:h2:~/connect4"; // Adjust path as needed
    private final String USER = "A";
    private final String PASSWORD = "";

    public  void add1point(String playerName) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            // Check if Highscores table exists, create if not
            createTableIfNotExists(conn);

            // Check if playerName exists in the table, insert if not
            insertPlayerIfNotExists(conn, playerName);

            // Update points for playerName
            updatePoints(conn, playerName);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTableIfNotExists(Connection conn) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Highscores (" +
                "name VARCHAR(20) PRIMARY KEY, " +
                "points INT, " +
                "CHECK (CHAR_LENGTH(name) >= 0)" +  // Allow empty string for name
                ")";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    private static void insertPlayerIfNotExists(Connection conn, String playerName) throws SQLException {
        String selectSQL = "SELECT name FROM Highscores WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, playerName);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                String insertSQL = "INSERT INTO Highscores (name, points) VALUES (?, 0)";
                try (PreparedStatement insertPstmt = conn.prepareStatement(insertSQL)) {
                    insertPstmt.setString(1, playerName);
                    insertPstmt.executeUpdate();
                }
            }
        }
    }

    private static void updatePoints(Connection conn, String playerName) throws SQLException {
        String updateSQL = "UPDATE Highscores SET points = points + 1 WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, playerName);
            pstmt.executeUpdate();
        }
    }

}
