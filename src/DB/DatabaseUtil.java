package DB;

import java.sql.*;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3307/Gestion de Produit";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            // Optional: Explicitly load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}