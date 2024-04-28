package modele;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    // Informations de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/javafxJO";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";

    // Méthode pour établir la connexion à la base de données
    public static Connection getConnection() {
        try {
            // Chargement du pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Établissement de la connexion
            Connection connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
            System.out.println("Connexion à la base de données réussie !");
            return connexion;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            return null;
        }
    }
}
