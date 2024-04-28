package modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClientPart {
    private final StringProperty email;
    private final StringProperty nom;
    private final StringProperty prenom;
    private final StringProperty numeroTel;
    private final StringProperty password;

    public ClientPart(String email, String nom, String prenom, String numeroTel, String password) {
        this.email = new SimpleStringProperty(email);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.numeroTel = new SimpleStringProperty(numeroTel);
        this.password = new SimpleStringProperty(password);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public StringProperty prenomProperty() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getNumeroTel() {
        return numeroTel.get();
    }

    public StringProperty numeroTelProperty() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel.set(numeroTel);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
