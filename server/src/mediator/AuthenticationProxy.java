package mediator;

import common.model.Product;
import common.model.User;
import common.model.UserList;
import common.network.RemoteClientInterface;
import common.network.RemoteServerInterface;
import common.utility.collection.BidirectionalHashMap;
import model.Model;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;

public class AuthenticationProxy implements RemoteServerInterface {

    private Model model;
    private UserList cache;
    private BidirectionalHashMap<RemoteClientInterface, GenericAccessType> authenticatedInstances;

    public AuthenticationProxy(Model model) throws Exception {
        this.model = model;
        cache = model.getAllRegisteredUsers();
        authenticatedInstances = new BidirectionalHashMap<>();
        UnicastRemoteObject.exportObject(this, 0);
        Naming.rebind("access", this);
    }

    @Override
    public RemoteServerInterface authenticate(RemoteClientInterface client, String email, String password) throws RemoteException {
        // Validate the provided arguments.
        if (email == null || email.isEmpty()) throw new IllegalArgumentException("Email value can't be null or empty.");
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("Password value can't be null or empty.");
        // Check if such a user is registered in the system.
        User tmp = cache.getUser(email);
        if (tmp == null || !tmp.getPassword().equals(password)) throw new IllegalArgumentException("Invalid email or password.");
        // Checks if the given user is already logged in.
        GenericAccessType toReturn;
        try {
            toReturn = tmp.isEmployee() ? new EmployeeAuthenticated(model, email, password) : new CustomerAuthenticated(model, email, password);
        } catch (Exception e) {
            throw new IllegalStateException("Could not authenticate at this moment, try later.");
        }
        if (authenticatedInstances.getKey(toReturn) != null) throw new IllegalStateException("This user is already authenticated.");
        // Return the real subject to be replaced instead of the proxy on the client side.
        authenticatedInstances.put(client, toReturn);
        return toReturn;
    }

    @Override
    public void deauthenticate(RemoteClientInterface client) {
        if (authenticatedInstances.getValue(client) != null) authenticatedInstances.remove(client);
    }

    @Override
    public void register(String email, String password, String firstName, String lastName, LocalDate birthday, char gender) throws RemoteException {
        model.register(email, password, firstName, lastName, birthday, gender);
        cache = model.getAllRegisteredUsers();
    }

    @Override
    public ArrayList<Product> getCatalogOfProducts() throws RemoteException {
        throw new IllegalStateException("Authenticate in order to perform this request.");
    }
}
