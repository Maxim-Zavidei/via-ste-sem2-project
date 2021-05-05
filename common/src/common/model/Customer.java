package common.model;

public class Customer extends User{

   // private String name;
   // private String surname;
   // private int age;
   // private DateTime birthday;
   // private String gender;

    /**Constructor calling the super constructor*/
    public Customer(String username, String password, String email, String name, String surname, DateTime birthday, char gender) {
        super(username, password,email, name, surname, birthday, gender );
    }
    public Customer(String firstName, String lastName, String email){
        super(firstName, lastName, email);
    }

    /**Getters calling the super getters*/
    public String getUsername(){return super.getUsername();}
    public String getPassword(){return super.getPassword();}
    public String getFirstName() { return super.getFirstName(); }
    public String getLastName() { return super.getLastName();}
    public String getFullName() { return super.getFullName();}
    public int getAge() { return super.getAge(); }
    public DateTime getBirthday() { return super.getBirthday(); }
    public char getGender() {return super.getGender();}

    @Override String getStatus()
    {
        return "Customer";
    }

    /**Setters calling the super setters*/
    public void setUsername(String username) { super.setUsername(username); }
    public void setPassword(String password) { super.setPassword(password); }
    public void setFirstName(String firstName) { super.setFirstName(firstName); }
    public void setLastName(String lastName) { super.setLastName(lastName); }
    public void setGender(char gender) {super.setGender(gender); }

   /* @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Customer)) return false;
        Customer other = (Customer) obj;
        return name.equals(other.name) && surname.equals(other.surname) && age == other.age && birthday.equals(other.birthday) && gender.equals(other.gender);
    }*/

    @Override
    public String toString() {
        return super.toString();
    }

}
