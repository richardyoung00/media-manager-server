package MediaManager.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* Annotation allows you to restrict access to certain actions.
* Adding @Authentication to a method requires the user to be
* logged in to process that action.
 *
 * The optional parameter 'roles' allows you to restrict access to
 * users with a specific set of roles
 *
 * eg @Authentication(roles="admin,guest,manager")
*
* */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface Authentication {
    public String roles() default "";
}
