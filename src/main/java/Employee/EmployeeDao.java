package Employee;

import java.util.List;

public interface EmployeeDao {
    boolean addEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    boolean isEmailUnique(String email);
}
