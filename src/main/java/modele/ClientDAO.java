package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private final Connection connexion;

    public ClientDAO(Connection connexion) {
        this.connexion = connexion;
    }

    public void ajouterClientPart(ClientPart client) throws SQLException {
        String query = "INSERT INTO client_particulier (email, nom, prenom, numero_tel, password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connexion.prepareStatement(query)) {
            statement.setString(1, client.getEmail());
            statement.setString(2, client.getNom());
            statement.setString(3, client.getPrenom());
            statement.setString(4, client.getNumeroTel());
            statement.setString(5, client.getPassword());
            statement.executeUpdate();
        }
    }

    public void ajouterClientPro(ClientPro client) throws SQLException {
        String query = "INSERT INTO client_professionnel (email, nom, prenom, numero_tel, password, numero_siret) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connexion.prepareStatement(query)) {
            statement.setString(1, client.getEmail());
            statement.setString(2, client.getNom());
            statement.setString(3, client.getPrenom());
            statement.setString(4, client.getNumeroTel());
            statement.setString(5, client.getPassword());
            statement.setString(6, client.getSiret());
            statement.executeUpdate();
        }
    }

    public List<ClientPart> listerClientsPart() throws SQLException {
        List<ClientPart> clientsPart = new ArrayList<>();
        String query = "SELECT * FROM client_particulier";
        try (PreparedStatement statement = connexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ClientPart client = new ClientPart(
                        resultSet.getString("email"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("numero_tel"),
                        resultSet.getString("password")
                );
                clientsPart.add(client);
            }
        }
        return clientsPart;
    }

    public List<ClientPro> listerClientsPro() throws SQLException {
        List<ClientPro> clientsPro = new ArrayList<>();
        String query = "SELECT * FROM client_professionnel";
        try (PreparedStatement statement = connexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ClientPro client = new ClientPro(
                        resultSet.getString("email"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("numero_tel"),
                        resultSet.getString("password"),
                        resultSet.getString("siret")
                );
                clientsPro.add(client);
            }
        }
        return clientsPro;
    }

    // Mettre à jour les clients particuliers
    public void mettreAJourClientPart(ClientPart client) throws SQLException {
        String query = "UPDATE client_particulier SET nom = ?, prenom = ?, numero_tel = ?, password = ? WHERE email = ?";
        try (PreparedStatement statement = connexion.prepareStatement(query)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            statement.setString(3, client.getNumeroTel());
            statement.setString(4, client.getPassword());
            statement.setString(5, client.getEmail());
            statement.executeUpdate();
        }
    }

    // Mettre à jour les clients professionnels
    public void mettreAJourClientPro(ClientPro client) throws SQLException {
        String query = "UPDATE client_professionnel SET nom = ?, prenom = ?, numero_tel = ?, password = ?, siret = ? WHERE email = ?";
        try (PreparedStatement statement = connexion.prepareStatement(query)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            statement.setString(3, client.getNumeroTel());
            statement.setString(4, client.getPassword());
            statement.setString(5, client.getSiret());
            statement.setString(6, client.getEmail());
            statement.executeUpdate();
        }
    }

    // Supprimer les clients particuliers
    public void supprimerClientPart(ClientPart client) throws SQLException {
        String query = "DELETE FROM client_particulier WHERE email = ?";
        try (PreparedStatement statement = connexion.prepareStatement(query)) {
            statement.setString(1, client.getEmail());
            statement.executeUpdate();
        }
    }

    // Supprimer les clients professionnels
    public void supprimerClientPro(ClientPro client) throws SQLException {
        String query = "DELETE FROM client_professionnel WHERE email = ?";
        try (PreparedStatement statement = connexion.prepareStatement(query)) {
            statement.setString(1, client.getEmail());
            statement.executeUpdate();
        }
    }
}
