package viewmodel;

import common.model.DateTime;
import common.model.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserView {

    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty email;
    private ObjectProperty<DateTime> birthDate;
    private StringProperty gender;
    private StringProperty status;

    public UserView(User user) {
        this.firstName = new SimpleStringProperty(user.getFirstName());
        this.lastName = new SimpleStringProperty(user.getLastName());
        this.email = new SimpleStringProperty(user.getEmail());
        this.birthDate = new SimpleObjectProperty<>(user.getBirthday());
        this.gender = new SimpleStringProperty(user.getGender() + "");
        this.status = new SimpleStringProperty(user.isEmployee() ? "Employee" : "Customer");
    }

    public StringProperty getFirstName() {
        return firstName;
    }

    public StringProperty getLastName() {
        return lastName;
    }

    public StringProperty getEmail() {
        return email;
    }

    public StringProperty getStatus() {
        return status;
    }

    public StringProperty getGender() {
        return gender;
    }

    public ObjectProperty<DateTime> getBirthDate() {
        return birthDate;
    }
}
