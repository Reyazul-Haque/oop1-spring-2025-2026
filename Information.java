class Position {
    private String name;

    public Position(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Employee {
    private String name;
    private Position position;

    public Employee(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public void showEmployeeInfo() {
        System.out.println("Employee Name: " + name);
        System.out.println("Position: " + position.getName());
    }

    public String getName() {
        return name;
    }
}

class Company {
    private String companyName;
    private Employee[] employees;
    private int count = 0;

    public Company(String companyName, int numberOfemployees) {
        this.companyName = companyName;
        this.employees = new Employee[numberOfemployees];
    }

    public void addEmployee(Employee employee) {
        if (count < employees.length) {
            employees[count] = employee;
            count++;
        } else {
            System.out.println("Cannot add more Employees to " + companyName);
        }
    }

    public void showEmployees() {
        System.out.println("Company: " + companyName);
        System.out.println("Employees:");
        for (int i = 0; i < count; i++) {
            System.out.println(" - " + employees[i].getName());
        }
    }
}

public class Information {
    public static void main(String[] args) {

        Company c = new Company("Amazon", 3);
        c.addEmployee(new Employee("Rony", new Position("Manager")));
        c.addEmployee(new Employee("Jonny", new Position("Developer")));
        c.addEmployee(new Employee("Sonny", new Position("Designer")));

        c.showEmployees();
        System.out.println();

        Employee e = new Employee("John Doe", new Position("Engineer"));

        e.showEmployeeInfo();
    }
}