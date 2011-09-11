package MediaManager.action;

import MediaManager.model.User;
import MediaManager.util.PropertyFetcher;
import MediaManager.util.PropertyInjector;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * Base action class
 */
public abstract class BaseActionBean implements ActionBean {
    private ActionBeanContext context;

    protected PropertyFetcher properties = new PropertyFetcher();


    public BaseActionBean() {
        super();
    }

    public void setContext(ActionBeanContext context) {
        this.context = (ActionBeanContext) context;
    }

    public ActionBeanContext getContext() {
        return this.context;
    }

    public User getCurrentUser() {
        return (User) getContext().getRequest().getSession().getAttribute("user");
    }

    public void setCurrentUser(User user) {
        getContext().getRequest().getSession().setAttribute("user", user);
    }


}

