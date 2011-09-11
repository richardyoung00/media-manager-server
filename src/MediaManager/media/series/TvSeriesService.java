package MediaManager.media.series;

import MediaManager.util.PropertyFetcher;
import com.moviejukebox.thetvdb.TheTVDB;
import com.moviejukebox.thetvdb.model.Banners;
import com.moviejukebox.thetvdb.model.Episode;
import com.moviejukebox.thetvdb.model.Series;

import java.util.List;

/**
 * Service to manage the pulling of tv series information from the internet
 */
public class TvSeriesService {
    private PropertyFetcher properties = new PropertyFetcher();
    private TheTVDB tvDB;

    public TvSeriesService(){
        tvDB = new TheTVDB(properties.getString("apiKey"));
    }

    public List<Series> searchSeries(String query) {
        List<Series> results = tvDB.searchSeries(query, "en");
        return results;
    }

    public Series getSeriesInfo(Series s) {
        Series series = tvDB.getSeries(s.getId(), "en");

        return series;
    }

    public Episode getEpisode(Series series, int s, int e ){
        Episode episode = tvDB.getEpisode(series.getId(), s, e, "en" );
        return episode;
    }

    public List<Episode> getAllEpisodes(Series s){
        return tvDB.getAllEpisodes(s.getId(), "en");
    }

    public void getBanners(){
        Banners banners = tvDB.getBanners("73739");
    }

}
