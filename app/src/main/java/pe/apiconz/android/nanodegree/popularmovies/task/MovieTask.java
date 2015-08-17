package pe.apiconz.android.nanodegree.popularmovies.task;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import pe.apiconz.android.nanodegree.popularmovies.adapter.MovieAdapter;
import pe.apiconz.android.nanodegree.popularmovies.pojo.Movie;

/**
 * Created by armando on 16/08/15.
 */
public class MovieTask extends AsyncTask<String, Void, List<Movie>> {

    private static final String LOG_TAG = MovieTask.class.getCanonicalName();

    private static final String API_KEY = "c2cdb027bf4546d03077621c8d7b3aec";

    private final Context context;
    private MovieAdapter movieAdapter;

    public MovieTask(Context context, MovieAdapter movieAdapter) {
        this.context = context;
        this.movieAdapter = movieAdapter;
    }

    protected List<Movie> doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String moviesJson = null;

        try {


            final String MOVIES_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
            final String SORT_PARAM = "sort_by";
            final String API_KEY_PARAM = "api_key";

            Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                    .appendQueryParameter(SORT_PARAM, params[0])
                    .appendQueryParameter(API_KEY_PARAM, API_KEY).build();

            Log.d(LOG_TAG,builtUri.toString());

            URL url = new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null){
                moviesJson = null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null){
                buffer.append(line + "\n");
            }
            moviesJson = buffer.toString();

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Ocurrió un error con la URL", e);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Ocurrió un error durante la lectura de la trama", e);
        } finally {
            if (urlConnection!=null){
                urlConnection.disconnect();
            }
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG,"Ocurrió un error durante el intento de cierre del reader",e);
                }
            }
        }

        Log.d(LOG_TAG, moviesJson);

        try {
            return getMovieDataFromJson(moviesJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        Log.d(LOG_TAG,"ingreso a onPostExecute");
        if(movies != null){
            movieAdapter.setMovieList(movies);
        }

    }

    private List<Movie> getMovieDataFromJson(String jsonString) throws JSONException {

        JSONObject moviesJson = new JSONObject(jsonString);
        JSONArray moviesArray = moviesJson.getJSONArray("results");
        List<Movie> results = new ArrayList<>();

        Movie movie = null;
        for (int i = 0; i < moviesArray.length(); i++){
            JSONObject movieJson = moviesArray.getJSONObject(i);
            movie = new Movie();
            movie.setId(movieJson.getString("id"));
            movie.setPosterPath(movieJson.getString("poster_path"));
            movie.setTitle(movieJson.getString("original_title"));
            movie.setSynopsis(movieJson.getString("overview"));
            movie.setUserRating(movieJson.getString("vote_average"));
            movie.setReleaseDate(movieJson.getString("release_date"));

            results.add(movie);
        }

        return results;
    }
}
