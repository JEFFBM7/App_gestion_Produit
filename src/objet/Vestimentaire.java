package objet;

public class Vestimentaire extends Produit {
    private String taille;

    public Vestimentaire(String id, String nom, double prix, int quantite, String taille) {
        super(id, nom, prix, quantite);
        this.taille = taille;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        return super.toString() + ", Vestimentaire{taille='" + taille + "'}";
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + taille;
    }
}