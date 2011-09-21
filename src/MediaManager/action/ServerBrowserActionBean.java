package MediaManager.action;

import MediaManager.util.Authentication;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;


@UrlBinding("/server/{mediaType=series}")
public class ServerBrowserActionBean extends BaseActionBean {

    private String mediaType;

    @Authentication
    @DefaultHandler
    public Resolution view() {
        if (mediaType != null && !mediaType.isEmpty()) {
            String browsePath = properties.getString("media.path."+mediaType);
            getContext().getRequest().getSession().setAttribute("browsePath",browsePath);
        }
        return new ForwardResolution("/WEB-INF/jsp/serverBrowser.jsp");
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
