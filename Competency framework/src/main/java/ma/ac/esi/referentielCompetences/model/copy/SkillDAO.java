package ma.ac.esi.referentielCompetences.model.copy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SkillDAO {
    private String url = "jdbc:mysql://localhost:3306/competency_framework";
    private String username = "root";
    private String password = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void addSkill(Skill skill) {
        String sql = "INSERT INTO Skills (name, description, domain, level) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, skill.getName());
            pstmt.setString(2, skill.getDescription());
            pstmt.setString(3, skill.getDomain());
            pstmt.setString(4, skill.getLevel());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
