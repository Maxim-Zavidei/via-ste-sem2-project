package common.model;

public abstract class User {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private DateTime birthday;
    private char gender;
    private String status;

    public User(String username, String password){
    this.username = username;
    this.password = password;
    setEmail(null);
    setFirstName(null);
    setLastName(null);
    setGender('u');
    setBirthday(null);
    this.status=null;
    }

    public User( String username, String password, String email, String firstName, String lastName, DateTime birthday, char gender){
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
    }
    public User(String username, String password, String email){
      this(username, password);
      this.email = email;
      setFirstName(null);
      setLastName(null);
      setGender('u');
      setBirthday(null);
    }
    public User(String username, String password, String email, String firstName, String lastName){
      this(username, password, email);
      setFirstName(firstName);
      setLastName(lastName);
    }


  /**Getters*/
    public String getEmail()
  {
    return email;
  }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName(){return firstName + " " + lastName; }
    public DateTime getBirthday() {  return birthday; }
    public int getAge(){ return DateTime.yearsBetween(this.birthday); }
    public char getGender() {return gender; }
    abstract String getStatus();

  /**Setters*/
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email)
  {
    this.email = email;
  }
    public void setPassword(String password) { this.password = password; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setGender(char gender) {this.gender = gender; }
    public void setBirthday(DateTime birthday){
    this.birthday = birthday;
    }
    public void setStatus(String status){
      this.status = status;
    }

  /**Methods*/
    @Override
    public String toString() {
        return String.format("%s - %s %s - %s - %c", username,firstName, lastName, birthday.toString(), gender);
    }
    public String userType(User user){
      if(user instanceof Employee)return "Employee";
      else if(user instanceof Customer) return "Customer";
      return "No type?";
    }
  public boolean equals(Object obj){
    if(!(obj instanceof User))return false;
    User another = (User)obj;
    return this.getUsername().equals(another.getUsername()) && this.getEmail().equals(another.getEmail()) &&
        this.getBirthday().toString().equals(another.getBirthday().toString()) && this.getFullName().equals(another.getFullName())
        && this.getPassword().equals(another.getPassword()) && this.getStatus().equals(another.getStatus());
  }

}
