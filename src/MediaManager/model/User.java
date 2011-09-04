package MediaManager.model;

import cat.quickdb.annotation.Column;
import cat.quickdb.annotation.Table;

import java.util.ArrayList;

/**
 * Represents a user in the DB.
 */
@Table
public class User {

    private int id;
    private String username;
    private String password;
    @Column(collectionClass = "Role")
    private ArrayList roles = new ArrayList();

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }
}
