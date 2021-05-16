package daos;

import common.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl  implements UserDAO{
    private  static UserDAOImpl instance;

    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=cake_store";
    private static final String CONNECTION_USER = "postgres";
    private static final String CONNECTION_PASSWORD = "6364";

    private UserDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static  synchronized UserDAOImpl getInstance() throws  SQLException{
        if(instance == null){
            instance = new UserDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);
    }

    @Override
    public User create(String email, String password, String firstName, String lastName, DateTime birthday, char sex, boolean isEmployee) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO \"user\"(email, password, f_name, l_name, birthday, age, gender, isemployee) VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setObject(5, birthday.getSortableDate(), Types.DATE);
            statement.setInt(6, DateTime.yearsBetween(birthday));
            statement.setObject(7, sex, Types.CHAR);
            statement.setBoolean(8, isEmployee);
            statement.executeUpdate();
            if(isEmployee){
                return new Employee(email, password,firstName,lastName, birthday, sex);
            }
            else return new Customer(email, password,firstName,lastName, birthday, sex);
        }
    }

    @Override
    public void update(User user)  throws SQLException{
        try(Connection connection = getConnection()){
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE \"user\" SET  password = ?, f_name = ?, l_name = ?, birthday = ?, age = ?, gender = ?, isemployee = ? WHERE email = ?");
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setObject(4, user.getBirthday(), Types.DATE);
            statement.setInt(5, user.getAge());
            statement.setObject(6, user.getGender(), Types.CHAR);
            statement.setBoolean(7, user instanceof Employee);
            statement.setString(8, user.getEmail());
            statement.executeUpdate();
        }
    }

    @Override
    public void updateAge(User user) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE \"user\" SET  age = ? WHERE email = ?");
            statement.setInt(1, DateTime.yearsBetween(user.getBirthday()));
            statement.setString(2, user.getEmail());
            statement.executeUpdate();
        }
    }

    @Override
    public void updatePassword(User user) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE \"user\" SET  password = ? WHERE email = ?");
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(User user)  throws SQLException{
        try(Connection connection = getConnection()){
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM \"user\" WHERE email = ?");
            statement.setInt(1, Integer.parseInt(user.getEmail()));
            statement.executeUpdate();
        }
    }

    @Override
    public List<Employee> allEmployees() throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM \"user\" WHERE isemployee = true");
            ResultSet employeeSet = statement.executeQuery();
            ArrayList<Employee> employees = new ArrayList<>();
            while (employeeSet.next()){
                String email = employeeSet.getString("email");
                String pass = employeeSet.getString("password");
                String fname = employeeSet.getString("f_name");
                String lname = employeeSet.getString("l_name");
                Date date = (Date) employeeSet.getObject("birthday");
                DateTime bday = new DateTime(date.toLocalDate().getDayOfMonth(), date.toLocalDate().getMonthValue(), date.toLocalDate().getYear());
                int age = employeeSet.getInt("age");
                char gender = (Character) employeeSet.getString("gender").charAt(0);
                Employee employee = new Employee(email, pass, fname, lname, bday, gender);
                employees.add(employee);
            }
            return employees;
        }
    }
}
