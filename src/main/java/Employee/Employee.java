package Employee;

public class Employee {
    private int id;
    private String fullName;
    private String email;
    private String department;
    private String hireDate; // Changer le type de hireDate en String
    private String phoneNumber; // Champ pour le numéro de téléphone

    public Employee(int id, String fullName, String email, String department, String hireDate, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.hireDate = hireDate;
        this.phoneNumber = phoneNumber;
    }

    // Ajouter un constructeur par défaut
    public Employee(){
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
