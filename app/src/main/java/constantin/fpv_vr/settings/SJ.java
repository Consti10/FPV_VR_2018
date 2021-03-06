package constantin.fpv_vr.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import constantin.fpv_vr.R;

import static android.content.Context.MODE_PRIVATE;
import static constantin.fpv_vr.connect.AConnect.CONNECTION_TYPE_TestFile;

@SuppressWarnings("WeakerAccess")

/* *
 * SJ is only a wrapper around the android shared preferences that has functions for most commonly used values
 */

public class SJ {

    //********************************** pref_connect only **********************************
    public static int getConnectionType(final Context context){
        final SharedPreferences pref_connect=context.getSharedPreferences("pref_connect", MODE_PRIVATE);
        return pref_connect.getInt(context.getString(R.string.CONNECTION_TYPE),CONNECTION_TYPE_TestFile);
    }
    @SuppressLint("ApplySharedPref")
    public static void setConnectionType(final Context context, final int value){
        final SharedPreferences pref_connect=context.getSharedPreferences("pref_connect", MODE_PRIVATE);
        pref_connect.edit().putInt(context.getString(R.string.CONNECTION_TYPE),value).commit();
    }
    //********************************** pref_connect only **********************************


    //******************************** pref_vr **************************************

    public static boolean EnableAHT(final Context context){
        final SharedPreferences pref_vr= context.getSharedPreferences("pref_vr_rendering",MODE_PRIVATE);
        return (pref_vr.getInt(context.getString(R.string.AirHeadTrackingMode),0)!=0);
    }

    public static int AHTRefreshRateMs(final Context context){
        final SharedPreferences pref_vr = context.getSharedPreferences("pref_vr_rendering",MODE_PRIVATE);
        return pref_vr.getInt(context.getString(R.string.AHTRefreshRateMs),100);
    }

    public static int AHTPort(final Context context) {
        final SharedPreferences pref_connect = context.getSharedPreferences("pref_connect", MODE_PRIVATE);
        return pref_connect.getInt(context.getString(R.string.AHTPort),5200);
    }
}
