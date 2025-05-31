import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class ManageUserFrame extends JFrame {
    private DefaultTableModel model;

    public ManageUserFrame() {
        setTitle("Manage Users");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        

        
        String[] columnNames = {"ID", "Username","Password"};
        model = new DefaultTableModel(columnNames, 0);
        JTable userTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(20, 20, 450, 200);
        add(scrollPane);

        
        loadUsers();

       
        JButton addUserButton = new JButton("Add User");
        addUserButton.setBounds(25, 250, 100, 30);
        add(addUserButton);

       
        JButton deleteUserButton = new JButton("Delete User");
        deleteUserButton.setBounds(200, 250, 100, 30);
        add(deleteUserButton);

        
        addUserButton.addActionListener(e -> {
            String username = JOptionPane.showInputDialog("Enter Username:");
            String password = JOptionPane.showInputDialog("Enter Password:");

            if (username != null && password != null) {
                if (DatabaseHelper.addUser(username, password)) {
                    JOptionPane.showMessageDialog(this, "User added successfully");
                    loadUsers(); 
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add user. Username may already exist.");
                }
            }
        });

        
        deleteUserButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                int userId = (int) model.getValueAt(selectedRow, 0);
                if (DatabaseHelper.deleteUser(userId)) {
                    JOptionPane.showMessageDialog(this, "User deleted successfully");
                    loadUsers(); 
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete user");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a user to delete");
            }
        });
        setLocationRelativeTo(null);

        setVisible(true);
        
        JButton Return = new JButton("Return");
        Return.setBounds(350,250,100,30);
        add(Return);
        
        Return.addActionListener(e -> {
        	new AdminDashboardFrame();
            dispose();
        });
        
    }

    private void loadUsers() {
        model.setRowCount(0); 
        DatabaseHelper.getUsers().forEach(user -> {
            model.addRow(new Object[]{user.getId(), user.getUsername(), user.getPassword()});
        });
    }
}
