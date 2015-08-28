package pe.apiconz.android.nanodegree.popularmovies.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by armando on 28/08/15.
 */
public class MovieDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "movies.db";

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieEntry.TABLE_NAME + " (" +
                MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MovieEntry.COLUMN_MOVIE_KEY + " TEXT NOT NULL," +
                MovieEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL," +
                MovieEntry.COLUMN_TITLE + " TEXT NOT NULL," +
                MovieEntry.COLUMN_RATING + " TEXT NOT NULL," +
                MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL," +
                MovieEntry.COLUMN_SYNOPSIS + " TEXT NOT NULL, " +
                " UNIQUE (" + MovieEntry.COLUMN_MOVIE_KEY + ") ON CONFLICT REPLACE);";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
