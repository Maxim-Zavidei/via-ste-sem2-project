package mediator;

import common.model.Product;
import common.network.RemoteClientInterface;
import common.network.RemoteServerInterface;
import model.Model;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class GenericAccessType implements RemoteServerInterface {

    private Model model;
    private String email;
    private String password;

    public GenericAccessType(Model model, String email, String password) throws RemoteException {
        this.model = model;
        this.email = email;
        this.password = password;
        UnicastRemoteObject.exportObject(this, 0);
    }

    public Model getModel() {
        return model;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GenericAccessType)) return false;
        GenericAccessType tmp = (GenericAccessType) obj;
        return email.equals(tmp.email) && password.equals(tmp.password);
    }

    // Overriding the hashCode method is necessary in order for the reverse entry lookup of the bidirectional map to work properly.
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + email.hashCode();
        hash = 31 * hash + password.hashCode();
        return hash;
    }

    @Override
    public RemoteServerInterface authenticate(RemoteClientInterface client, String email, String password) throws RemoteException {
        throw new IllegalStateException("Can't perform request, already authenticated.");
    }

    @Override
    public void deauthenticate(RemoteClientInterface client) throws RemoteException {
        try {
            UnicastRemoteObject.unexportObject(this, true);
            ((RemoteServerInterface) Naming.lookup("rmi://127.0.0.1:1099/access")).deauthenticate(client);
        } catch (Exception e) {
            throw new IllegalStateException("Could not deauthenticate.");
        }
    }

    @Override
    public void register(String email, String password, String firstName, String lastName, LocalDate birthday, char gender) throws RemoteException {
        throw new IllegalStateException("Can't perform register request, already authenticated.");
    }

    @Override
    public ArrayList<Product> getCatalogOfProducts() throws RemoteException {
        return model.getCatalogOfProducts();
    }
}
