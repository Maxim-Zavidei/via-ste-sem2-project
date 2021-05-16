package daos;

import common.model.DateTime;
import common.model.Employee;
import common.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    User create(String email, String password, String firstName, String lastName, DateTime birthday, char sex, boolean isEmployee) throws SQLException;
    void update(User user) throws SQLException;
    void updateAge(User user) throws SQLException;
    void updatePassword(User user) throws SQLException;
    void delete(User user) throws  SQLException;
    List<Employee> allEmployees() throws SQLException;
}
