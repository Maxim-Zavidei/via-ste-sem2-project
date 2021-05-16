package daos;

import common.model.DateTime;
import common.model.User;

import java.sql.SQLException;

public interface UserDAO {
    User create(String email, String password, String firstName, String lastName, DateTime birthday, char sex, boolean isEmployee) throws SQLException;
    void update(User user) throws SQLException;
    void delete(User user) throws  SQLException;
}
