package org.example.javafxjo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.ClientDAO;
import modele.ClientPart;
import modele.ClientPro;
import modele.DataBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private Button connected;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Button created;

    @FXML
    private TextField emailPart;

    @FXML
    private TextField nom;

    @FXML
    private TextField numTelPart;

    @FXML
    private TextField passwordPart;

    @FXML
    private TextField prenom;

    @FXML
    private Button createdAc;

    @FXML
    private Button accountPro;

    @FXML
    private TextField numSiret;




    @FXML
    void handleChangeView() {
        // Charger la nouvelle vue depuis le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("creationClient.fxml"));
        try {
            Parent root = loader.load();
            // Créer une nouvelle scène avec la nouvelle vue chargée
            Scene scene = new Scene(root);
            // Obtenir la scène actuelle à partir du bouton
            Stage stage = (Stage) connected.getScene().getWindow();
            // Définir la nouvelle scène avec la nouvelle vue
            stage.setScene(scene);
            // Afficher la nouvelle scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleChangeView2() {
        // Charger la nouvelle vue depuis le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        try {
            Parent root = loader.load();
            // Créer une nouvelle scène avec la nouvelle vue chargée
            Scene scene = new Scene(root);
            // Obtenir la scène actuelle à partir du bouton
            Stage stage = (Stage) connected.getScene().getWindow();
            // Définir la nouvelle scène avec la nouvelle vue
            stage.setScene(scene);
            // Afficher la nouvelle scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void handleChangeView3() {
        // Charger la nouvelle vue depuis le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("creationClientPro.fxml"));
        try {
            Parent root = loader.load();
            // Créer une nouvelle scène avec la nouvelle vue chargée
            Scene scene = new Scene(root);
            // Obtenir la scène actuelle à partir du bouton
            Stage stage = (Stage) connected.getScene().getWindow();
            // Définir la nouvelle scène avec la nouvelle vue
            stage.setScene(scene);
            // Afficher la nouvelle scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleConnection() {
        // Récupération des informations saisies par l'utilisateur
        String userEmail = email.getText();
        String userPassword = password.getText();

        // Tentative de connexion à la base de données
        Connection connexion = DataBase.getConnection();
        if (connexion != null) {
            System.out.println("Connexion réussie pour l'utilisateur : " + userEmail);
            handleChangeView4();
        } else {
            System.out.println("Échec de la connexion pour l'utilisateur : " + userEmail);
            // Vous pouvez ajouter ici des actions à effectuer en cas d'échec de la connexion
        }
    }
    @FXML
    void handleChangeView4() {
        // Charger la nouvelle vue depuis le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("event-read.fxml"));
        try {
            Parent root = loader.load();
            // Créer une nouvelle scène avec la nouvelle vue chargée
            Scene scene = new Scene(root);
            // Obtenir la scène actuelle à partir du bouton
            Stage stage = (Stage) connected.getScene().getWindow();
            // Définir la nouvelle scène avec la nouvelle vue
            stage.setScene(scene);
            // Afficher la nouvelle scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void handleCreateAccount() {
        // Récupération des informations saisies par l'utilisateur
        String userEmail = emailPart.getText();
        String userNom = nom.getText();
        String userPrenom = prenom.getText();
        String userNumTel = numTelPart.getText();
        String userPassword = passwordPart.getText();

        // Vérification si l'email est vide
        if (userEmail.isEmpty()) {
            System.out.println("Veuillez saisir une adresse email.");
            return;
        }

        // Vérification si le mot de passe est vide
        if (userPassword.isEmpty()) {
            System.out.println("Veuillez saisir un mot de passe.");
            return;
        }

        // Tentative de connexion à la base de données
        Connection connexion = DataBase.getConnection();
        if (connexion != null) {
            System.out.println("Connexion réussie.");

            // Création d'un objet ClientPart avec les informations saisies par l'utilisateur
            ClientPart clientPart = new ClientPart(userEmail, userNom, userPrenom, userNumTel, userPassword);

            // Création d'un objet ClientDAO pour interagir avec la base de données
            ClientDAO clientDAO = new ClientDAO(connexion);

            try {
                // Ajout du client dans la base de données
                clientDAO.ajouterClientPart(clientPart);
                System.out.println("Compte client particulier créé avec succès.");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la création du compte client particulier.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Échec de la connexion à la base de données.");
        }
    }
    @FXML
    void handleCreateAccountPro() {
        // Récupération des informations saisies par l'utilisateur
        String userEmail = emailPart.getText();
        String userNom = nom.getText();
        String userPrenom = prenom.getText();
        String userNumTel = numTelPart.getText();
        String userPassword = passwordPart.getText();
        String userNumSiret = numSiret.getText();


        // Vérification si l'email est vide
        if (userEmail.isEmpty()) {
            System.out.println("Veuillez saisir une adresse email.");
            return;
        }

        // Vérification si le mot de passe est vide
        if (userPassword.isEmpty()) {
            System.out.println("Veuillez saisir un mot de passe.");
            return;
        }

        // Tentative de connexion à la base de données
        Connection connexion = DataBase.getConnection();
        if (connexion != null) {
            System.out.println("Connexion réussie.");

            // Création d'un objet ClientPart avec les informations saisies par l'utilisateur
            ClientPro clientPro = new ClientPro(userEmail, userNom, userPrenom, userNumTel, userPassword , userNumSiret);

            // Création d'un objet ClientDAO pour interagir avec la base de données
            ClientDAO clientDAO = new ClientDAO(connexion);

            try {
                // Ajout du client dans la base de données
                clientDAO.ajouterClientPro(clientPro);
                System.out.println("Compte client pro créé avec succès.");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la création du compte client pro.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Échec de la connexion à la base de données.");
        }
    }
}
