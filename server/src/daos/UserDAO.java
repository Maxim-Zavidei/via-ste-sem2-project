package daos;

import common.model.*;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends DAO{
    User create(String email, String password, String firstName, String lastName, DateTime birthday, char sex, boolean isEmployee) throws SQLException;
    void update(User user) throws SQLException;
    void updateAge(User user) throws SQLException;
    void updatePassword(User user) throws SQLException;
    void delete(User user) throws  SQLException;
    UserList allEmployees() throws SQLException;
    UserList allCustomers() throws SQLException;
    UserList allUsers() throws SQLException;
    User readByEmail(String email) throws SQLException;
    void createDummyData(String email, String password, String firstName, String lastName, DateTime birthday, char sex, boolean isEmployee) throws SQLException;
}
