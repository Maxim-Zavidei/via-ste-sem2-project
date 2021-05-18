package mediator;

import common.model.Product;
import common.model.User;
import common.model.UserList;
import common.network.RemoteClientInterface;
import common.network.RemoteServerInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;

public class Client implements ClientTarget, RemoteClientInterface {

    private RemoteServerInterface server;

    public Client() throws Exception {
        server = (RemoteServerInterface) Naming.lookup("rmi://127.0.0.1:1099/access");
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public void stop() {
        try {
            server.deauthenticate(this);
            UnicastRemoteObject.unexportObject(this, true);
        } catch (Exception e) {
            // Do nothing.
        }
    }

    @Override
    public void authenticate(String email, String password) throws Exception {
        server = server.authenticate(this, email, password);
    }

    @Override
    public boolean deauthenticate() {
        try {
            server.deauthenticate(this);
        } catch (RemoteException e) {
            // Do nothing.
        }
        try {
            server = (RemoteServerInterface) Naming.lookup("rmi://127.0.0.1:1099/access");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void register(String email, String password, String firstName, String lastName, LocalDate birthday, char gender) throws Exception {
        server.register(email, password, firstName, lastName, birthday, gender);
    }

    @Override
    public User getAuthenticatedUser() throws Exception {
        return server.getAuthenticatedUser();
    }

    @Override
    public UserList getAllRegisteredUsers() throws Exception {
        return server.getAllRegisteredUsers();
    }

    @Override
    public void updateUser(String oldEmail, String newEmail, String password, String firstName, String lastName, LocalDate birthday, char gender, boolean isEmployee) throws Exception {
        server.updateUser(oldEmail, newEmail, password, firstName, lastName, birthday, gender, isEmployee);
    }

    @Override public void updateUser(String email, User user) throws Exception
    {
        server.updateUser(email, user);
    }

    @Override
    public void removeUser(String email) throws Exception {
        server.removeUser(email);
    }

    @Override public void addUser(User user) throws Exception
    {
        server.addUser(user);
    }

    @Override
    public ArrayList<Product> getCatalogOfProducts() throws Exception {
        return server.getCatalogOfProducts();
    }

    @Override
    public void addProduct(Product product) throws Exception {
        server.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        server.updateProduct(product);
    }

    @Override
    public void removeProduct(Product product) throws Exception {
        server.removeProduct(product);
    }
}
