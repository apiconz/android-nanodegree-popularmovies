package pe.apiconz.android.nanodegree.popularmovies.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pe.apiconz.android.nanodegree.popularmovies.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailActivityFragment extends Fragment {

    public MovieDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        Intent intent = getActivity().getIntent();

        TextView txtMovieTitle = (TextView) rootView.findViewById(R.id.movie_title);
        txtMovieTitle.setText(intent.getStringExtra("movieTitle"));
        ImageView imgMovieImage = (ImageView) rootView.findViewById(R.id.movie_image);
        Picasso.with(getActivity())
                .load("http://image.tmdb.org/t/p/w342/" + intent.getStringExtra("moviePosterPath"))
                .placeholder(R.drawable.dumb)
                .into(imgMovieImage);
        TextView txtMovieYear = (TextView) rootView.findViewById(R.id.movie_year);
        txtMovieYear.setText(intent.getStringExtra("movieReleaseDate"));
        //TextView txtMovieDuration = (TextView) rootView.findViewById(R.id.movie_duration);
        TextView txtMovieRate = (TextView) rootView.findViewById(R.id.movie_rate);
        txtMovieRate.setText(intent.getStringExtra("movieUserRating"));
        TextView txtMovieSynopsis = (TextView) rootView.findViewById(R.id.movie_synopsis);
        txtMovieSynopsis.setText(intent.getStringExtra("movieSynopsis"));


        return rootView;
    }
}
