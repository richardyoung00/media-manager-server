package MediaManager.DAO;

import MediaManager.model.Role;
import MediaManager.model.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Database access for user operations
 */

public class UserDataProvider extends BaseDAO {


    public boolean checkUserLogin(String username, String password) {

        User u = new User();
        //dbAdmin.obtainWhere(u, "username = '" + username + "'" );

        if (u.getPassword() == null || !u.getPassword().equals(password)) {
            return false;
        }

        return true;

    }

    public User findUser(String username) {

        User u = new User();
        dbAdmin.obtainWhere(u, "username = '" + username + "'");

        return u;
    }

    //fetches the guest user or creates it if it does not exist
    public User getGuest() {
        User guest = findUser("Guest");

        if (guest.getId() <= 0) {
            //create the guest and add it to the database;
            guest.setUsername("Guest");
            guest.setPassword(UUID.randomUUID().toString());

            Role guestRole = new Role("guest");
            ArrayList<Role> roles = new ArrayList<Role>();
            roles.add(guestRole);

            guest.setRoles(roles);

            dbAdmin.save(guest);

            guest = findUser("Guest");
        }
        return guest;
    }
}
