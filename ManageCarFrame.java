import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ManageCarFrame extends JFrame {
    private DefaultTableModel model;

    public ManageCarFrame() {
        setTitle("Manage Cars");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        

        
        String[] columnNames = {"ID", "Model", "Make", "Price", "Availability"};
        model = new DefaultTableModel(columnNames, 0);
        JTable carTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(carTable);
        scrollPane.setBounds(20, 20, 550, 200);
        add(scrollPane);


        JButton addCarButton = new JButton("Add Car");
        addCarButton.setBounds(25, 250, 100, 30);
        add(addCarButton);

        JButton deleteCarButton = new JButton("Delete Car");
        deleteCarButton.setBounds(150, 250, 100, 30);
        add(deleteCarButton);

        JButton updateCarButton = new JButton("Update Car");
        updateCarButton.setBounds(275, 250, 100, 30);
        add(updateCarButton);

       
        loadCars();

   
        addCarButton.addActionListener(e -> {
            String model = JOptionPane.showInputDialog("Enter Car Model:");
            String make = JOptionPane.showInputDialog("Enter Car Make:");
            String priceStr = JOptionPane.showInputDialog("Enter Car Price:");
            String availabilityStr = JOptionPane.showInputDialog("Is the car available? (Yes/No)");

            try {
                double price = Double.parseDouble(priceStr);
                boolean availability = availabilityStr.equalsIgnoreCase("Yes");

                if (DatabaseHelper.addCar(model, make, price, availability)) {
                    JOptionPane.showMessageDialog(this, "Car added successfully");
                    loadCars(); 
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add car");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid price. Please try again.");
            }
        });

       
        deleteCarButton.addActionListener(e -> {
            int selectedRow = carTable.getSelectedRow();
            if (selectedRow != -1) {
                int carId = (int) model.getValueAt(selectedRow, 0);
                if (DatabaseHelper.deleteCar(carId)) {
                    JOptionPane.showMessageDialog(this, "Car deleted successfully");
                    loadCars(); 
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete car");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a car to delete");
            }
        });

        
        updateCarButton.addActionListener(e -> {
            int selectedRow = carTable.getSelectedRow();
            if (selectedRow != -1) {
                int carId = (int) model.getValueAt(selectedRow, 0);
                String newModel = JOptionPane.showInputDialog("Enter New Model:", model.getValueAt(selectedRow, 1));
                String newMake = JOptionPane.showInputDialog("Enter New Make:", model.getValueAt(selectedRow, 2));
                String newPriceStr = JOptionPane.showInputDialog("Enter New Price:", model.getValueAt(selectedRow, 3));
                String newAvailabilityStr = JOptionPane.showInputDialog("Is the car available? (Yes/No):", model.getValueAt(selectedRow, 4));

                try {
                    double newPrice = Double.parseDouble(newPriceStr);
                    boolean newAvailability = newAvailabilityStr.equalsIgnoreCase("Yes");

                    if (DatabaseHelper.updateCar(carId, newModel, newMake, newPrice, newAvailability)) {
                        JOptionPane.showMessageDialog(this, "Car updated successfully");
                        loadCars(); 
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update car");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price. Please try again.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a car to update");
            }
        });

        
        setLocationRelativeTo(null);

        setVisible(true);
        
        JButton Return = new JButton("Return");
        Return.setBounds(400,250,100,30);
        add(Return);
        
        Return.addActionListener(e -> {
        	new AdminDashboardFrame();
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

