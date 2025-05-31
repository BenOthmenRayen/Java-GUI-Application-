import javax.swing.*;

public class UserSubscribeFrame extends JFrame {
    public UserSubscribeFrame() {
        setTitle("User Subscription");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(20, 30, 80, 25);
        add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(100, 30, 150, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(20, 70, 80, 25);
        add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100, 70, 150, 25);
        add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(50, 110, 80, 25);
        add(registerButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 110, 80, 25);
        add(backButton);

        
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (DatabaseHelper.addUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Registration successful!");
                new UserLoginFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists. Please try a different one.");
            }
        });

        
        backButton.addActionListener(e -> {
            new UserLoginFrame();
            dispose();
        });
        setLocationRelativeTo(null);

        setVisible(true);
    }
}
