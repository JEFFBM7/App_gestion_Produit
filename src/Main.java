import objet.*;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Magasin magasin = new Magasin();
        magasin.chargerProduits();

        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Supprimer un produit");
            System.out.println("3. Modifier un produit");
            System.out.println("4. Rechercher un produit par nom");
            System.out.println("5. Lister les produits par lettre");
            System.out.println("6. Afficher le nombre de produits en stock");
            System.out.println("7. Exporter les produits en CSV");
            System.out.println("8. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    // Logique pour ajouter un produit
                    System.out.println("Type de produit (1: Electronique, 2: Alimentaire, 3: Vestimentaire):");
                    int type = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne
                    System.out.println("ID:");
                    String id = scanner.nextLine();
                    System.out.println("Nom:");
                    String nom = scanner.nextLine();
                    System.out.println("Prix:");
                    double prix = scanner.nextDouble();
                    System.out.println("Quantite:");
                    int quantite = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne

                    switch (type) {
                        case 1:
                            System.out.println("Garantie (années):");
                            int garantie = scanner.nextInt();
                            scanner.nextLine(); // Consommer la nouvelle ligne
                            magasin.ajouterProduit(new Electronique(id, nom, prix, quantite, garantie));
                            break;
                        case 2:
                            System.out.println("Date d'expiration (yyyy-mm-dd):");
                            String dateExpiration = scanner.nextLine();
                            magasin.ajouterProduit(new Alimentaire(id, nom, prix, quantite, dateExpiration));
                            break;
                        case 3:
                            System.out.println("Taille:");
                            String taille = scanner.nextLine();
                            magasin.ajouterProduit(new Vestimentaire(id, nom, prix, quantite, taille));
                            break;
                    }
                    break;
                case 2:
                    // Logique pour supprimer un produit
                    System.out.println("ID du produit à supprimer:");
                    String idSupp = scanner.nextLine();
                    magasin.supprimerProduit(idSupp);
                    break;
                case 3:
                    // Logique pour modifier un produit
                    System.out.println("ID du produit à modifier:");
                    String idModif = scanner.nextLine();
                    Produit produit = magasin.rechercherProduitParNom(idModif);
                    if (produit != null) {
                        System.out.println("Nouveau nom:");
                        String nouveauNom = scanner.nextLine();
                        System.out.println("Nouveau prix:");
                        double nouveauPrix = scanner.nextDouble();
                        System.out.println("Nouvelle quantite:");
                        int nouvelleQuantite = scanner.nextInt();
                        scanner.nextLine(); // Consommer la nouvelle ligne

                        produit.setNom(nouveauNom);
                        produit.setPrix(nouveauPrix);
                        produit.setQuantite(nouvelleQuantite);
                        magasin.modifierProduit(idModif, produit);
                    } else {
                        System.out.println("Produit non trouvé.");
                    }
                    break;
                case 4:
                    // Logique pour rechercher un produit par nom
                    System.out.println("Nom du produit à rechercher:");
                    String nomRech = scanner.nextLine();
                    Produit produitRech = magasin.rechercherProduitParNom(nomRech);
                    if (produitRech != null) {
                        System.out.println(produitRech);
                    } else {
                        System.out.println("Produit non trouvé.");
                    }
                    break;
                case 5:
                    // Logique pour lister les produits par lettre
                    System.out.println("Lettre:");
                    char lettre = scanner.nextLine().charAt(0);
                    List<Produit> produits = magasin.listerProduitsParLettre(lettre);
                    for (Produit p : produits) {
                        System.out.println(p);
                    }
                    break;
                case 6:
                    // Logique pour afficher le nombre de produits en stock
                    System.out.println("Nombre de produits en stock: " + magasin.nombreProduitsEnStock());
                    break;
                case 7:
                    // Logique pour exporter les produits en CSV
                    magasin.exporterProduitsEnCSV();
                    System.out.println("Les produits ont été exportés en produits.csv.");
                    break;
                case 8:
                    continuer = false;
                    break;
            }
        }
        scanner.close();
    }
}
