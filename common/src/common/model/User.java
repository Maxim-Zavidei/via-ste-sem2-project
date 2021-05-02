package common.model;

public class User {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private DateTime birthday;
    private String gender;

    public User(String email, String password, String username, String firstName, String lastName, DateTime birthday, String gender){
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
    }

    public User(String email, String password){
    }
}
