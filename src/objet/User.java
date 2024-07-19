package objet;

import DB.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    private String email;
    private String nom;
    private String poste_nom;
    private String motdepasse;

    public User(String email, String nom, String poste_nom, String motdepasse) {
        this.email = email;
        this.nom = nom;
        this.poste_nom = poste_nom;
        this.motdepasse = motdepasse;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getPoste_nom() {
        return poste_nom;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public static boolean saveToDatabase(Connection connection, User user) {
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO users (email, nom, poste_nom, motdepasse) VALUES (?, ?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getNom());
            stmt.setString(3, user.getPoste_nom());
            stmt.setString(4, user.getMotdepasse());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DatabaseUtil.close(null, stmt, null);
        }
    }
}