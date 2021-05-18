package mediator;

import common.model.Product;
import common.model.UserList;
import model.Model;
import java.rmi.RemoteException;
import java.time.LocalDate;

public class CustomerAuthenticated extends GenericAccessType {

    public CustomerAuthenticated(Model model, String email, String password) throws Exception {
        super(model, email, password);
    }

    @Override
    public UserList getAllRegisteredUsers() throws RemoteException {
        throw new IllegalStateException("Only an employee is allowed to perform this request.");
    }

    @Override
    public void updateUser(String oldEmail, String newEmail, String password, String firstName, String lastName, LocalDate birthday, char gender, boolean isEmployee) throws RemoteException {
        if (oldEmail != null && oldEmail.equals(getEmail())) {
            getModel().updateUser(oldEmail, newEmail, password, firstName, lastName, birthday, gender, isEmployee);
        }
        throw new IllegalArgumentException("A customer can only change his account settings.");
    }

    @Override
    public void removeUser(String email) throws RemoteException {
        throw new IllegalStateException("Only an employee is allowed to perform this request.");
    }

    @Override
    public void addProduct(int quantity, String name, String description, double price) throws RemoteException {
        throw new IllegalStateException("Only an employee is allowed to perform this request.");
    }

    @Override
    public void updateProduct(Product product) throws RemoteException {
        throw new IllegalStateException("Only an employee is allowed to perform this request.");
    }

    @Override
    public void removeProduct(Product product) throws RemoteException {
        throw new IllegalStateException("Only an employee is allowed to perform this request.");
    }
}
