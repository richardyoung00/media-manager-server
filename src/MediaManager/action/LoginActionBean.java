package MediaManager.action;

import MediaManager.DAO.UserDataProvider;
import MediaManager.model.User;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;

/**
 * Action class for performing user operations
 */

@UrlBinding("/login")
public class LoginActionBean extends BaseActionBean {


    private UserDataProvider userDataProvider = new UserDataProvider();


    @Validate(required = true)
    private String username;
    @Validate(required = true)
    private String password;

    private String targetUrl;

    @DontValidate
    @DefaultHandler
    public Resolution view() {

        return new ForwardResolution("/WEB-INF/jsp/login.jsp?");
    }


    public Resolution login() {
        User user = userDataProvider.findUserByName(getUsername());

        if (user.getId() <= 0) {
            getContext().getValidationErrors().add("username", new SimpleError("Username not found"));
            return getContext().getSourcePageResolution();

        } else if (!user.getPassword().equals(getPassword())) {
            getContext().getValidationErrors().add("password", new SimpleError("Incorrect password"));
            return getContext().getSourcePageResolution();

        } else {
            setCurrentUser(user);

            return new RedirectResolution(HomePageActionBean.class);

        }

    }

    public Resolution logout() {
        getContext().getRequest().getSession().removeAttribute("user");
        return new RedirectResolution(LoginActionBean.class);
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

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
}
