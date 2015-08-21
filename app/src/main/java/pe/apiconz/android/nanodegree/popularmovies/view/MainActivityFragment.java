package pe.apiconz.android.nanodegree.popularmovies.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import pe.apiconz.android.nanodegree.popularmovies.R;
import pe.apiconz.android.nanodegree.popularmovies.adapter.MovieAdapter;
import pe.apiconz.android.nanodegree.popularmovies.pojo.Movie;
import pe.apiconz.android.nanodegree.popularmovies.task.MovieTask;
import pe.apiconz.android.nanodegree.popularmovies.util.MovieInterface;
import pe.apiconz.android.nanodegree.popularmovies.util.Utility;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MovieInterface {

    private static final String LOG_TAG = MainActivityFragment.class.getCanonicalName();
    public static final String MOVIE_LIST_KEY = "movieList";

    private MovieAdapter movieAdapter;
    private GridView myGridView;
    private List<Movie> movieList;

    public MainActivityFragment() {
        movieList = new ArrayList<>();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "onSaveInstanceState Tamaño de lista:" + movieList.size());
        outState.putParcelableArrayList(MOVIE_LIST_KEY, (ArrayList<? extends Parcelable>) movieList);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            Log.d(LOG_TAG, "onViewStateRestored Tamaño de lista:" + movieList.size());
            movieList = (List<Movie>) savedInstanceState.get(MOVIE_LIST_KEY);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Log.d(LOG_TAG, "onCreate");

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_refresh){
            //updateMovies();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        if (savedInstanceState != null) {
            Log.d(LOG_TAG, "onCreateView");
            movieList = (List<Movie>) savedInstanceState.get(MOVIE_LIST_KEY);
            Log.d(LOG_TAG, "tamaño de lista:" + movieList.size());
        }

        movieAdapter = new MovieAdapter(getActivity(), movieList);
        myGridView = (GridView) rootView.findViewById(R.id.myGridView);

        if (movieList == null || movieList.size() == 0) {
            if (savedInstanceState == null) {
                savedInstanceState = new Bundle();
            }
            updateMovies(savedInstanceState);
        }

        myGridView.setAdapter(movieAdapter);
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) myGridView.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("movieId", movie.getId());
                intent.putExtra("moviePosterPath", movie.getPosterPath());
                intent.putExtra("movieTitle", movie.getTitle());
                intent.putExtra("movieReleaseDate", movie.getReleaseDate());
                intent.putExtra("movieSynopsis", movie.getSynopsis());
                intent.putExtra("movieUserRating", movie.getUserRating());
                startActivity(intent);

                Log.i(LOG_TAG, "" + movie.toString());
            }
        });

        return rootView;
    }

    private void updateMovies(Bundle savedInstanceState) {
        if (Utility.isConnectionActive(getActivity())) {
            MovieTask movieTask = new MovieTask(getActivity(), movieAdapter, savedInstanceState, this);
            String sortBy = Utility.getPreferredSortingCriteria(getActivity());
            movieTask.execute(sortBy);
            super.onSaveInstanceState(savedInstanceState);
        }
    }

    private void updateMovies(){
        if (Utility.isConnectionActive(getActivity())) {
            MovieTask movieTask = new MovieTask(getActivity(), movieAdapter, this);
            String sortBy = Utility.getPreferredSortingCriteria(getActivity());
            movieTask.execute(sortBy);
        }

    }


    @Override
    public void setMovieData(List<Movie> movieData) {
        movieList = movieData;
    }
}
