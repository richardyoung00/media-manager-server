package MediaManager.action;

import MediaManager.DAO.UserDataProvider;
import MediaManager.model.User;
import MediaManager.util.Authentication;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import java.util.List;

/**
 * User operations like add edit and delete.
 */
@UrlBinding("/users/{$event}")
public class UserActionBean extends BaseActionBean{

    private List<User> users;
    private UserDataProvider userDataProvider = new UserDataProvider();

    @Authentication
    @DefaultHandler
    public Resolution view() {
        users = userDataProvider.getAllUsers();
        return new ForwardResolution("/WEB-INF/jsp/users.jsp");
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
