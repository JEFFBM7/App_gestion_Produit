import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import objet.User;
import DB.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Start with the Sign In page
        Scene signInScene = createSignInScene();

        primaryStage.setScene(signInScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    private Scene createSignInScene() {
        // Create the main container
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));

        // Set dark background color
        root.setBackground(new Background(new BackgroundFill(Color.rgb(10, 10, 30), CornerRadii.EMPTY, Insets.EMPTY)));

        // Create the form container with rounded corners and dark theme
        VBox formContainer = new VBox(15);
        formContainer.setPadding(new Insets(30));
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setStyle("-fx-background-color: #1e1e2f; -fx-border-radius: 20; -fx-background-radius: 20;");

        // Create form elements
        Label iconLabel = new Label("GESTION DE PRODUITS");
        iconLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #ccc;");

        Label formTitle = new Label("Sign in with email");
        formTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #ccc;");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setStyle("-fx-background-color: #2e2e4e; -fx-text-fill: #ccc; -fx-border-color: #4a4a6a; -fx-border-radius: 5;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: #2e2e4e; -fx-text-fill: #ccc; -fx-border-color: #4a4a6a; -fx-border-radius: 5;");

        Button signInButton = new Button("Get Started");
        signInButton.setStyle("-fx-background-color: #3a3a5e; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-border-radius: 10; -fx-background-radius: 10;");
        signInButton.setMaxWidth(Double.MAX_VALUE);

        Hyperlink signUpLink = new Hyperlink("Don't have an account? Sign Up");
        signUpLink.setStyle("-fx-text-fill: #007BFF;");
        signUpLink.setOnAction(e -> primaryStage.setScene(createSignUpScene()));  // Switch to Sign Up page

        // Add elements to form container
        formContainer.getChildren().addAll(
                iconLabel,
                formTitle,
                emailField,
                passwordField,
                signInButton,
                signUpLink
        );

        // Add form container to the root
        root.getChildren().add(formContainer);

        return new Scene(root, 500, 700);
    }

    private Scene createSignUpScene() {
        // Create the main container
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));

        // Set dark background color
        root.setBackground(new Background(new BackgroundFill(Color.rgb(10, 10, 30), CornerRadii.EMPTY, Insets.EMPTY)));

        // Create the form container with rounded corners and dark theme
        VBox formContainer = new VBox(15);
        formContainer.setPadding(new Insets(30));
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setStyle("-fx-background-color: #1e1e2f; -fx-border-radius: 20; -fx-background-radius: 20;");

        // Create form elements
        Label iconLabel = new Label("GESTION DE PRODUITS");
        iconLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #ccc;");

        Label formTitle = new Label("Create your account");
        formTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #ccc;");

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First name");
        firstNameField.setStyle("-fx-background-color: #2e2e4e; -fx-text-fill: #ccc; -fx-border-color: #4a4a6a; -fx-border-radius: 5;");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last name");
        lastNameField.setStyle("-fx-background-color: #2e2e4e; -fx-text-fill: #ccc; -fx-border-color: #4a4a6a; -fx-border-radius: 5;");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setStyle("-fx-background-color: #2e2e4e; -fx-text-fill: #ccc; -fx-border-color: #4a4a6a; -fx-border-radius: 5;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: #2e2e4e; -fx-text-fill: #ccc; -fx-border-color: #4a4a6a; -fx-border-radius: 5;");

        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-background-color: #3a3a5e; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-border-radius: 10; -fx-background-radius: 10;");
        signUpButton.setMaxWidth(Double.MAX_VALUE);
        signUpButton.setOnAction(e -> {
            String email = emailField.getText();
            String nom = firstNameField.getText() + " " + lastNameField.getText();
            String poste_nom = "User"; // Default value for poste_nom
            String motdepasse = passwordField.getText();

            Connection connection = null;
            try {
                connection = DatabaseUtil.getConnection();
                if (connection == null) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de se connecter à la base de données");
                    return;
                } else {
                    User newUser = new User(email, nom, poste_nom, motdepasse);
                    if (User.saveToDatabase(connection, newUser)) {
                        showAlert(Alert.AlertType.INFORMATION, "Succès", "Utilisateur enregistré avec succès");
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible d'enregistrer l'utilisateur");
                    }
                }
            } catch (SQLException ex) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur SQL: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                DatabaseUtil.close(connection, null, null);
            }
        });

        Hyperlink signInLink = new Hyperlink("Already have an account? Sign In");
        signInLink.setStyle("-fx-text-fill: #007BFF;");
        signInLink.setOnAction(e -> primaryStage.setScene(createSignInScene()));  // Switch to Sign In page

        // Add elements to form container
        formContainer.getChildren().addAll(
                iconLabel,
                formTitle,
                firstNameField,
                lastNameField,
                emailField,
                passwordField,
                signUpButton,
                signInLink
        );

        // Add form container to the root
        root.getChildren().add(formContainer);

        return new Scene(root, 500, 700);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}