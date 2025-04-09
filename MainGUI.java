import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.*;

public class MainGUI extends JFrame {
    private final VolDAO dao ;
    private JTextField serialField;
    private JLabel resultLabel;

    public MainGUI() {
        this.dao=new VolDAO();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Vérification d'objets volés - MySQL");
        setSize(500, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel de recherche
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        serialField = new JTextField(20);
        JButton searchButton = new JButton("Vérification");
        searchButton.addActionListener(this::handleSearch);
        searchPanel.add(new JLabel("Numéro de série :"));
        searchPanel.add(serialField);
        searchPanel.add(searchButton);

        // Résultat
        resultLabel = new JLabel(" ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Bouton Admin
        JButton adminButton = new JButton("Mode Admin");
        adminButton.addActionListener(e -> {
            try {
                new AdminGUI(dao).setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Erreur: " + ex.getMessage(),
                        "Erreur Admin",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Assemblage
        add(searchPanel, BorderLayout.NORTH);
        add(resultLabel, BorderLayout.CENTER);
        add(adminButton, BorderLayout.SOUTH);
    }

    private void handleSearch(ActionEvent e) {
        String serial = serialField.getText().trim();
        if (serial.isEmpty()) {
            resultLabel.setText("Veuillez entrer un numéro de série");
            return;
        }

        try {
            Vol item = dao.searchByNumeroserie(serial);
            if (item != null) {
                resultLabel.setText("<html>Objet signalé volé !<br>Contact: " + item.getOwnerContact());
                resultLabel.setForeground(Color.RED);
            } else {
                resultLabel.setText("aucune correspondance trouvee");
                resultLabel.setForeground(Color.BLACK);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erreur base de données: " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI app = new MainGUI();
            app.setVisible(true);
        });
    }
}