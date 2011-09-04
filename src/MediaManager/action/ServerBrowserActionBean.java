package MediaManager.action;

import MediaManager.util.Authentication;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/server.action")
public class ServerBrowserActionBean extends BaseActionBean {

    @Authentication
    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution("/WEB-INF/jsp/serverBrowser.jsp");
    }
}
