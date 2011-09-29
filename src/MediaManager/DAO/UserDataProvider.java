package MediaManager.DAO;

import MediaManager.model.Role;
import MediaManager.model.User;
import sun.rmi.server.UnicastServerRef;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Database access for user operations
 */

public class UserDataProvider extends BaseDAO {

    public UserDataProvider(){
        super();
    }


    public boolean checkUserLogin(String username, String password) {

        User u = findUserByName(username);

        return !(u.getPassword() == null || !u.getPassword().equals(password));

    }

    public User findUserByName(String username) {

        User u = null;
        try{
            String query = "SELECT * FROM User WHERE username = ?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, username);
            ResultSet result = st.executeQuery();

            while(result.next()){
                u = DbToUser(result);
            }
        }  catch(SQLException e){
            System.out.println(e.getMessage());
            //TODO log errors
        }
        return u;
    }

    public User findUserById(int id) {

        User u = null;
        try{
            String query = "SELECT * FROM User WHERE id = ?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            ResultSet result = st.executeQuery();

            while(result.next()){
                u = DbToUser(result);
            }
        }  catch(SQLException e){
            //TODO log errors
        }
        return u;
    }

    public Role findRoleById(int id) {

        Role r = null;
        try{
            String query = "SELECT * FROM Role WHERE id = ?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            ResultSet result = st.executeQuery();

            while(result.next()){
                r = DbToRole(result);
            }
        }  catch(SQLException e){
            //TODO log errors
        }
        return r;
    }

    public Role findRoleByName(String name) {

        Role r = null;
        try{
            String query = "SELECT * FROM Role WHERE name = ?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, name);
            ResultSet result = st.executeQuery();

            while(result.next()){
                r = DbToRole(result);
            }
        }  catch(SQLException e){
            //TODO log errors
        }
        return r;
    }

    public void saveUser(User user){
        if (findUserById(user.getId()) == null){
            createNewUser(user);
        }else{
            updateExistingUser(user);
        }
    }

    private void createNewUser(User user){
        try{
            String query =  "INSERT INTO User "
                    + "(username, password, email) "
                    + "Values (?, ?, ?);";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
            st.executeUpdate(query);

            saveUserRoles(user);
        }  catch(SQLException e){
            //TODO log errors
        }
    }

    private void createNewRole(Role role){
        if (findRoleByName(role.getName()) != null){
            return;
        }
        try{
            String query =  "INSERT INTO Role "
                    + "(name) "
                    + "Values (?);";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, role.getName());
            st.executeUpdate(query);

        }  catch(SQLException e){
            //TODO log errors
        }
    }

    private void createNewUserRole(int userId, int roleId){
        if (userRoleExists(userId, roleId)){
            return;
        }
        try{
            String query =  "INSERT INTO UserRole "
                    + "(userId, roleId) "
                    + "Values (?, ?);";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, userId);
            st.setInt(2, roleId);
            st.executeUpdate(query);

        }  catch(SQLException e){
            //TODO log errors
        }
    }


    private boolean userRoleExists(int userId, int roleId) {
        try{
            String query = "SELECT * FROM UserRole WHERE userId = ? AND roleId = ?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, userId);
            st.setInt(2, roleId);
            ResultSet result = st.executeQuery();

            return result.next();
        }  catch(SQLException e){
            //TODO log errors
        }
        return false;
    }


    private void saveUserRoles(User user){
        List<Role> roles = user.getRoles();
        if (roles == null){
            return;
        }

        for (Role r : roles){
            if (findRoleById(r.getId()) == null){
                createNewRole(r);
                r = findRoleByName(r.getName());
            }
            createNewUserRole(user.getId(), r.getId());
        }
    }

    private void updateUserRoles(User user){
        List<Role> roles = user.getRoles();
        List<Role> dbRoles = getUserRoles(user.getId());

        StringBuilder userRolesToDelete = new StringBuilder();
        List<Integer> userRolesToAdd = new ArrayList<Integer>();

        for (Role r : roles){
            if(!dbRoles.contains(r)){
                userRolesToAdd.add(r.getId());
            }
        }
        for (Role r : dbRoles){
            if(!roles.contains(r)){
                if (!userRolesToDelete.toString().equals("")){
                    userRolesToDelete.append(", ");
                }
                userRolesToDelete.append(r.getId());
            }
        }

        if(!userRolesToDelete.toString().equals(""))
        try{
            String query = "DELETE FROM UserRole WHERE userId = ? AND roleId in (?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, user.getId());
            st.setString(2, userRolesToDelete.toString());
            st.executeUpdate();

        }   catch(SQLException e){
            //TODO log
        }

        for (int roleId : userRolesToAdd){
            createNewUserRole(user.getId(), roleId);
        }
    }

    private void updateExistingUser(User user){

        try{
            String query = "UPDATE User SET" +
                    "username = ?, " +
                    "password = ?, " +
                    "email = ? " +
                    "WHERE id = ?;";
            PreparedStatement st = con.prepareStatement(query);

            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
            st.setInt(4, user.getId());
            st.executeUpdate();

            updateUserRoles(user);
        }  catch(SQLException e){
            //TODO log errors
        }
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();
        try{
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM User;");

            while(result.next()){
                users.add(DbToUser(result));
            }
        }  catch(SQLException e){
            //TODO log errors
        }

        return users;
    }

    private User DbToUser(ResultSet result) throws SQLException{
        User u = new User();
        u.setId(result.getInt("id"));
        u.setUsername(result.getString("username"));
        u.setPassword(result.getString("password"));
        u.setRoles(getUserRoles(u.getId()));
        return u;
    }

    private Role DbToRole(ResultSet result) throws SQLException{
        Role r = new Role();
        r.setId(result.getInt("id"));
        r.setName(result.getString("name"));
        return r;
    }

    public List<Role> getUserRoles(int userId){
        List<Role> userRoles = new ArrayList<Role>();
        try{
            String query = "SELECT Role.id, Role.name " +
                    "FROM UserRole LEFT JOIN Role " +
                    "ON Role.id = UserRole.roleId " +
                    "WHERE UserRole.userId = ?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, userId);
            ResultSet result = st.executeQuery();

            while(result.next()){
                userRoles.add(DbToRole(result));
            }
        }  catch(SQLException e){
            //TODO log errors
        }
        return userRoles;
    }

    //fetches the guest user or creates it if it does not exist
    public User getGuest() {
        User guest = findUserByName("Guest");

        if (guest == null) {
            //create the guest and add it to the database;
            guest = new User();
            guest.setUsername("Guest");
            guest.setPassword(UUID.randomUUID().toString());

            Role guestRole = new Role("guest");
            ArrayList<Role> roles = new ArrayList<Role>();
            roles.add(guestRole);
            guest.setRoles(roles);

            createNewUser(guest);
            guest = findUserByName("Guest");
        }
        return guest;
    }


}
