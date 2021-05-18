package mediator;

import common.model.Product;
import common.model.User;
import common.model.UserList;
import model.Model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class EmployeeAuthenticated extends GenericAccessType {
    public EmployeeAuthenticated(Model model, String email, String password) throws Exception {
        super(model, email, password);
    }


    @Override
    public void addProduct(Product product) throws RemoteException {
        getModel().addProduct(product);
    }

    @Override public UserList getUsers() throws RemoteException
    {
        return getModel().getAllRegisteredUsers();
    }
}
