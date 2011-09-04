package MediaManager.extensions;

import MediaManager.DAO.UserDataProvider;
import MediaManager.action.BaseActionBean;
import MediaManager.action.LoginActionBean;
import MediaManager.model.Role;
import MediaManager.model.User;
import MediaManager.util.Authentication;
import MediaManager.util.PropertyFetcher;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;

import javax.servlet.http.HttpSession;
import java.lang.reflect.AnnotatedElement;


@Intercepts(LifecycleStage.HandlerResolution)
public class SecurityInterceptor implements Interceptor {


    private UserDataProvider userDataProvider = new UserDataProvider();
    private PropertyFetcher properties = new PropertyFetcher();


    public Resolution intercept(ExecutionContext execContext) throws Exception {
        Resolution resolution = execContext.proceed();
        BaseActionBean actionBean = (BaseActionBean) execContext.getActionBean();

        AnnotatedElement method = execContext.getHandler();

        if (method != null && method.isAnnotationPresent(Authentication.class)) {
            HttpSession session = execContext.getActionBeanContext().getRequest().getSession(false);


            //handle automatically logging guests in, if feature is allowed
            if (session != null && session.getAttribute("user") == null && properties.getBoolean("allowGuest")) {
                session.setAttribute("user", userDataProvider.getGuest());
            }

            if (session == null || session.getAttribute("user") == null) {
                return new RedirectResolution(LoginActionBean.class);
            }

            if (!checkRoles(method.getAnnotation(Authentication.class).roles(), (User) session.getAttribute("user"))) {
                //User is logged in, but does not have correct roles to view page
                return new RedirectResolution("/error.jsp");
            }
        }
        return resolution;
    }

    private boolean checkRoles(String rolesStr, User user) {
        //if the user is logged in, and no roles are defined for the function, then allow access
        if (rolesStr.trim().isEmpty() && user != null) {
            return true;
        }
        //if roles are defined for the function, check if the user has the correct roles
        String[] allowedRoles = rolesStr.trim().toLowerCase().split(",");
        for (String r1 : allowedRoles) {
            for (Role r2 : user.getRoles()) {
                if (r1.trim().equals(r2.getName().toLowerCase())) {
                    return true;
                }
            }
        }
        return false;


    }
}
