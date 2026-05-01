abstract class Vehicle {
    String brand;

    abstract double calculateFuelEfficiency();

    public Vehicle(String brand) {
        System.out.println("Vehicle constructor called");
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}

class Car extends Vehicle {
    double distanceTravelled;
    double fuelConsumed;

    public Car(String brand, double distanceTravelled, double fuelConsumed) {
        super(brand);
        System.out.println("Car constructor called");
        this.distanceTravelled = distanceTravelled;
        this.fuelConsumed = fuelConsumed;
    }

    @Override
    double calculateFuelEfficiency() {
        return distanceTravelled / fuelConsumed;
    }

    @Override
    public String toString() {
        return "Car brand is " + getBrand() +
                " and fuel efficiency is: " + calculateFuelEfficiency() + " km/l";
    }
}

class Motorcycle extends Vehicle {
    int engineCapacity;
    double mileage;

    public Motorcycle(String brand, int engineCapacity, double mileage) {
        super(brand);
        System.out.println("Motorcycle constructor called");
        this.engineCapacity = engineCapacity;
        this.mileage = mileage;
    }

    @Override
    double calculateFuelEfficiency() {
        return mileage;
    }

    @Override
    public String toString() {
        return "Motorcycle brand is " + getBrand() +
                " and fuel efficiency is: " + calculateFuelEfficiency() + " km/l";
    }
}

public class Test {
    public static void main(String[] args) {
        Vehicle v1 = new Car("Toyota", 500, 40);
        Vehicle v2 = new Motorcycle("Yamaha", 150, 45);

        System.out.println(v1.toString());
        System.out.println(v2.toString());
    }
}