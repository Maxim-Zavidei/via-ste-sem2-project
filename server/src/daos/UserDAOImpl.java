package daos;

import com.sun.tools.attach.AgentInitializationException;
import common.model.*;

import java.sql.*;

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
                    connection.prepareStatement("INSERT INTO user(email, password, f_name, l_name, birthday, age, gender, isEmployee) VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setObject(5, birthday, Types.DATE);
            statement.setInt(6, DateTime.yearsBetween(birthday));
            statement.setObject(7, birthday, Types.CHAR);
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

    }

    @Override
    public void delete(User user)  throws SQLException{

    }
}
