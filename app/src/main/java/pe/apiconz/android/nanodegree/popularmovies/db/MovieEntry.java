package pe.apiconz.android.nanodegree.popularmovies.db;

import android.provider.BaseColumns;

/**
 * Created by armando on 28/08/15.
 */
public class MovieEntry implements BaseColumns {
    public static final String TABLE_NAME = "movie";
    public static final String COLUMN_MOVIE_KEY = "movie_id";
    public static final String COLUMN_POSTER_PATH = "poster_path";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_SYNOPSIS = "synopsis";
}
