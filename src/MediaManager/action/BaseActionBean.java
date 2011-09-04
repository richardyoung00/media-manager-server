package MediaManager.action;

import MediaManager.model.User;
import MediaManager.util.PropertyInjector;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * Base action class
 */
public abstract class BaseActionBean implements ActionBean {
    ActionBeanContext context;


    private PropertyInjector propertyInjector = new PropertyInjector();

    public BaseActionBean() {
        super();
        propertyInjector.injectProperties(this);
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

