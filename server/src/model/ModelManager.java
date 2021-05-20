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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
            if (userDAO.readByEmail(newEmail) != null && !newEmail.equals(oldEmail)) throw new IllegalStateException("The given new email is already taken.");
            // Validate first the arguments through creating an object of type user.
            User current = isEmployee ? new Employee(newEmail, password, firstName, lastName, birthday, gender) : new Customer(newEmail, password, firstName, lastName, birthday, gender);
            // Update the newly registered user in the database and remove the old one.
            userDAO.delete(oldEmail);
            userDAO.create(current);
        } catch (SQLException e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override public void updateUser(String email, User user)
        throws IllegalStateException, IllegalArgumentException
    {
        try {
            User old = userDAO.readByEmail(email);
            // Checks if an user with this old email exists.
            if (old == null) throw new IllegalStateException("No registered user with such email could be found.");
            // Check if the new email is not already taken.
            if (userDAO.readByEmail(user.getEmail()) != null && !user.getEmail().equals(email)) throw new IllegalStateException("The given new email is already taken.");
            // Update the newly registered user in the database and remove the old one.
            userDAO.delete(email);
            userDAO.create(user);
        } catch (SQLException e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public void removeUser(String email) throws IllegalArgumentException, IllegalStateException {
        if (email == null || email.isEmpty()) throw new IllegalArgumentException("Email can not be empty.");
        try {
            if (userDAO.readByEmail(email) == null) throw new IllegalStateException("No registered user with such email could be found.");
            userDAO.delete(email);
        } catch (SQLException e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override public void addUser(User user)
        throws IllegalArgumentException, IllegalStateException
    {
        if (user==null || user.getEmail() == null || user.getEmail().isEmpty()) throw new IllegalArgumentException("Email can not be empty.");
        try {
            if (userDAO.readByEmail(user.getEmail()) != null) throw new IllegalStateException("Registered user with such email could be found.");
            userDAO.create(user);
        } catch (SQLException e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public ArrayList<Product> getCatalogOfProducts() throws IllegalStateException {
        try {
            return new ArrayList<>(ProductDAOImpl.getInstance().read());
        } catch (SQLException e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public Product getProductById(String productId) throws IllegalArgumentException, IllegalStateException {
        if (productId == null || productId.isEmpty()) throw new IllegalArgumentException("Product id can not be empty.");
        try {
            Product toReturn = productDAO.getById(productId);
            if (toReturn == null) throw new IllegalStateException("No such product could be found.");
            return toReturn;
        } catch (SQLException e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public void addProduct(int quantity, String name, String description, double price) throws IllegalStateException {
        if (quantity < 1) throw new IllegalArgumentException("Product quantity can't be less then 1.");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Product name can't be empty.");
        if (name.length() > 100) throw new IllegalArgumentException("Product name can't be longer then 100 chars.");
        if (description == null) description = "";
        if (description.length() > 10000) throw new IllegalArgumentException("Product description can't be longer then 10 000 chars.");
        if (price < 0) throw new IllegalArgumentException("Product price can't be negative.");
        try {
            if (!productDAO.readByName(name).isEmpty()) throw new IllegalStateException("A product with this name already exists.");
            productDAO.create(quantity, name, description, price);
        } catch (SQLException e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public void updateProduct(Product product) throws IllegalStateException {
        try {
            if (productDAO.getById(product.getId()) == null) throw new IllegalStateException("No such product could be found.");
            // Check if the new name of the product conflicts with another product's name.
            Product tmp = productDAO.readByName(product.getName()).isEmpty() ? null : productDAO.readByName(product.getName()).get(0);
            if (tmp != null && !tmp.getId().equals(product.getId())) throw new IllegalStateException("This product name is already taken.");
            productDAO.update(product);
        } catch (SQLException e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public void removeProduct(Product product) throws IllegalStateException {
        try {
            if (productDAO.getById(product.getId()) == null) throw new IllegalStateException("No such product could be found.");
            productDAO.delete(product);
        } catch (SQLException e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }

    @Override
    public void placeOrder(Order order) throws IllegalStateException{
        try{
            orderDAO.create(order.getProducts(), order.getDate(), order.getCustomer());
        }catch (SQLException e) {
            throw new IllegalStateException("Server is unavailable at the moment. Try Later.");
        }
    }
}
