package objet;

public class Vestimentaire extends Produit {
    private String taille; // taille du vêtement

    public Vestimentaire(String id, String nom, double prix, int quantite, String taille) {
        super(id, nom, prix, quantite, "Vestimentaire");
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
        return "Vestimentaire{" +
                "taille='" + taille + '\'' +
                "} " + super.toString();
    }
}
