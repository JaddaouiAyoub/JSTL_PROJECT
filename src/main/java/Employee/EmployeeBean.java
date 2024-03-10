package Employee;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@ManagedBean
@ViewScoped
public class EmployeeBean {
    private final EmployeeService employeeService;
    private int editEmployeeId;
    private Employee newEmployee;
    private boolean formVisible;
    private boolean editMode = false;
    //private Employee editEmployee;
    private int currentPage = 0; // Numéro de la page actuelle
    private int nbrPage;
    private Employee editedEmployee;
    private List<Employee> employees;
    private String msgNotification;
    public EmployeeBean() {
        employeeService = new EmployeeService();
        employees = employeeService.getAllEmployees();
        int size = employees.size();
        nbrPage = size / 7; // Calculer le nombre de pages

        if (size % 7 != 0) { // Vérifier si le résultat n'est pas un nombre entier
            nbrPage++; // Ajouter 1 si le résultat n'est pas un nombre entier
        }
        newEmployee = new Employee();
    }

    // Méthode pour obtenir les employés de la page actuelle
    public List<Employee> getDisplayedEmployees() {
        int fromIndex = currentPage * 7;
        int toIndex = Math.min(fromIndex + 7, employees.size());
        return employees.subList(fromIndex, toIndex);
    }

    // Méthode pour passer à la page précédente
    public void previousPage() {
        if (currentPage > 0) {
            currentPage--;
        }
    }

    // Méthode pour passer à la page suivante
    public void nextPage() {
        int maxPage = (int) Math.ceil((double) employees.size() / 7);
        if (currentPage < maxPage - 1) {
            currentPage++;
        }
    }
//Methode pour effacer le message de notification
    public String clearDeletedSuccessfully() {
        msgNotification = null;
        return "";
    }

    public void deleteEmployee(Employee employee) {
        if (employeeService.deleteEmployee(employee)) {
            setMsgNotification("employee.delete.success");
            employees=employeeService.getAllEmployees();
        } else {
            setMsgNotification("employee.delete.failure");
        }
    }

    public static String formatDateString(String dateString, Locale locale) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(dateString);
            Locale browserLocale = locale != null ? locale : Locale.getDefault(); // Utiliser la langue du navigateur ou la langue par défaut
            DateFormat outputFormat = DateFormat.getDateInstance(DateFormat.SHORT, browserLocale);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace(); // Gérer l'erreur de parsing de la date
            return dateString; // Retourner la date non formatée en cas d'erreur
        }
    }


    public void addEmployee() {
        if (employeeService.addEmployee(newEmployee)) {
            setMsgNotification("employee.add.success");
            formVisible = !formVisible;

            // Ajouter le nouvel employé à la liste des employés affichés
            employees = employeeService.getAllEmployees();
        } else {
            setMsgNotification("employee.add.failure");
        }
        newEmployee = new Employee();
    }

    public void enableEdit(Employee e) {
//        bindingEnabled = true;
        editMode = true;
        editedEmployee = e;
        editEmployeeId = e.getId();
    }

    public void saveChanges() {

        if (employeeService.updateEmployee(editedEmployee)) {
            setMsgNotification("employee.edit.success");
        } else {
            setMsgNotification("employee.edit.failure");
        }
        editEmployeeId = 0;
        editedEmployee = null;
    }

    public boolean isFormVisible() {
        return formVisible;
    }

    public void setFormVisible(boolean formVisible) {
        this.formVisible = formVisible;
    }

    public int getEditEmployeeId() {
        return editEmployeeId;
    }

    public void setEditEmployeeId(int editEmployeeId) {
        this.editEmployeeId = editEmployeeId;
    }

    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isRenderNextButton() {
        int maxPage = (int) Math.ceil((double) employees.size() / 7);
        return currentPage < maxPage - 1;
    }

    public boolean isRenderPreviousButton() {
        return currentPage != 0;
    }

    public void toggleFormVisibility() {
        formVisible = !formVisible;
    }

    public boolean isEmployeeInEditMode(Employee employee) {
        return editedEmployee != null && editedEmployee.equals(employee);
    }


    public Employee getEditedEmployee() {
        return editedEmployee;
    }

    public void setEditedEmployee(Employee editedEmployee) {
        this.editedEmployee = editedEmployee;
    }



    public String getMsgNotification() {
        return msgNotification;
    }

    public void setMsgNotification(String msgNotification) {
        this.msgNotification = msgNotification;
    }

    public int getNbrPage() {
        return nbrPage;
    }

    public void setNbrPage(int nbrPage) {
        this.nbrPage = nbrPage;
    }
}
