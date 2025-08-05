package restAssured.enitites;

public class EmployeeResponse {
    private String city;
    private String name;
    private String position;
    private String surname;

    public EmployeeResponse(String city, String name, String position, String surname) {
        this.city = city;
        this.name = name;
        this.position = position;
        this.surname = surname;
    }

    public EmployeeResponse(String name, String position, String surname) {
        this.name = name;
        this.position = position;
        this.surname = surname;
    }


    public EmployeeResponse(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
