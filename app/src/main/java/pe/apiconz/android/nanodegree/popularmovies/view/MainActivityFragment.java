package pe.apiconz.android.nanodegree.popularmovies.view;

import android.content.Intent;
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

import pe.apiconz.android.nanodegree.popularmovies.R;
import pe.apiconz.android.nanodegree.popularmovies.adapter.MovieAdapter;
import pe.apiconz.android.nanodegree.popularmovies.pojo.Movie;
import pe.apiconz.android.nanodegree.popularmovies.task.MovieTask;
import pe.apiconz.android.nanodegree.popularmovies.util.Utility;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String LOG_TAG = MainActivityFragment.class.getCanonicalName();

    private MovieAdapter movieAdapter;
    private GridView myGridView;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_refresh){
            updateMovies();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        movieAdapter = new MovieAdapter(getActivity(), new ArrayList<>());
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        myGridView = (GridView) rootView.findViewById(R.id.myGridView);
        myGridView.setNumColumns(2);
        myGridView.setAdapter(movieAdapter);
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie)myGridView.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("movieId", movie.getId());
                intent.putExtra("moviePosterPath", movie.getPosterPath());
                intent.putExtra("movieTitle", movie.getTitle());
                intent.putExtra("movieReleaseDate", movie.getReleaseDate());
                intent.putExtra("movieSynopsis", movie.getSynopsis());
                intent.putExtra("movieUserRating", movie.getUserRating());
                startActivity(intent);

                Log.i(LOG_TAG, "" + movie.toString() );
            }
        });

        return rootView;
    }

    private void updateMovies(){

        MovieTask movieTask = new MovieTask(getActivity(),movieAdapter);
        String sortBy = Utility.getPreferredSortingCriteria(getActivity());
        movieTask.execute(sortBy);

    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovies();
    }
}
