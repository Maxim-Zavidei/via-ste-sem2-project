package model;

public class Customer {
    private String name;
    private String surname;
    private int age;
    private MyDate birthday;
    private String gender;

    public Customer(String name, String surname, int age, MyDate birthday, String gender)
    {
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
    public String getFullName()
    {
        return name+" "+surname;
    }

    public int getAge() {
        return age;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof Customer))
        {
            return false;
        }
        Customer other = (Customer) obj;
        return name.equals(other.name) && surname.equals(other.surname) && age == other.age && birthday.equals(other.birthday) && gender.equals(other.gender);
    }

    public String toString()
    {
        String s ="";
        s = getFullName()+" "+age+" "+birthday+" "+gender;
        return s;
    }
}
