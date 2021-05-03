package common.model;

public class User {
    private String username;
    private String password;
   // private String email;
    private String firstName;
    private String lastName;
    private DateTime birthday;
    private char gender;

    public User( String username, String password, String firstName, String lastName, DateTime birthday, char gender){
        //this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
    }

    /**Getters*/
    public String getUsername() { return username; }
    //public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName(){return firstName + " " + lastName; }
    public DateTime getBirthday() {  return birthday; }
    public int getAge(){ return DateTime.yearsBetween(this.birthday); }
    public char getGender() {return gender; }

    /**Setters*/
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setGender(char gender) {this.gender = gender; }

    @Override
    public String toString() {
        return String.format("%s - %s %s - %s - %c", username,firstName, lastName, birthday.toString(), gender);
    }
}
