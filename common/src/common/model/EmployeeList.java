package common.model;

import java.util.HashMap;
import java.util.Map;

public class EmployeeList {
    /**
     * Instance variables
     */
    private Map<String, Employee> employees;

    /**
     * Constructor
     */
    public EmployeeList() {
        employees = new HashMap<>();
    }


    /**
     * Methods
     */
    public void addEmployee(Employee employee) {
        employees.put(employee.getEmail(), employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee.getEmail());
    }

    public void removeEmployee(String email) {
        employees.remove(email);
    }

    public Employee getEmployee(String email) {
        return employees.get(email);
    }

    public int getSize() {
        return employees.size();
    }

    public String getEmployees() {
        StringBuilder returnEmployees = new StringBuilder();
        for (Map.Entry<String, Employee> employee : employees.entrySet()) {
            returnEmployees.append(employee.getValue().toString());
        }
        return returnEmployees.toString();
    }
}
