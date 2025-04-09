import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VolDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/Vol_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public VolDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.err.println("echec sale enfoire");
        }
    }

    public void addItem(Vol item) {
        String sql = "INSERT INTO Vol (Numeroserie, type, description, ownerContact) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getNumeroserie());
            stmt.setString(2, item.getType());
            stmt.setString(3, item.getDescription());
            stmt.setString(4, item.getOwnerContact());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout en BDD", e);
        }
    }

    public Vol searchByNumeroserie(String Numeroserie) {
        String sql = "SELECT * FROM Vol WHERE Numeroserie = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Numeroserie);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Vol(
                        rs.getString("Numeroserie"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getString("ownerContact"),
                        rs.getTimestamp("date_reported")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de recherche en BDD", e);
        }
    }

    public List<Vol> getAllItems() {
        List<Vol> items = new ArrayList<>();
        String sql = "SELECT * FROM vol";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                items.add(new Vol(
                        rs.getString("Numeroserie"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getString("ownerContact"),
                        rs.getTimestamp("date_reported")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de lecture en BDD", e);
        }
        return items;
    }
}