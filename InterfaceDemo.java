interface Drivable {
    void start();

    void stop();

    default void describe() {
        System.out.println("This is a drivable vehicle.");
    }
}

abstract class Vehicle implements Drivable {

    private String brand;

    public Vehicle(String brand) {
        System.out.println("Vehicle constructor called");
        this.brand = brand;
    }

    abstract double calculateFuelEfficiency();

    public abstract String toString();

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

class Car extends Vehicle {
    private double distanceTravelled;
    private double fuelConsumed;

    public Car(String brand, double distanceTravelled, double fuelConsumed) {
        super(brand);
        this.distanceTravelled = distanceTravelled;
        this.fuelConsumed = fuelConsumed;
        System.out.println("Car constructor called");
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(double dist) {
        this.distanceTravelled = dist;
    }

    public double getFuelConsumed() {
        return fuelConsumed;
    }

    public void setFuelConsumed(double fuel) {
        this.fuelConsumed = fuel;
    }

    @Override
    public double calculateFuelEfficiency() {
        return distanceTravelled / fuelConsumed;
    }

    @Override
    public String toString() {
        return "Car brand is " + getBrand() + " and fuel efficiency is: " + calculateFuelEfficiency() + " km/l";
    }

    @Override
    public void start() {
        System.out.println("Car " + getBrand() + " engine started.");
    }

    @Override
    public void stop() {
        System.out.println("Car " + getBrand() + " engine stopped.");
    }
}

class Motorcycle extends Vehicle {
    private int engineCapacity;
    private double mileage;

    public Motorcycle(String brand, int engineCapacity, double mileage) {
        super(brand);
        this.engineCapacity = engineCapacity;
        this.mileage = mileage;
        System.out.println("Motorcycle constructor called");
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int cap) {
        this.engineCapacity = cap;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double m) {
        this.mileage = m;
    }

    @Override
    public double calculateFuelEfficiency() {
        return mileage;
    }

    @Override
    public String toString() {
        return "Motorcycle brand is " + getBrand() + " and fuel efficiency is: " + calculateFuelEfficiency() + " km/l";
    }

    @Override
    public void start() {
        System.out.println("Motorcycle " + getBrand() + " engine started.");
    }

    @Override
    public void stop() {
        System.out.println("Motorcycle " + getBrand() + " engine stopped.");
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {

        Vehicle car = new Car("Toyota", 500, 40);
        Vehicle bike = new Motorcycle("Yamaha", 150, 45.5);

        System.out.println(car.toString());
        System.out.println(bike.toString());

        car.start();
        car.stop();
        bike.start();
        bike.stop();

        car.describe();

        car.setBrand("Honda");
        System.out.println("Updated " + car.toString());
    }
}