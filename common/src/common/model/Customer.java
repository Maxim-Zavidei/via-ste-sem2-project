package common.model;

public class Customer {

    private String name;
    private String surname;
    private int age;
    private DateTime birthday;
    private String gender;

    public Customer(String name, String surname, int age, DateTime birthday, String gender) {
        this.name = name;
        this.surname = surname;
        this.age =age;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public int getAge() {
        return age;
    }

    public DateTime getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Customer)) return false;
        Customer other = (Customer) obj;
        return name.equals(other.name) && surname.equals(other.surname) && age == other.age && birthday.equals(other.birthday) && gender.equals(other.gender);
    }

    @Override
    public String toString() {
        return getFullName() + " " + age + " " + birthday + " " + gender;
    }
}
