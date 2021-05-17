package common.model;

import java.io.Serializable;

public abstract class User implements Serializable {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private DateTime birthday;
    private char gender;
    private boolean isEmployee;

    // Constructor with extended number of defined values.
    public User(String email, String password, String firstName, String lastName, DateTime birthday, char gender) {
        if (email == null || email.isEmpty()) throw new IllegalArgumentException("Email argument can't be null or empty");
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("Password argument can't be null or empty.");
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        isEmployee();
    }

    // Constructor with minimal required values.
    public User(String email, String password) {
        this(email, password, null, null, null, 'u');
    }

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

    @Override
    public String toString() {
        return String.format("%s %s - %s - %c", firstName, lastName, birthday.toString(), gender);
    }

    public boolean isEmployee() {
        return isEmployee = this instanceof Employee;
    }
}
