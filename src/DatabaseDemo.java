import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DatabaseDemo extends JFrame implements ActionListener {
    private JTextField nameField, ageField, idField;
    private JButton addButton, updateButton, deleteButton;
    private JLabel messageLabel;
    private Connection connection;

    public DatabaseDemo() {
        super("Database Demo");

        // Create text fields
        nameField = new JTextField(20);
        ageField = new JTextField(20);
        idField = new JTextField(20);

        // Create buttons
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);

        // Create message label
        messageLabel = new JLabel("");

        // Add components to window
        setLayout(new GridLayout(4, 2));
        add(new Label("Name:"));
        add(nameField);
        add(new Label("Age:"));
        add(ageField);
        add(new Label("ID:"));
        add(idField);
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(messageLabel);

        // Connect to MySQL database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/harry", "root", "Iamindian@13");
        } catch (Exception ex) {
            messageLabel.setText("Error connecting to database: " + ex.getMessage());
        }

        // Set window size and visibility
        setSize(400, 200);
        getForeground();
        setBackground(Color.yellow);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Statement statement = connection.createStatement();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            int id = Integer.parseInt(idField.getText());

            if (e.getSource() == addButton) {

                statement.executeUpdate("INSERT INTO mytable (name, age, id) VALUES ('" + name + "', '" + age + "', " + id + ")");
                messageLabel.setText("Record added successfully");
            } else if (e.getSource() == updateButton) {
                statement.executeUpdate("UPDATE mytable SET name = '" + name + "', age = '" + age + "' WHERE id = " + id);
                messageLabel.setText("Record updated successfully");
            } else if (e.getSource() == deleteButton) {
                statement.executeUpdate("DELETE FROM mytable WHERE id = " + id);
                messageLabel.setText("Record deleted successfully");
            }
        } catch (Exception ex) {
            messageLabel.setText("Error performing operation: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new DatabaseDemo();
    }
}



