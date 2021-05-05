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
    dummyData();
  }

  private void dummyData()
  {
    Employee employee1 = new Employee("Stefan", "Georgiev");
    employees.put(employee1.getUsername(), employee1);
    Employee employee2 = new Employee("Maxim", "Zavidei", 'M');
    employees.put(employee2.getUsername(), employee2);
    Employee employee3 = new Employee("Yoana", "Miteva");
    employees.put(employee3.getUsername(), employee3);
    Employee employee4 = new Employee("Valeriu", "Rosca");
    employees.put(employee4.getUsername(), employee4);
  }
  /****************************/

  /**Methods*/
  public void addEmployee(Employee employee){
    employees.put(employee.getUsername(), employee);
  }
  public void removeEmployee(Employee employee){
    employees.remove(employee.getUsername(), employee);
  }
  public void removeEmployee(String name){
    employees.remove(name);
  }
  public Employee getEmployee(String name){
    return employees.get(name);
  }
  public Employee getEmployee(int index){
    return employees.get(index);
  }
  public int getSize(){return employees.size();}
  public String getEmployees(){
    StringBuilder returnEmployees= new StringBuilder();
    for (Map.Entry<String, Employee> employee : employees.entrySet()){
      returnEmployees.append(employee.getValue().toString());
    }
    return returnEmployees.toString();
  }
  /**************************************/
}
