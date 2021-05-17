package mediator;

import common.model.Product;
import model.Model;

import java.rmi.RemoteException;

public class CustomerAuthenticated extends GenericAccessType {
    public CustomerAuthenticated(Model model, String email, String password) throws Exception {
        super(model, email, password);
    }


    @Override
    public void addProduct(Product product) throws RemoteException {
        throw new IllegalStateException("You don't have the privileges.");
    }
}
