package pe.apiconz.android.nanodegree.popularmovies.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import pe.apiconz.android.nanodegree.popularmovies.R;

/**
 * Created by armando on 17/08/15.
 */
public class Utility {

    public static String getPreferredSortingCriteria(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_title_sort_by),
                context.getString(R.string.pref_default_sort_by));
    }
}
