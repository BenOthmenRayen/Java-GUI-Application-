import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame {
    public HomeFrame() {
        setTitle("Home");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        JLabel background = new JLabel(new ImageIcon("D:\\CODING\\Java saves\\Car project/car.jpg"));
        background.setLayout(new GridBagLayout());
        add(background);

       
        JButton adminButton = new JButton("Admin");
        JButton userButton = new JButton("User");

     
        JPanel panel = new JPanel();
        panel.setOpaque(false); 
        panel.add(adminButton);
        panel.add(userButton);

        background.add(panel);

        adminButton.addActionListener(e -> {
            new AdminLoginFrame();
            dispose();
        });

        userButton.addActionListener(e -> {
            new UserLoginFrame();
            dispose();
        });
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public static void main(String[] args) {
        new HomeFrame();
    }
}
