import javax.swing.*;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginFrame extends JFrame {
    public AdminLoginFrame() {
        setTitle("Admin Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(100, 100, 80, 25);
        add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(200, 100, 150, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(100, 150, 80, 25);
        add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(200, 150, 150, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(300, 200, 80, 25);
        add(loginButton);
        
        JButton Return = new JButton("Return");
        Return.setBounds(150,200,80,25);
        add(Return);
        
        Return.addActionListener(e -> {
        	new HomeFrame();
            dispose();
        });
        

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (DatabaseHelper.validateAdmin(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                new AdminDashboardFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        });
        setLocationRelativeTo(null);

        setVisible(true);
    }
}
