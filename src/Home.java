import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;
import objet.*;

import java.util.stream.Collectors;

public class Home extends Application {

    private final Magasin magasin = new Magasin();
    private TableView<Produit> tableView;
    private StackPane fragmentContainer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        magasin.chargerProduits();

        primaryStage.setTitle("Gestion du Magasin");

        StackPane root = new StackPane();
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Dégradé de fond
        Stop[] stops = new Stop[]{new Stop(0, Color.LIGHTSKYBLUE), new Stop(1, Color.WHITE)};
        LinearGradient backgroundGradient = new LinearGradient(0, 0, 0, 1, true, null, stops);
        root.setBackground(new Background(new BackgroundFill(backgroundGradient, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox mainContainer = new VBox(15);
        mainContainer.setPadding(new Insets(30));
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setStyle("-fx-background-color: #1e1e2f; -fx-border-radius: 20; -fx-background-radius: 10; (gaussian,  10, 0, 0, 0);");

        Label titleLabel = new Label("Gestion des Produits");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        fragmentContainer = new StackPane();
        VBox productsSection = createProjectsSection();
        VBox.setVgrow(productsSection, Priority.ALWAYS);

        fragmentContainer.getChildren().add(productsSection);

        mainContainer.getChildren().addAll(titleLabel, fragmentContainer);

        root.getChildren().add(mainContainer);

        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createProjectsSection() {
        VBox productsBox = new VBox(15);
        productsBox.setPadding(new Insets(10));
        productsBox.setAlignment(Pos.CENTER);
        productsBox.setStyle("-fx-background-color: #1E1E2F; -fx-border-radius: 10; -fx-background-radius: 10;");

        tableView = new TableView<>();
        tableView.setItems(magasin.getProduits());
        tableView.setStyle("-fx-background-color: #1E1E2F; -fx-border-radius: 10; -fx-background-radius: 10;");

        TableColumn<Produit, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Produit, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Produit, Double> priceColumn = new TableColumn<>("Prix");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));

        TableColumn<Produit, Integer> quantityColumn = new TableColumn<>("Quantité");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        TableColumn<Produit, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Produit, String> garantieColumn = new TableColumn<>("Garantie");
        garantieColumn.setCellValueFactory(new PropertyValueFactory<>("garantie"));

        TableColumn<Produit, String> dateColumn = new TableColumn<>("Date d'expiration");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateExpiration"));

        // Set the style for the column headers

        idColumn.setStyle("-fx-text-fill: black;");
        nameColumn.setStyle("-fx-text-fill: black;");
        priceColumn.setStyle("-fx-text-fill: black;");
        quantityColumn.setStyle("-fx-text-fill: black;");
        typeColumn.setStyle("-fx-text-fill: black;");
        garantieColumn.setStyle("-fx-text-fill: black;");
        dateColumn.setStyle("-fx-text-fill: black;");

        tableView.getColumns().addAll(idColumn, nameColumn, priceColumn, quantityColumn, typeColumn );
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox.setVgrow(tableView, Priority.ALWAYS);

        HBox buttonBox = createButtonBox();
        VBox.setVgrow(buttonBox, Priority.NEVER);

        productsBox.getChildren().addAll(tableView, buttonBox);
        return productsBox;
    }

    private <T> TableCell<Produit, T> createCellFactory() {
        return new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("-");
                } else {
                    setText(item.toString());
                }
            }
        };
    }

    private HBox createButtonBox() {
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER);

        Button addButton = new Button("Ajouter Produit");
        addButton.setOnAction(e -> switchFragment(createAddProductFragment()));

        Button deleteButton = new Button("Supprimer Produit");
        deleteButton.setOnAction(e -> switchFragment(createDeleteProductFragment()));

        Button modifyButton = new Button("Modifier Produit");
        modifyButton.setOnAction(e -> switchFragment(createModifyProductFragment()));

        Button searchButton = new Button("Rechercher Produit");
        searchButton.setOnAction(e -> switchFragment(createSearchProductFragment()));

        Button listButton = new Button("Lister Produits par Lettre");
        listButton.setOnAction(e -> switchFragment(createListProductsByLetterFragment()));

        buttonBox.getChildren().addAll(addButton, deleteButton, modifyButton, searchButton, listButton);
        return buttonBox;
    }

    private VBox createAddProductFragment() {
        VBox addProductBox = new VBox(15);
        addProductBox.setPadding(new Insets(10));
        addProductBox.setAlignment(Pos.CENTER);
        addProductBox.setStyle("-fx-background-color: #1E1E2F; -fx-border-radius: 10; -fx-background-radius: 10;");

        GridPane grid = createProductGrid();

        Button addButton = new Button("Ajouter");
        addButton.setOnAction(e -> {
            TextField idField = (TextField) grid.getChildren().get(1);
            TextField nomField = (TextField) grid.getChildren().get(3);
            TextField prixField = (TextField) grid.getChildren().get(5);
            TextField quantiteField = (TextField) grid.getChildren().get(7);
            ComboBox<String> typeComboBox = (ComboBox<String>) grid.getChildren().get(9);

            String id = idField.getText();
            String nom = nomField.getText();
            double prix = Double.parseDouble(prixField.getText());
            int quantite = Integer.parseInt(quantiteField.getText());
            String type = typeComboBox.getValue();
            Produit produit = createProduct(id, nom, prix, quantite, type);

            if (produit != null) {
                magasin.ajouterProduit(produit);
                tableView.setItems(FXCollections.observableArrayList(magasin.getProduits()));
                showAlert("Succès", "Produit ajouté avec succès!");
            } else {
                showAlert("Erreur", "Erreur lors de l'ajout du produit.");
            }
        });

        Button backButton = new Button("Retour");
        backButton.setOnAction(e -> switchFragment(createProjectsSection()));

        addProductBox.getChildren().addAll(grid, addButton, backButton);

        return addProductBox;
    }

    private VBox createDeleteProductFragment() {
        VBox deleteProductBox = new VBox(15);
        deleteProductBox.setPadding(new Insets(10));
        deleteProductBox.setAlignment(Pos.CENTER);
        deleteProductBox.setStyle("-fx-background-color: #1E1E2F; -fx-border-radius: 10; -fx-background-radius: 10;");

        TextField idField = new TextField();
        idField.setPromptText("Entrez l'ID du produit à supprimer");

        Button deleteButton = new Button("Supprimer");
        deleteButton.setOnAction(e -> {
            String id = idField.getText();
            Produit produit = magasin.getProduits().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
            if (produit != null) {
                magasin.supprimerProduit(id);
                tableView.setItems(FXCollections.observableArrayList(magasin.getProduits()));
                showAlert("Succès", "Produit supprimé avec succès!");
            } else {
                showAlert("Erreur", "Produit non trouvé!");
            }
        });

        Button backButton = new Button("Retour");
        backButton.setOnAction(e -> switchFragment(createProjectsSection()));

        deleteProductBox.getChildren().addAll(idField, deleteButton, backButton);

        return deleteProductBox;
    }
    private VBox createModifyBox(Produit produit) {
        VBox modifyBox = new VBox(15);
        modifyBox.setPadding(new Insets(10));
        modifyBox.setAlignment(Pos.CENTER);
        modifyBox.setStyle("-fx-background-color: #1E1E2F; -fx-border-radius: 10; -fx-background-radius: 10;");

        TextField idField = new TextField(produit.getId());
        idField.setPromptText("ID");
        idField.setDisable(true); // ID should not be editable

        TextField nomField = new TextField(produit.getNom());
        nomField.setPromptText("Nom");

        TextField prixField = new TextField(String.valueOf(produit.getPrix()));
        prixField.setPromptText("Prix");

        TextField quantiteField = new TextField(String.valueOf(produit.getQuantite()));
        quantiteField.setPromptText("Quantité");

        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("Electronique", "Alimentaire", "Vestimentaire");
        typeComboBox.setValue(produit.getType());

        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(e -> {
            produit.setNom(nomField.getText());
            produit.setPrix(Double.parseDouble(prixField.getText()));
            produit.setQuantite(Integer.parseInt(quantiteField.getText()));
            produit.setType(typeComboBox.getValue());

            magasin.modifierProduit(produit.getId(), produit);
            tableView.setItems(FXCollections.observableArrayList(magasin.getProduits()));
            showAlert("Succès", "Produit modifié avec succès!");
            switchFragment(createProjectsSection());
        });

        Button backButton = new Button("Retour");
        backButton.setOnAction(e -> switchFragment(createProjectsSection()));

        modifyBox.getChildren().addAll(idField, nomField, prixField, quantiteField, typeComboBox, saveButton, backButton);

        return modifyBox;
    }

    private VBox createModifyProductFragment() {
        VBox modifyProductBox = new VBox(15);
        modifyProductBox.setPadding(new Insets(10));
        modifyProductBox.setAlignment(Pos.CENTER);
        modifyProductBox.setStyle("-fx-background-color: #1E1E2F; -fx-border-radius: 10; -fx-background-radius: 10;");

        TextField idField = new TextField();
        idField.setPromptText("Entrez l'ID du produit à modifier");

        Button findButton = new Button("Trouver");
        findButton.setOnAction(e -> {
            String id = idField.getText();
            Produit produit = magasin.getProduits().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
            if (produit != null) {
                VBox modifyBox = createModifyBox(produit);

                switchFragment(modifyBox);
            } else {
                showAlert("Erreur", "Produit non trouvé!");
            }
        });

        Button backButton = new Button("Retour");
        backButton.setOnAction(e -> switchFragment(createProjectsSection()));

        modifyProductBox.getChildren().addAll(idField, findButton, backButton);

        return modifyProductBox;
    }

    private VBox createSearchProductFragment() {
        VBox searchProductBox = new VBox(15);
        searchProductBox.setPadding(new Insets(10));
        searchProductBox.setAlignment(Pos.CENTER);
        searchProductBox.setStyle("-fx-background-color: #1E1E2F; -fx-border-radius: 10; -fx-background-radius: 10;");

        TextField nameField = new TextField();
        nameField.setPromptText("Entrez le nom du produit à rechercher");

        Button searchButton = new Button("Rechercher");
        searchButton.setOnAction(e -> {
            String name = nameField.getText();
            Produit produit = magasin.getProduits().stream().filter(p -> p.getNom().equalsIgnoreCase(name)).findFirst().orElse(null);
            if (produit != null) {
                tableView.setItems(FXCollections.observableArrayList(produit));
            } else {
                showAlert("Erreur", "Produit non trouvé!");
            }
        });

        Button backButton = new Button("Retour");
        backButton.setOnAction(e -> switchFragment(createProjectsSection()));

        searchProductBox.getChildren().addAll(nameField, searchButton, backButton);

        return searchProductBox;
    }

    private VBox createListProductsByLetterFragment() {
        VBox listProductsByLetterBox = new VBox(15);
        listProductsByLetterBox.setPadding(new Insets(10));
        listProductsByLetterBox.setAlignment(Pos.CENTER);
        listProductsByLetterBox.setStyle("-fx-background-color: #1E1E2F; -fx-border-radius: 10; -fx-background-radius: 10;");

        TextField letterField = new TextField();
        letterField.setPromptText("Entrez la lettre par laquelle commence le nom des produits");

        Button listButton = new Button("Lister");
        listButton.setOnAction(e -> {
            char initial = letterField.getText().toUpperCase().charAt(0);
            var produitsFiltres = magasin.getProduits().stream().filter(p -> p.getNom().toUpperCase().charAt(0) == initial).collect(Collectors.toList());
            if (!produitsFiltres.isEmpty()) {
                tableView.setItems(FXCollections.observableArrayList(produitsFiltres));
            } else {
                showAlert("Erreur", "Aucun produit trouvé pour cette lettre!");
            }
        });

        Button backButton = new Button("Retour");
        backButton.setOnAction(e -> switchFragment(createProjectsSection()));

        listProductsByLetterBox.getChildren().addAll(letterField, listButton, backButton);

        return listProductsByLetterBox;
    }

    private GridPane createProductGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField idField = new TextField();
        idField.setPromptText("ID");
        TextField nomField = new TextField();
        nomField.setPromptText("Nom");
        TextField prixField = new TextField();
        prixField.setPromptText("Prix");
        TextField quantiteField = new TextField();
        quantiteField.setPromptText("Quantité");
        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("Electronique", "Alimentaire", "Vestimentaire");

        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Nom:"), 0, 1);
        grid.add(nomField, 1, 1);
        grid.add(new Label("Prix:"), 0, 2);
        grid.add(prixField, 1, 2);
        grid.add(new Label("Quantité:"), 0, 3);
        grid.add(quantiteField, 1, 3);
        grid.add(new Label("Type:"), 0, 4);
        grid.add(typeComboBox, 1, 4);

        return grid;
    }

    private Produit createProduct(String id, String nom, double prix, int quantite, String type) {
        switch (type) {
            case "Electronique":
                return new Electronique(id, nom, prix, quantite, 2);
            case "Alimentaire":
                return new Alimentaire(id, nom, prix, quantite, "2024-12-31");
            case "Vestimentaire":
                return new Vestimentaire(id, nom, prix, quantite, "M");
            default:
                return null;
        }
    }

    private void switchFragment(Node fragment) {
        if (!fragmentContainer.getChildren().isEmpty()) {
            Node currentFragment = fragmentContainer.getChildren().get(0);

            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), currentFragment);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                fragmentContainer.getChildren().clear();
                fragmentContainer.getChildren().add(fragment);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(300), fragment);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });
            fadeOut.play();
        } else {
            fragmentContainer.getChildren().add(fragment);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), fragment);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}