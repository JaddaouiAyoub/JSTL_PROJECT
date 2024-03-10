package Employee;

import java.util.List;

public class EmployeeService {
    private final EmployeeDao employeeDao;

    public EmployeeService() {
        this.employeeDao = new EmployeeDaoImpl();
    }

    // Méthode pour ajouter un employé en vérifiant l'unicité de l'e-mail
    public boolean addEmployee(Employee employee) {
        if (employeeDao.isEmailUnique(employee.getEmail())) {
           return employeeDao.addEmployee(employee);
        } else {
            System.out.println("L'email fourni existe déjà dans la base de données. Veuillez fournir un autre email.");
            return false;// Vous pouvez lancer une exception ou effectuer toute autre action en cas d'email déjà existant
        }
    }

    // Méthode pour mettre à jour un employé
    public boolean updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    // Méthode pour supprimer un employé
    public boolean deleteEmployee(Employee employee) {
        return employeeDao.deleteEmployee(employee);
    }

    // Méthode pour récupérer tous les employés
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    // Méthode pour récupérer un employé par son ID
    public Employee getEmployeeById(int id) {
        return employeeDao.getEmployeeById(id);
    }

    // Méthode pour fermer la connexion à la base de données
//    public void closeConnection() {
//        employeeDao.closeConnection();
//    }
}
