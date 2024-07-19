package objet;

public class Electronique extends Produit {
    private int garantie; // durée de garantie en années

    public Electronique(String id, String nom, double prix, int quantite, int garantie) {
        super(id, nom, prix, quantite, "Electronique");
        this.garantie = garantie;
    }

    public int getGarantie() {
        return garantie;
    }

    public void setGarantie(int garantie) {
        this.garantie = garantie;
    }

    @Override
    public String toString() {
        return "Electronique{" +
                "garantie=" + garantie +
                "} " + super.toString();
    }
}