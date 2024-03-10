package Employee;

import DataBase.DataBaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private final DataBaseAccess dataBaseAccess;

    public EmployeeDaoImpl() {
        this.dataBaseAccess = new DataBaseAccess();
    }

    // Méthode pour ajouter un nouvel employé
    public boolean addEmployee(Employee employee) {
        String query = "INSERT INTO employeed (id, fullName, email, department, hireDate, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataBaseAccess.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getFullName());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getDepartment());
            statement.setString(5, employee.getHireDate()); // Utilisation de la chaîne de caractères pour la date de recrutement
            statement.setString(6, employee.getPhoneNumber());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer la PreparedStatement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Ne pas fermer la connexion ici
        }
        return false;
    }

    // Méthode pour mettre à jour un employé existant
    public boolean updateEmployee(Employee employee) {
        String query = "UPDATE employeed SET fullName = ?, email = ?, department = ?, hireDate = ?, phoneNumber = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataBaseAccess.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, employee.getFullName());
            statement.setString(2, employee.getEmail());
            statement.setString(3, employee.getDepartment());
            statement.setString(4, employee.getHireDate()); // Utilisation de la chaîne de caractères pour la date de recrutement
            statement.setString(5, employee.getPhoneNumber());
            statement.setInt(6, employee.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer la PreparedStatement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Ne pas fermer la connexion ici
        }
        return false;
    }

    // Méthode pour récupérer tous les employés
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employeed";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataBaseAccess.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fullName = resultSet.getString("fullName");
                String email = resultSet.getString("email");
                String department = resultSet.getString("department");
                String hireDate = resultSet.getString("hireDate"); // Utilisation de la chaîne de caractères pour la date de recrutement
                String phoneNumber = resultSet.getString("phoneNumber");
                employees.add(new Employee(id, fullName, email, department, hireDate, phoneNumber));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer le ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Fermer la PreparedStatement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Ne pas fermer la connexion ici
        }
        return employees;
    }

    // Méthode pour récupérer un employé par son ID
    public Employee getEmployeeById(int id) {
        String query = "SELECT * FROM employeed WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataBaseAccess.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String fullName = resultSet.getString("fullName");
                String email = resultSet.getString("email");
                String department = resultSet.getString("department");
                String hireDate = resultSet.getString("hireDate"); // Utilisation de la chaîne de caractères pour la date de recrutement
                String phoneNumber = resultSet.getString("phoneNumber");
                return new Employee(id, fullName, email, department, hireDate, phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer le ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Fermer la PreparedStatement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Ne pas fermer la connexion ici
        }
        return null;
    }

    // Méthode pour vérifier si un email est unique
    public boolean isEmailUnique(String email) {
        String query = "SELECT COUNT(*) FROM employeed WHERE email = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataBaseAccess.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer le ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Fermer la PreparedStatement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Ne pas fermer la connexion ici
        }
        return false;
    }

    // Méthode pour supprimer un employé
    public boolean deleteEmployee(Employee employee) {
        String query = "DELETE FROM employeed WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataBaseAccess.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, employee.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer la PreparedStatement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Ne pas fermer la connexion ici
        }
        return false;
    }

    // Méthode pour fermer la connexion à la base de données
    public void closeConnection() {
        dataBaseAccess.closeConnection();
    }
}
