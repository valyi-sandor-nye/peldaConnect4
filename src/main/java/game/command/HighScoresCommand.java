package game.command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.Game;


public class HighScoresCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^HIGHSCORES$");
    private static final String JDBC_URL = "jdbc:h2:~/connect4";
    private static final String USER = "A";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load H2 driver", e);
        }
    }

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public void execute(Matcher matcher, Game game) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            // Check if Highscores table exists
            if (tableExists(conn, "HIGHSCORES")) {
                // List all records in Highscores table
                listHighScores(conn);
            } else {
                System.out.println("No high scores yet.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    private boolean tableExists(Connection conn, String tableName) throws SQLException {
        String checkTableSQL = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(checkTableSQL)) {
            pstmt.setString(1, tableName.toUpperCase());
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }

    private void listHighScores(Connection conn) throws SQLException {
        String selectSQL = "SELECT name, points FROM Highscores";
        try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int points = rs.getInt("points");
                System.out.println(name + ": " + points);
            }
        }
    }
}
