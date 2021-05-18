package mediator;

import common.model.Product;
import common.model.UserList;
import model.Model;
import java.rmi.RemoteException;
import java.time.LocalDate;

public class EmployeeAuthenticated extends GenericAccessType {

    public EmployeeAuthenticated(Model model, String email, String password) throws Exception {
        super(model, email, password);
    }

    @Override
    public UserList getAllRegisteredUsers() throws RemoteException {
        return getModel().getAllRegisteredUsers();
    }

    @Override
    public void updateUser(String oldEmail, String newEmail, String password, String firstName, String lastName, LocalDate birthday, char gender, boolean isEmployee) throws RemoteException {
        getModel().updateUser(oldEmail, newEmail, password, firstName, lastName, birthday, gender, isEmployee);
    }

    @Override
    public void removeUser(String email) throws RemoteException {
        getModel().removeUser(email);
    }

    @Override
    public void addProduct(int quantity, String name, String description, double price) throws RemoteException {
        getModel().addProduct(quantity, name, description, price);
    }

    @Override
    public void updateProduct(Product product) throws RemoteException {
        getModel().updateProduct(product);
    }

    @Override
    public void removeProduct(Product product) throws RemoteException {
        getModel().removeProduct(product);
    }
}
