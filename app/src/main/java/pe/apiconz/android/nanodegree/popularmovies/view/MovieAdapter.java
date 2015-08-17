package pe.apiconz.android.nanodegree.popularmovies.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

import pe.apiconz.android.nanodegree.popularmovies.R;
import pe.apiconz.android.nanodegree.popularmovies.pojo.Movie;

/**
 * Created by armando on 16/08/15.
 * http://developer.android.com/guide/topics/ui/layout/gridview.html
 */
public class MovieAdapter extends BaseAdapter {

    private static final String LOG_TAG = MovieAdapter.class.getCanonicalName();

    private Context context;
    private List movieList;

    public MovieAdapter(Context context, List movieList) {
        Log.d(LOG_TAG,"Ingreso al constructor 2");
        this.context = context;
        this.movieList = movieList;
    }

    public void setMovieList(List movieList){
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    private String getMoviePoster(String posterPath) {
        final String ImageUrlBase = "http://image.tmdb.org/t/p/w342/";
        return ImageUrlBase + posterPath;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(LOG_TAG,"entro a getView");
        Log.d(LOG_TAG,"Position:" + position);
        ImageView imageView;

        Movie movie = (Movie) movieList.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, null);
            imageView = (ImageView) convertView.findViewById(R.id.imageViewPoster);
        } else{
            imageView = (ImageView) convertView;
        }

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        convertView.setLayoutParams(new GridView.LayoutParams(params));

        Picasso.with(context).load(getMoviePoster(movie.getPosterPath())).error(R.drawable.dumb).into(imageView);

        return convertView;
    }

}
