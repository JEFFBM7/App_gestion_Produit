package objet;

import DB.DatabaseUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Magasin {
    private final List<Produit> produits = new ArrayList<>();

    public void chargerProduits() {
        produits.clear();
        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM produits")) {

            while (rs.next()) {
                Produit produit = new Produit(
                        rs.getString("id"),
                        rs.getString("nom"),
                        rs.getDouble("prix"),
                        rs.getInt("quantite"),
                        rs.getString("type")
                );
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Produit> getProduits() {
        return FXCollections.observableArrayList(produits);
    }

    public void ajouterProduit(Produit produit) {
        produits.add(produit);
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "INSERT INTO produits (id, nom, prix, quantite, type) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, produit.getId());
            stmt.setString(2, produit.getNom());
            stmt.setDouble(3, produit.getPrix());
            stmt.setInt(4, produit.getQuantite());
            stmt.setString(5, produit.getType());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerProduit(String id) {
        produits.removeIf(produit -> produit.getId().equals(id));
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM produits WHERE id = ?")) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierProduit(String id, Produit newProduit) {
        for (int i = 0; i < produits.size(); i++) {
            if (produits.get(i).getId().equals(id)) {
                produits.set(i, newProduit);
                break;
            }
        }
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "UPDATE produits SET nom = ?, prix = ?, quantite = ?, type = ? WHERE id = ?")) {
            stmt.setString(1, newProduit.getNom());
            stmt.setDouble(2, newProduit.getPrix());
            stmt.setInt(3, newProduit.getQuantite());
            stmt.setString(4, newProduit.getType());
            stmt.setString(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produit rechercherProduitParNom(String nom) {
        return produits.stream().filter(produit -> produit.getNom().equals(nom)).findFirst().orElse(null);
    }

    public List<Produit> listerProduitsParLettre(char lettre) {
        List<Produit> result = new ArrayList<>();
        for (Produit produit : produits) {
            if (produit.getNom().startsWith(String.valueOf(lettre))) {
                result.add(produit);
            }
        }
        return result;
    }

    public int nombreProduitsEnStock() {
        return produits.size();
    }

    public List<Produit> getAllProduits() {
        return produits;
    }
}