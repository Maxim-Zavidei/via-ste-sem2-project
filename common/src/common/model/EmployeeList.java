package common.model;

import java.util.HashMap;
import java.util.Map;

public class EmployeeList
{
  /**Instance variables*/
  private Map<String, Employee> employees;
  /************************************/

  /**Constructor*/
  public EmployeeList(){
    employees = new HashMap<>();
  }
  /****************************/

  /**Methods*/
  public void addEmployee(Employee employee){
    employees.put(employee.getAbbreviation(), employee);
  }
  public void removeEmployee(Employee employee){
    employees.remove(employee.getAbbreviation(), employee);
  }
  public void removeEmployee(String name){
    employees.remove(name);
  }
  public Employee getEmployee(String name){
    return employees.get(name);
  }
  public String getEmployees(){
    StringBuilder returnEmployees= new StringBuilder();
    for (Map.Entry<String, Employee> employee : employees.entrySet()){
      returnEmployees.append(employee.getValue().toString());
    }
    return returnEmployees.toString();
  }
  /**************************************/
}
