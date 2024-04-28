package org.example.javafxjo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modele.EventClass;
import modele.EventModele;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventController {

    @FXML
    private TableView<EventClass> tableView;

    @FXML
    private TableColumn<EventClass, String> NomEvent;

    @FXML
    private TableColumn<EventClass, String> descriptionEvent;

    @FXML
    private TableColumn<EventClass, Date> HoraireDebutEvent;

    @FXML
    private TableColumn<EventClass, Date> HoraireFinEvent;

    @FXML
    private TableColumn<EventClass, Integer> capaciteEvent;

    @FXML
    private TableColumn<EventClass, String> typeEvent;

    @FXML
    private TableColumn<EventClass, String> JourEvent;

    @FXML
    public void initialize() {
        // Initialisation des colonnes
        NomEvent.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        descriptionEvent.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        HoraireDebutEvent.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getHoraireDebut()));
        HoraireFinEvent.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getHoraireFin()));
        capaciteEvent.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCapacite()).asObject());
        typeEvent.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));

        // Formater la date en une chaîne de caractères avec SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        JourEvent.setCellValueFactory(cellData -> {
            Date date = cellData.getValue().getDateEvent();
            String formattedDate = (date != null) ? dateFormat.format(date) : "";
            return new SimpleStringProperty(formattedDate);
        });

    }


    @FXML
    private void ajouterEvent() {
        EventModele eventModele = new EventModele();
        // Récupérer les valeurs des champs de texte
        String nom = NomEvent.getText();
        String description = descriptionEvent.getText();
        Date horaireDebut = HoraireDebutEvent.getCellData(0);
        Date horaireFin = HoraireFinEvent.getCellData(0);
        int capacite = capaciteEvent.getCellData(0);
        String type = typeEvent.getText();

        // Créer un nouvel événement avec les valeurs saisies
        EventClass nouvelEvent = new EventClass();
        nouvelEvent.setNom(nom);
        nouvelEvent.setDescription(description);
        nouvelEvent.setHoraireDebut(horaireDebut);
        nouvelEvent.setHoraireFin(horaireFin);
        nouvelEvent.setCapacite(capacite);
        nouvelEvent.setType(type);

        try {
            // Ajouter l'événement à la base de données
            eventModele.ajouterEvent(nouvelEvent);
            // Rafraîchir la TableView pour afficher le nouvel événement
            // Effacer les champs de texte après l'ajout
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'erreur de manière appropriée
        }
    }




}
