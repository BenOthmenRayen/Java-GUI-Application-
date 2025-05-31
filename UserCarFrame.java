import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UserCarFrame extends JFrame {
    private DefaultTableModel model;

    public UserCarFrame() {
        setTitle("Available Cars");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        

       
        String[] columnNames = {"ID", "Model", "Make", "Price", "Availability"};
        model = new DefaultTableModel(columnNames, 0);
        JTable carTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(carTable);
        scrollPane.setBounds(20, 20, 550, 250);
        add(scrollPane);

        
        JButton buyButton = new JButton("Buy");
        buyButton.setBounds(150, 300, 100, 30);
        add(buyButton);

       
        loadCars();

        
        buyButton.addActionListener(e -> {
            int selectedRow = carTable.getSelectedRow();
            if (selectedRow != -1) {
                int carId = (int) model.getValueAt(selectedRow, 0);
                String availability = (String) model.getValueAt(selectedRow, 4);

                if ("No".equalsIgnoreCase(availability)) {
                    JOptionPane.showMessageDialog(this, "This car is already unavailable.");
                } else {
                    
                    if (DatabaseHelper.buyCar(carId)) {
                        JOptionPane.showMessageDialog(this, "Car purchased successfully!");
                        loadCars(); 
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to buy car. Please try again.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a car to buy.");
            }
        });

        
        setLocationRelativeTo(null);

        setVisible(true);
        
        JButton Return = new JButton("Return");
        Return.setBounds(300,300,100,30);
        add(Return);
        
        Return.addActionListener(e -> {
        	new UserLoginFrame();
            dispose();
        });
    }

    private void loadCars() {
        model.setRowCount(0); 
        List<Car> cars = DatabaseHelper.getCars();
        for (Car car : cars) {
            model.addRow(new Object[]{
                car.getId(), car.getModel(), car.getMake(), car.getPrice(), car.isAvailable() ? "Yes" : "No"
            });
        }
    }
}

