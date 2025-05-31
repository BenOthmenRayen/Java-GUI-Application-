import javax.swing.*;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboardFrame extends JFrame {
    public AdminDashboardFrame() {
        setTitle("Admin Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JButton manageCarButton = new JButton("Manage Cars");
        manageCarButton.setBounds(50, 50, 150, 30);
        add(manageCarButton);

        JButton manageUserButton = new JButton("Manage Users");
        manageUserButton.setBounds(50, 100, 150, 30);
        add(manageUserButton);
        
        
        
        JButton Return = new JButton("Return");
        Return.setBounds(150,200,80,25);
        add(Return);
        
        Return.addActionListener(e -> {
        	new HomeFrame();
            dispose();
        });

        
        manageCarButton.addActionListener(e -> {
            new ManageCarFrame();
            dispose();
        });

        manageUserButton.addActionListener(e -> {
            new ManageUserFrame();
            dispose();
        });
        setLocationRelativeTo(null);

        setVisible(true);
    }
}
