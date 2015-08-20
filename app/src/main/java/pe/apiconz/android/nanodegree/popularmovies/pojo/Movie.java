package pe.apiconz.android.nanodegree.popularmovies.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by armando on 16/08/15.
 * http://developer.android.com/reference/android/os/Parcelable.html
 */
public class Movie implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(posterPath);
        dest.writeString(title);
        dest.writeString(userRating);
        dest.writeString(releaseDate);
        dest.writeString(synopsis);
    }

    public Movie() {
        super();
    }


    public Movie(Parcel in) {
        id = in.readString();
        posterPath = in.readString();
        title = in.readString();
        userRating = in.readString();
        releaseDate = in.readString();
        synopsis = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
