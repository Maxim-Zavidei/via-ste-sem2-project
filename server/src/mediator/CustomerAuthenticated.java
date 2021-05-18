package mediator;

import common.model.Product;
import common.model.User;
import common.model.UserList;
import model.Model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CustomerAuthenticated extends GenericAccessType {
    public CustomerAuthenticated(Model model, String email, String password) throws Exception {
        super(model, email, password);
    }


    @Override
    public void addProduct(Product product) throws RemoteException {
        throw new IllegalStateException("You don't have the privileges.");
    }

    @Override public UserList getUsers() throws RemoteException
    {
        throw new IllegalStateException("You don't have the privileges.");
    }
}
