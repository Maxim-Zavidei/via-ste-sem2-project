package mediator;

import common.model.Product;
import model.Model;

import java.rmi.RemoteException;

public class EmployeeAuthenticated extends GenericAccessType {
    public EmployeeAuthenticated(Model model, String email, String password) throws Exception {
        super(model, email, password);
    }


    @Override
    public void addProduct(Product product) throws RemoteException {
        getModel().addProduct(product);
    }
}
