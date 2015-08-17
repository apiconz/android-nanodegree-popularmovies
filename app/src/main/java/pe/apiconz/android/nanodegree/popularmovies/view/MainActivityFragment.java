package pe.apiconz.android.nanodegree.popularmovies.view;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import pe.apiconz.android.nanodegree.popularmovies.R;
import pe.apiconz.android.nanodegree.popularmovies.pojo.Movie;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private MovieAdapter movieAdapter;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
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

        GridView myGridView = (GridView) rootView.findViewById(R.id.myGridView);
        myGridView.setNumColumns(2);
        myGridView.setAdapter(movieAdapter);

        return rootView;
    }

    private void updateMovies(){

        MovieTask movieTask = new MovieTask(getActivity(),movieAdapter);
        movieTask.execute();

    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovies();
    }
}
