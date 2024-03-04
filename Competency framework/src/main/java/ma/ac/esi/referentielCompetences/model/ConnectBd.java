package ma.ac.esi.referentielCompetences.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectBd {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/competencylogin";
    private static final String USER = "login";
    private static final String PASS = "password";

    public User FindUser(String login, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            // Charger le driver JDBC pour MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Établir la connexion à la base de données
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Requête SQL pour rechercher l'utilisateur
            String sql = "SELECT * FROM users WHERE login = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            // Si un utilisateur correspondant est trouvé, créer un objet User
            if (rs.next()) {
                user = new User(rs.getString("login"), rs.getString("password"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Gérer les exceptions correctement dans votre application
        } finally {
            // Fermer les ressources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Gérer les exceptions correctement dans votre application
            }
        }

        return user;
    }
}
