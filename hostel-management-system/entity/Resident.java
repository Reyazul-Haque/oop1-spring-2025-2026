package entity;

public class Resident {

    private String id;
    private String name;
    private String age;
    private String roomNumber;

    public Resident(String id, String name, String age, String roomNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.roomNumber = roomNumber;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String toLine() {

        return id + "," + name + "," + age + "," + roomNumber;
    }

    public static Resident fromLine(String line) {

        if (line == null)
            return null;

        String[] data = line.split(",", -1);

        if (data.length != 4)
            return null;

        return new Resident(data[0], data[1], data[2], data[3]);
    }

    public Object[] toRow() {

        return new Object[] { id, name, age, roomNumber };
    }
}