package common.network;

import common.model.Product;
import common.model.User;
import common.model.UserList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface RemoteServerInterface extends Remote {
    RemoteServerInterface authenticate(RemoteClientInterface client, String email, String password) throws RemoteException;
    void deauthenticate(RemoteClientInterface client) throws RemoteException;
    void register(String email, String password, String firstName, String lastName, LocalDate birthday, char gender) throws RemoteException;
    User getAuthenticatedUser() throws RemoteException;
    ArrayList<Product> getCatalogOfProducts() throws RemoteException;
    void addProduct(Product product) throws RemoteException;
    UserList getUsers() throws RemoteException;
}
