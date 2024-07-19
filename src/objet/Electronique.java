package objet;

public class Electronique extends Produit {
    private int garantie;

    public Electronique(String id, String nom, double prix, int quantite, int garantie) {
        super(id, nom, prix, quantite);
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
        return super.toString() + ", Electronique{garantie=" + garantie + "}";
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + garantie;
    }
}