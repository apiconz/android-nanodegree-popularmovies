package pe.apiconz.android.nanodegree.popularmovies.pojo;

import java.io.Serializable;

/**
 * Created by armando on 16/08/15.
 */
public class Movie implements Serializable{

    private String id;
    private String posterPath;
    private String title;
    private String userRating;
    private String releaseDate;
    private String synopsis;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public String toString() {
        return "{id:" + this.id + ", posterPath:" + this.posterPath + "}";
    }
}
