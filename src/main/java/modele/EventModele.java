package modele;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventModele {
    private int id;
    private String nom;
    private String description;
    private String horaireDebut;
    private String horaireFin;
    private int capacite;
    private String type;
    private Date dateEvent;

    // Constructeur(s), getters et setters

    // Méthode pour ajouter un événement dans la base de données
    public void ajouterEvent(EventClass event) throws SQLException {
        Connection connexion = null;
        PreparedStatement statement = null;
        try {
            connexion = DataBase.getConnection();
            String query = "INSERT INTO event (nom, description, horaireDebut, horaireFin, capacite, type, dateEvent) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connexion.prepareStatement(query);
            // Utiliser les valeurs de l'objet EventClass passé en paramètre
            statement.setString(1, event.getNom());
            statement.setString(2, event.getDescription());
            statement.setDate(3, new java.sql.Date(event.getHoraireDebut().getTime()));
            statement.setDate(4, new java.sql.Date(event.getHoraireFin().getTime()));
            statement.setInt(5, event.getCapacite());
            statement.setString(6, event.getType());
            statement.setDate(7, new java.sql.Date(event.getDateEvent().getTime()));
            statement.executeUpdate();
            System.out.println("Événement ajouté avec succès.");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connexion != null) {
                connexion.close();
            }
        }
    }


    // Méthode pour récupérer tous les événements de la base de données
    public static List<EventClass> getAllEvents() throws SQLException {
        List<EventClass> events = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connexion = DataBase.getConnection();
            String query = "SELECT * FROM event";
            statement = connexion.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                EventClass event = new EventClass();
                event.setId(resultSet.getInt("id"));
                event.setNom(resultSet.getString("nom"));
                event.setDescription(resultSet.getString("description"));
                event.setHoraireDebut(resultSet.getDate("horaireDebut"));
                event.setHoraireFin(resultSet.getDate("horaireFin"));
                event.setCapacite(resultSet.getInt("capacite"));
                event.setType(resultSet.getString("type"));
                event.setDateEvent(resultSet.getDate("dateEvent"));
                events.add(event);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connexion != null) {
                connexion.close();
            }
        }
        return events;
    }

    // Méthode pour mettre à jour un événement dans la base de données
    public void updateEvent() throws SQLException {
        Connection connexion = null;
        PreparedStatement statement = null;
        try {
            connexion = DataBase.getConnection();
            String query = "UPDATE event SET nom = ?, description = ?, horaireDebut = ?, horaireFin = ?, capacite = ?, type = ?, dateEvent = ? WHERE id = ?";
            statement = connexion.prepareStatement(query);
            statement.setString(1, this.nom);
            statement.setString(2, this.description);
            statement.setString(3, this.horaireDebut);
            statement.setString(4, this.horaireFin);
            statement.setInt(5, this.capacite);
            statement.setString(6, this.type);
            statement.setDate(7, this.dateEvent);
            statement.setInt(8, this.id);
            statement.executeUpdate();
            System.out.println("Événement mis à jour avec succès.");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connexion != null) {
                connexion.close();
            }
        }
    }

    // Méthode pour supprimer un événement de la base de données
    public void deleteEvent() throws SQLException {
        Connection connexion = null;
        PreparedStatement statement = null;
        try {
            connexion = DataBase.getConnection();
            String query = "DELETE FROM event WHERE id = ?";
            statement = connexion.prepareStatement(query);
            statement.setInt(1, this.id);
            statement.executeUpdate();
            System.out.println("Événement supprimé avec succès.");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connexion != null) {
                connexion.close();
            }
        }
    }
}
