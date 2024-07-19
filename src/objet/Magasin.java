package objet;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Magasin {
    private HashMap<String, Produit> produits = new HashMap<>();

    public void ajouterProduit(Produit produit) {
        produits.put(produit.getId(), produit);
        sauvegarderProduits();
    }

    public void supprimerProduit(String id) {
        produits.remove(id);
        sauvegarderProduits();
    }

    public void modifierProduit(String id, Produit produit) {
        produits.put(id, produit);
        sauvegarderProduits();
    }

    public Produit rechercherProduitParNom(String nom) {
        for (Produit produit : produits.values()) {
            if (produit.getNom().equalsIgnoreCase(nom)) {
                return produit;
            }
        }
        return null;
    }

    public List<Produit> listerProduitsParLettre(char lettre) {
        List<Produit> resultat = new ArrayList<>();
        for (Produit produit : produits.values()) {
            if (produit.getNom().charAt(0) == lettre) {
                resultat.add(produit);
            }
        }
        return resultat;
    }

    public int nombreProduitsEnStock() {
        return produits.size();
    }

    public void sauvegarderProduits() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("produits.txt"))) {
            for (Produit produit : produits.values()) {
                writer.println(produit);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargerProduits() {
        try (BufferedReader reader = new BufferedReader(new FileReader("produits.txt"))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                // Parsing logic to recreate products and add to produits map
                // Example: Produit{id='1', nom='Laptop', prix=1000.0, quantite=10}, Electronique{garantie=2}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exporterProduitsEnCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("produits.csv"))) {
            writer.println("ID,Nom,Prix,Quantite,Type,AttributSpécifique");
            for (Produit produit : produits.values()) {
                writer.println(produit.toCSV());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}