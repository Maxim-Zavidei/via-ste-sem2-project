package common.model;

public abstract class User {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private DateTime birthday;
    private char gender;

    // Constructor with extended number of defined values.
    public User(String email, String password, String firstName, String lastName, DateTime birthday, char gender) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
    }

    public User(String email, String password, String firstName, String lastName) {
        this(email, password, firstName, lastName, null, 'u');
    }

    // Constructor with minimal required values.
    public User(String email, String password) {
        this(email, password, null, null, null, 'u');
    }

    /**
     * Getters
     */
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public DateTime getBirthday() {
        return birthday;
    }

    public int getAge() {
        return DateTime.yearsBetween(this.birthday);
    }

    public char getGender() {
        return gender;
    }

    /**
     * Setters
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setBirthday(DateTime birthday) {
        this.birthday = birthday;
    }

    /**
     * Methods
     */
    @Override
    public String toString() {
        return String.format("%s - %s %s - %c", firstName, lastName, birthday.toString(), gender);
    }

    public String userType() {
        return this instanceof Employee ? "Employee" : "Customer";
    }
}
