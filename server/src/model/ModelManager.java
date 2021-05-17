package model;

import common.model.*;
import daos.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model {

    private UserList registeredUsers;

    private ProductDAOImpl productDAO;
    private UserDAOImpl userDAO;
    private OrderDAO orderDAO;

    public ModelManager() {
        try {
            productDAO = ProductDAOImpl.getInstance();
            userDAO = UserDAOImpl.getInstance();
            orderDAO = OrderDAOImpl.getInstance();
            createDummyData();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        registeredUsers = new UserList();

    }

    @Override
    public UserList getAllRegisteredUsers() {

        //return registeredUsers;
        try {
            return userDAO.allUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void register(String email, String password, String firstName, String lastName, LocalDate birthday, char gender) throws IllegalArgumentException, IllegalStateException {
        checkRegister(email, password, firstName, lastName, birthday, gender);
        try {
            User user = userDAO.create(email, password, firstName, lastName, new DateTime(birthday.getDayOfMonth(), birthday.getMonthValue(), birthday.getYear()), gender, false);
            //registeredUsers.addUser(user);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private void checkRegister(String email, String password, String firstName, String lastName, LocalDate birthday, char gender){
        // Checks for null or empty arguments.
        if (email == null || email.isEmpty()) throw new IllegalArgumentException("Email can't be empty.");
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("Password can't be empty.");
        if (firstName == null || firstName.isEmpty()) throw new IllegalArgumentException("First name can't be empty.");
        if (lastName == null || lastName.isEmpty()) throw new IllegalArgumentException("Last name can't be empty.");
        if (birthday == null) throw new IllegalArgumentException("Birthday can't be null.");

        // Checks for email argument.
        if (!email.contains("@") || !email.substring(email.indexOf("@") + 1).contains(".")) throw new IllegalArgumentException("Invalid email format. Email must respect user@host.domain format.");
        String[] emailParts = email.split("[@._]");
        if (emailParts.length < 1 || emailParts[0] == null || emailParts[0].isEmpty()) throw new IllegalArgumentException("User part of email can't be empty, user@host.domain .");
        if (emailParts.length < 2 || emailParts[1] == null || emailParts[1].isEmpty()) throw new IllegalArgumentException("Host part of email can't be empty, user@host.domain .");
        if (emailParts.length < 3 || emailParts[2] == null || emailParts[2].isEmpty()) throw new IllegalArgumentException("Domain part of email can't be empty, user@host.domain .");
        if (!emailParts[1].matches("[a-zA-Z0-9]*")) throw new IllegalArgumentException("Host part of email can not contain any symbols, user@host.domain .");
        if (!emailParts[2].matches("[a-zA-Z0-9]*")) throw new IllegalArgumentException("Domain part of email can not contain any symbols, user@host.domain .");
        if (emailParts[0].length() > 64) throw new IllegalArgumentException("User part of email can't be more then 64 chars, user@host.domain .");
        if (emailParts[1].length() > 63) throw new IllegalArgumentException("Host part of email can't be more then 63 chars, user@host.domain .");
        if (emailParts[2].length() > 63) throw new IllegalArgumentException("Domain part of email can't be more then 63 chars, user@host.domain .");
        char c = emailParts[1].toUpperCase().charAt(0);
        if (!('A' <= c && c <= 'Z')) throw new IllegalArgumentException("The first char of the email host part has to be a letter, user@host.domain .");
        if (!emailParts[2].matches(".*[a-zA-Z]+.*")) throw new IllegalArgumentException("Domain part of email has to have at least one letter, user@host.domain .");

        // Checks for password argument.
        if (password.length() < 8) throw new IllegalArgumentException("Password must be at least 8 characters long.");
        if (!password.matches(".*[A-Z]+.*")) throw new IllegalArgumentException("Password must have at least one uppercase letter.");
        if (!password.matches(".*[0-9]+.*")) throw new IllegalArgumentException("Password must have at least one digit.");

        // Checks for first name argument.
        if (firstName.length() < 2) throw new IllegalArgumentException("First name can't have less then 2 characters.");
        if (firstName.length() > 150) throw new IllegalArgumentException("First name can't have more then 150 characters.");

        // Checks for last name argument.
        if (lastName.length() < 2) throw new IllegalArgumentException("Last name can't have less then 2 characters.");
        if (lastName.length() > 150) throw new IllegalArgumentException("Last name can't have more then 150 characters.");

        // Checks for birthday argument.
        LocalDate dateOfToday = LocalDate.now();
        if (birthday.isAfter(LocalDate.of(dateOfToday.getYear() - 14, dateOfToday.getMonth(), dateOfToday.getDayOfMonth()))) throw new IllegalArgumentException("User must be at least 14 years old to register.");
        if (birthday.isBefore(LocalDate.of(dateOfToday.getYear() - 150, dateOfToday.getMonth(), dateOfToday.getDayOfMonth()))) throw new IllegalArgumentException("User can't be more then 150 years old.");

        // Checks for gender argument.
        if (!(gender == 'm' || gender == 'M' || gender == 'f' || gender == 'F')) throw new IllegalArgumentException("User can either be male or female.");

        // Checks if an user with this email is already registered.
        if (registeredUsers.getUser(email) != null) throw new IllegalStateException("An user with this email is already registered.");
    }

    @Override
    public ArrayList<Product> getCatalogOfProducts() {
        // Dummy data.
        //ArrayList<Product> toReturn = new ArrayList<>();
       // toReturn.add(new Product("1", 4, "Pain au Chocolate", "nice", 5));
       // toReturn.add(new Product("2", 1, "Golden Apple", "extra nice", 7.41));
       // toReturn.add(new Product("3", 3, "Sugar Bombs", "niche", 3.22));
        //toReturn.add(new Product("4", 7, "2 kg of Sweets", "niche extra", 1));
        //return toReturn;
        try {
            return (ArrayList<Product>) ProductDAOImpl.getInstance().read();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void createDummyData() {
        try {
            /** Dummy data users.*/
            userDAO.createDummyData("bob@gmail.com", "1234", "Bob", "Bob", new DateTime(2, 3, 2001), 'M', false);
            userDAO.createDummyData("george@gmail.com", "5678", "George", "George", new DateTime(4, 2, 2001), 'M', false);
            userDAO.createDummyData("steve@gmail.com", "9876", "Steve", "Steve", new DateTime(26, 8, 2001), 'M', true);
            userDAO.createDummyData("katy@gmail.com", "123456", "Katy", "Katy", new DateTime(6, 1, 2001), 'F', true);
            /*registeredUsers.addUser(new Customer("bob@gmail.com", "1234"));
        registeredUsers.addUser(new Customer("george@gmail.com", "5678"));
        registeredUsers.addUser(new Employee("steve@gmail.com", "9876"));*/

            /**Dummy data products.*/
            productDAO.createDummyData(4, "Pain au Chocolate", "nice", 5);
            productDAO.createDummyData(1, "Golden Apple", "extra nice", 7.41);
            productDAO.createDummyData(3, "Sugar Bombs", "niche", 3.22);
            productDAO.createDummyData(7, "2 kg of Sweets", "niche extra", 1);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
