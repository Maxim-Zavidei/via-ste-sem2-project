package common.model;

public class Customer extends User {

    // Constructor with extended number of defined values.
    public Customer(String email, String password, String name, String surname, DateTime birthday, char gender) {
        super(email, password, name, surname, birthday, gender);
    }

    // Constructor with minimal required values.
    public Customer(String email, String password) {
        super(email, password);
    }

    /**
     * Getters calling the super getters
     */
    public String getPassword() {
        return super.getPassword();
    }

    public String getFirstName() {
        return super.getFirstName();
    }

    public String getLastName() {
        return super.getLastName();
    }

    public String getFullName() {
        return super.getFullName();
    }

    public int getAge() {
        return super.getAge();
    }

    public DateTime getBirthday() {
        return super.getBirthday();
    }

    public char getGender() {
        return super.getGender();
    }

    /**
     * Setters calling the super setters
     */
    public void setPassword(String password) {
        super.setPassword(password);
    }

    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    public void setGender(char gender) {
        super.setGender(gender);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
