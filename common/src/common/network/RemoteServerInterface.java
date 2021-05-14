package common.network;

import common.model.Product;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface RemoteServerInterface extends Remote {
    RemoteServerInterface authenticate(RemoteClientInterface client, String email, String password) throws RemoteException;
    void deauthenticate(RemoteClientInterface client) throws RemoteException;
    void register(String email, String password, String firstName, String lastName, LocalDate birthday, char gender) throws RemoteException;
    ArrayList<Product> getCatalogOfProducts() throws RemoteException;
}
