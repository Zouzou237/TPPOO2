import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Date;

public class AdminGUI extends JFrame {
    private final VolDAO dao;
    private JTextField serialField, typeField, contactField;
    private JTextArea descArea;

    public AdminGUI(VolDAO dao) {
        this.dao = dao;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Administration - MySQL");
        setSize(450, 350);
        setLayout(new GridLayout(5, 2, 10, 10));

        serialField = new JTextField();
        typeField = new JTextField();
        descArea = new JTextArea(3, 20);
        contactField = new JTextField();

        add(new JLabel("Numéro de série*:"));
        add(serialField);
        add(new JLabel("Type d'objet*:"));
        add(typeField);
        add(new JLabel("Description:"));
        add(new JScrollPane(descArea));
        add(new JLabel("numero a contacter:"));
        add(contactField);

        // Bouton de soumission
        JButton submitButton = new JButton("Enregistrer");
        submitButton.addActionListener(this::handleSubmit);
        add(new JLabel());
        add(submitButton);
    }

    private void handleSubmit(ActionEvent e) {
        String serial = serialField.getText().trim();
        String type = typeField.getText().trim();
        String contact = contactField.getText().trim();

        if (serial.isEmpty() || type.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Les champs marqués d'un * sont obligatoires",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Vol item = new Vol(
                    serial,
                    type,
                    descArea.getText(),
                    contact,
                    new Date()
            );

            dao.addItem(item);
            JOptionPane.showMessageDialog(this,
                    "Objet enregistré avec succès !",
                    "Succès",
                    JOptionPane.INFORMATION_MESSAGE);

            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erreur base de données: " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        serialField.setText("");
        typeField.setText("");
        descArea.setText("");
        contactField.setText("");
    }
}