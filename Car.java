public class Car {
    private int id;
    private String make;
    private String model;
    private double price;
    private boolean available;
    public Car(int id, String model, double price, boolean available, String make) {
        this.id = id;
        this.model = model;
        this.make = make;
        this.price = price;
        this.available = available;
    }

    public int getId() {
        return id;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public boolean isAvailable() {
        return available;
    }

    public double getPrice() {
        return price;
    }
}
