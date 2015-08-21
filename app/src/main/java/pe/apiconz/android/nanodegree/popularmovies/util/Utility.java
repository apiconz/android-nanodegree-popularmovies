package pe.apiconz.android.nanodegree.popularmovies.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

    /**
     * Reference:
     * http://stackoverflow.com/questions/4238921/detect-whether-there-is-an-internet-connection-available-on-android
     */
    public static boolean isConnectionActive(Context context) {
        boolean wifiConnection = false;
        boolean mobileConnection = false;

        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networksInfo = connManager.getAllNetworkInfo();
        for (NetworkInfo networkInfo : networksInfo) {
            if (networkInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (networkInfo.isConnected())
                    wifiConnection = true;
            if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (networkInfo.isConnected())
                    mobileConnection = true;
        }
        return wifiConnection || mobileConnection;
    }
}
