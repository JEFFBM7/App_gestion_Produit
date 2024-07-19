package objet;

public class Alimentaire extends Produit {
    private String dateExpiration; // date d'expiration au format "yyyy-MM-dd"

    public Alimentaire(String id, String nom, double prix, int quantite, String dateExpiration) {
        super(id, nom, prix, quantite, "Alimentaire");
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
        return "Alimentaire{" +
                "dateExpiration='" + dateExpiration + '\'' +
                "} " + super.toString();
    }
}
