// vhicle.java
class Vehicle {
    public double speed() {
        return 0;
    }
}

// car.java
class Car extends Vehicle {
    double engineSize;
    double fuelCapacity;

    Car() {
    }

    Car(double engineSize, double fuelCapacity) {
        this.engineSize = engineSize;
        this.fuelCapacity = fuelCapacity;
    }

    @Override
    public double speed() {
        return engineSize * fuelCapacity * 0.5;
    }
}

// Bike.java
class Bike extends Vehicle {
    double wheelSize;
    double frameWeight;

    Bike() {
    }

    Bike(double wheelSize, double frameWeight) {
        this.wheelSize = wheelSize;
        this.frameWeight = frameWeight;
    }

    @Override
    public double speed() {
        return (frameWeight / wheelSize) * 10;
    }
}

// Boat.java
class Boat extends Vehicle {
    double hullLength;
    double displacement;

    Boat() {
    }

    Boat(double hullLength, double displacement) {
        this.hullLength = hullLength;
        this.displacement = displacement;
    }

    @Override
    public double speed() {
        return (displacement / hullLength) * 3;
    }
}

public class Polymorphism {
    public static void main(String[] args) {

        // Object array of Vehicle
        Vehicle[] vehicles = new Vehicle[3];

        // Storing child class objects
        vehicles[0] = new Car(2.0, 40.0);
        vehicles[1] = new Bike(0.7, 15.0);
        vehicles[2] = new Boat(10.0, 200.0);

        // Calling speed() using parent reference
        for (Vehicle v : vehicles) {
            System.out.println(v.getClass().getSimpleName()
                    + " Speed = " + v.speed());
        }
    }
}