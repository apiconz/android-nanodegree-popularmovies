package pe.apiconz.android.nanodegree.popularmovies.pojo;

import java.io.Serializable;

/**
 * Created by armando on 16/08/15.
 */
public class Movie implements Serializable{

    private String id;
    private String posterPath;

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
}
