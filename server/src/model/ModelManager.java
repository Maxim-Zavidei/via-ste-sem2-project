package model;

import common.model.*;
import daos.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model {

    private ProductDAO productDAO;
    private UserDAO userDAO;
    private OrderDAO orderDAO;

    public ModelManager() throws SQLException {
        productDAO = ProductDAOImpl.getInstance();
        userDAO = UserDAOImpl.getInstance();
        orderDAO = OrderDAOImpl.getInstance();

        // Dummy data users.
//        userDAO.create(new Customer("bob@gmail.com", "Aaaa1234", "Bob", "Bob", new DateTime(2, 3, 2001), 'M'));
//        userDAO.create(new Customer("george@gmail.com", "Aaaa5678", "George", "George", new DateTime(4, 2, 2001), 'M'));
//        userDAO.create(new Employee("steve@gmail.com", "Aaaa9876", "Steve", "Steve", new DateTime(26, 8, 2001), 'M'));
//        userDAO.create(new Employee("katy@gmail.com", "Aaaa123456", "Katy", "Katy", new DateTime(6, 1, 2001), 'F'));

        // Dummy data products.
//        productDAO.create(3, "Baklava", "Baklava is very tasty", 2.5);
//        productDAO.create(4, "Pain au Chocolate", "nice", 5);
//        productDAO.create(1, "Golden Apple", "extra nice", 7.41);
//        productDAO.create(3, "Sugar Bombs", "niche", 3.22);
//        productDAO.create(7, "2 kg of Sweets", "niche extra", 1);
    }

    @Override
    public UserList getAllRegisteredUsers() throws IllegalStateException {
        try {
            return userDAO.allUsers();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public void register(String email, String password, String firstName, String lastName, LocalDate birthday, char gender) throws IllegalArgumentException, IllegalStateException {
        try {
            // Validate first the arguments through creating an object of type customer.
            User toCreate = new Customer(email, password, firstName, lastName, birthday, gender);
            // Checks if an user with this email is already registered.
            if (userDAO.readByEmail(email) != null) throw new IllegalStateException("An user with this email is already registered.");
            // Store the newly registered user in the database.
            userDAO.create(toCreate);
        } catch (Exception e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public User getUser(String email) throws IllegalArgumentException, IllegalStateException {
        if (email == null || email.isEmpty()) throw new IllegalArgumentException("Email can not be empty.");
        try {
            User toReturn = userDAO.readByEmail(email);
            if (toReturn == null) throw new IllegalStateException("No registered user with such email could be found.");
            return toReturn;
        } catch (Exception e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public void updateUser(String oldEmail, String newEmail, String password, String firstName, String lastName, LocalDate birthday, char gender, boolean isEmployee) throws IllegalArgumentException, IllegalStateException {
        try {
            User old = userDAO.readByEmail(oldEmail);
            // Checks if an user with this old email exists.
            if (old == null) throw new IllegalStateException("No registered user with such email could be found.");

            // Check if the new email is not already taken.
            if (userDAO.readByEmail(newEmail) != null && !newEmail
                .equals(oldEmail)) throw new IllegalStateException("The given new email is already taken.");
            // Validate first the arguments through creating an object of type user.
            User current = isEmployee ? new Employee(newEmail, password, firstName, lastName, birthday, gender) : new Customer(newEmail, password, firstName, lastName, birthday, gender);
            // Update the newly registered user in the database and remove the old one.
            userDAO.delete(oldEmail);
            userDAO.create(current);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public void removeUser(String email) throws IllegalArgumentException, IllegalStateException {
        if (email == null || email.isEmpty()) throw new IllegalArgumentException("Email can not be empty.");
        try {
            if (userDAO.readByEmail(email) == null) throw new IllegalStateException("No registered user with such email could be found.");
            userDAO.delete(email);
        } catch (Exception e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public ArrayList<Product> getCatalogOfProducts() throws IllegalStateException {
        try {
            return new ArrayList<>(ProductDAOImpl.getInstance().read());
        } catch (Exception e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public void addProduct(Product product) throws IllegalStateException {
        try {
            productDAO.create(product.getQuantity(), product.getName(), product.getDescription(), product.getPrice());
        } catch (Exception e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public void updateProduct(Product product) throws IllegalStateException {
        try {
            if (productDAO.getById(product.getId()) == null) throw new IllegalStateException("No such product could be found.");
            productDAO.update(product);
        } catch (Exception e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public void removeProduct(Product product) throws IllegalStateException {
        try {
            if (productDAO.getById(product.getId()) == null) throw new IllegalStateException("No such product could be found.");
            productDAO.delete(product);
        } catch (Exception e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }
}
