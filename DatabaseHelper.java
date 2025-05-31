import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/CarManagement";
    private static final String USER = "root"; 
    private static final String PASSWORD = "0000"; 

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean validateAdmin(String username, String password) {
        String query = "SELECT * FROM Admin WHERE username = ? AND password = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean validateUser(String username, String password) {
        String query = "SELECT * FROM User WHERE username = ? AND password = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean addUser(String username, String password) {
        String query = "INSERT INTO User (username, password) VALUES (?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { 
                System.out.println("Username already exists.");
            }
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteUser(int userId) {
        String query = "DELETE FROM User WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static List<User> getUsers() {
        String query = "SELECT * FROM User";
        List<User> users = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("username"),rs.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public static boolean addCar(String model, String make, double price, boolean available) {
        String query = "INSERT INTO Car (model, make, price, availability) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, model);
            stmt.setString(2, make);
            stmt.setDouble(3, price);
            stmt.setBoolean(4, available);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean updateCar(int id, String model, String make, double price, boolean available) {
        String query = "UPDATE Car SET model = ?, make = ?, price = ?, availability = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, model);
            stmt.setString(2, make);
            stmt.setDouble(3, price);
            stmt.setBoolean(4, available);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteCar(int carId) {
        String query = "DELETE FROM Car WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, carId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean buyCar(int carId) {
        String query = "UPDATE Car SET availability = false WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, carId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static List<Car> getCars() {
    	String query = "SELECT * FROM Car";
        List<Car> cars = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                cars.add(new Car(
                    rs.getInt("id"),
                    rs.getString("model"),
                    rs.getDouble("price"),
                    rs.getBoolean("availability"),
                    rs.getString("make")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
}
