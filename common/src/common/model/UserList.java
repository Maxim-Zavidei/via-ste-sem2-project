package common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserList implements Serializable {

    private Map<String, User> userList;

    public UserList() {
        userList = new HashMap<>();
    }

    public int getSize() {
        return new ArrayList<>(userList.values()).size();
    }

    public ArrayList<User> getAllUsers() {
        return new ArrayList<>(userList.values());
    }

    public void addUser(User user) {
        userList.put(user.getEmail(), user);
    }

    public User removeUser(String email) {
        return userList.remove(email);
    }

    public User removeUser(User user) {
        return removeUser(user.getEmail());
    }

    public User getUser(String email) {
        return userList.get(email);
    }

    public User getUser(User user) {
        return getUser(user.getEmail());
    }
}