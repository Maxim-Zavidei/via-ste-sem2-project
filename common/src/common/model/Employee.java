package common.model;

public class Employee extends User
{
  /**Instance variables*/
 // private String firstName;
  //private String lastName;
 // private String abbreviation;
 // private String email;
  // ? private String username; ? part of super class user //
  // ? private String password; ? part of super class user //
  /***********************/

  /**Constructors*/
  /*
  * public Employee(String username, String password, String firstName, String lastName){
  * super(username,password);
  * this(firstName, lastName);
  * } */
  public Employee(){
    super(createUsername("No", "Name"), "adm", "", "No", "Name", null, 'o');
    //setAbbreviation();
  }
  public Employee(String firstName, String lastName){
    super(createUsername(firstName,lastName), "adm","", firstName, lastName, null, 'o');
  }
  public Employee(String firstName, String lastName, char gender){
    super(createUsername(firstName,lastName), "adm","", firstName, lastName, null, gender);
  }
  public Employee(String firstName, String lastName, String abbreviation, char gender){
    super(abbreviation, "adm","", firstName, lastName, null, gender);
  }
  public Employee(String username, String password,String email)
  {
    super(username, password, email);
  }
  /**************************************************************************************/

  /**Getters*/
  public String getFirstName()
  {
    return super.getFirstName();
  }
  public String getLastName()
  {
    return super.getLastName();
  }
  public String getUsername()
  {
    return super.getUsername();
  }
  public String getFullName() { return super.getFullName();}
  public int getAge() { return super.getAge(); }
  public DateTime getBirthday() { return super.getBirthday(); }
  public char getGender() {return super.getGender();}
  /************************************************/

  /**Setters*/
  public void setPassword(String password) { super.setPassword(password); }
  public void setFirstName(String firstName) { super.setFirstName(firstName); }
  public void setLastName(String lastName) { super.setLastName(lastName); }
  public void setGender(char gender) {super.setGender(gender); }

  @Override String getStatus(){
    return "Employee";
  }
  /**************************************************************************************/

  /**Methods*/
  public String toString(){
    return super.toString();
  }
  public boolean equals(Object obj){
    if(!(obj instanceof Employee))return false;
    Employee another = (Employee) obj;
    return another.getFirstName().equals(getFirstName()) && another.getLastName().equals(getLastName());
  }
  public static String createUsername(String firstName, String lastName){
    return firstName.charAt(0)+lastName.charAt(0)+"";
  }
  // * ^^ probably changed with super class methods
}
