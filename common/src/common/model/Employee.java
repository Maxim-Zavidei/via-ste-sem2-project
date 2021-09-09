package common.model;

import java.time.LocalDate;

public class Employee extends User {

    public Employee(String email, String password, String name, String surname, LocalDate birthday, char gender) throws IllegalArgumentException {
        super(email, password, name, surname, birthday, gender);
    }

    public Employee(String email, String password, String name, String surname, DateTime birthday, char gender) throws IllegalArgumentException {
        super(email, password, name, surname, LocalDate.of(birthday.getYear(), birthday.getMonth(), birthday.getDay()), gender);
    }
}
