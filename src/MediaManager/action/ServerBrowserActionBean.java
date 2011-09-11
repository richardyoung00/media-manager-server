package MediaManager.action;

import MediaManager.util.Authentication;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import java.util.Properties;

@UrlBinding("/server.action")
public class ServerBrowserActionBean extends BaseActionBean {

    private String media;

    @Authentication
    @DefaultHandler
    public Resolution view() {
        if (media != null && !media.isEmpty()) {
            String browsePath = properties.getString("media.path."+media);
            getContext().getRequest().getSession().setAttribute("browsePath",browsePath);
        }
        return new ForwardResolution("/WEB-INF/jsp/serverBrowser.jsp");
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }
}
