package common.model;

public class Employee
{
  /**Instance variables*/
  private String firstName;
  private String lastName;
  private String abbreviation;
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
    this.firstName = "No";
    this.lastName = "Name";
    setAbbreviation();
  }
  public Employee(String firstName, String lastName){
   this();
   setFirstName(firstName);
   setLastName(lastName);
   setAbbreviation();
  }
  public Employee(String firstName, String lastName, String abbreviation){
    this(firstName, lastName);
    this.abbreviation = abbreviation;
  }
  /**************************************************************************************/

  /**Getters*/
  public String getFirstName()
  {
    return firstName;
  }
  public String getLastName()
  {
    return lastName;
  }
  public String getAbbreviation()
  {
    return abbreviation;
  }
  /************************************************/

  /**Setters*/
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  public void setAbbreviation() {
    this.abbreviation = firstName.charAt(0)+lastName.charAt(0)+"";
  }
  /**************************************************************************************/

  /**Methods*/
  public String toString(){
    return "{"+firstName+", " + lastName + "}\n";
  }
  public boolean equals(Object obj){
    if(!(obj instanceof Employee))return false;
    Employee another = (Employee) obj;
    return another.getFirstName().equals(getFirstName()) && another.getLastName().equals(getLastName());
  }
  // * ^^ probably changed with super class methods
}
