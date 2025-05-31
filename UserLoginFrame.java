import javax.swing.*;

public class UserLoginFrame extends JFrame {
    public UserLoginFrame() {
        setTitle("User Login");
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

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 110, 80, 25);
        add(loginButton);

        JButton subscribeButton = new JButton("Subscribe");
        subscribeButton.setBounds(150, 110, 100, 25);
        add(subscribeButton);
        
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

            if (DatabaseHelper.validateUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                new UserCarFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        });

        subscribeButton.addActionListener(e -> {
            new UserSubscribeFrame();
            dispose();
        });
        setLocationRelativeTo(null);

        setVisible(true);
    }
}
