package objet;

public class Alimentaire extends Produit {
    private String dateExpiration;

    public Alimentaire(String id, String nom, double prix, int quantite, String dateExpiration) {
        super(id, nom, prix, quantite);
        this.dateExpiration = dateExpiration;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    @Override
    public String toString() {
        return super.toString() + ", Alimentaire{dateExpiration='" + dateExpiration + "'}";
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + dateExpiration;
    }
}