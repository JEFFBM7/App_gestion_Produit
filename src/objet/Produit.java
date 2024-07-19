package objet;

public class Produit {
private String id;
private String nom;
private double prix;
private int quantite;

public Produit(String id, String nom, double prix, int quantite) {
    this.id = id;
    this.nom = nom;
    this.prix = prix;
    this.quantite = quantite;
}

public String getId() {
    return id;
}

public String getNom() {
    return nom;
}

public double getPrix() {
    return prix;
}

public int getQuantite() {
    return quantite;
}

public void setNom(String nom) {
    this.nom = nom;
}

public void setPrix(double prix) {
    this.prix = prix;
}

public void setQuantite(int quantite) {
    this.quantite = quantite;
}

@Override
public String toString() {
    return "Produit{id='" + id + "', nom='" + nom + "', prix=" + prix + ", quantite=" + quantite + "}";
}

public String toCSV() {
    return id + "," + nom + "," + prix + "," + quantite;
}
}