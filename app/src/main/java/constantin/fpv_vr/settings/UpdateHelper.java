package constantin.fpv_vr.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import constantin.fpv_vr.R;
import constantin.fpv_vr.connect.AConnect;
import constantin.telemetry.core.TelemetrySettings;
import constantin.video.core.player.VideoSettings;
import constantin.renderingx.core.vrsettings.ASettingsVR;

import static android.content.Context.MODE_PRIVATE;

public class UpdateHelper {

    private static final String FIRST_START_30 ="FIRST_START_31";

    //set default values for all preference files of the fpv-vr library/module
    private static void setAllDefaultValues(final Context c, final boolean readAgain){
        PreferenceManager.setDefaultValues(c,"pref_connect",MODE_PRIVATE,R.xml.pref_connect,readAgain);
        PreferenceManager.setDefaultValues(c,"pref_osd",MODE_PRIVATE,R.xml.pref_osd_elements,readAgain);
        PreferenceManager.setDefaultValues(c,"pref_osd",MODE_PRIVATE,R.xml.pref_osd_style,readAgain);
        TelemetrySettings.initializePreferences(c,readAgain);
        VideoSettings.initializePreferences(c,readAgain);
        ASettingsVR.initializePreferences(c,readAgain);
        AConnect.setPreferencesForConnectionType(c,c.getSharedPreferences("pref_connect",MODE_PRIVATE).getInt(c.getString(R.string.CONNECTION_TYPE),2));
    }

    @SuppressLint("ApplySharedPref")
    private static void clearPreviousPreferences(final Context c){
        final String[] preferenceNames=new String[]{
                "pref_connect","pref_osd",
                "pref_telemetry","pref_video",
                "pref_vr_rendering",
        };
        for(final String s:preferenceNames){
            c.getSharedPreferences(s,MODE_PRIVATE).edit().clear().commit();
        }
        PreferenceManager.getDefaultSharedPreferences(c).edit().clear().commit();
    }

    @SuppressLint("ApplySharedPref")
    public static void checkForFreshInstallOrUpdate(final Context c){
        final SharedPreferences pref_default= PreferenceManager.getDefaultSharedPreferences(c);
        if(pref_default.getBoolean(FIRST_START_30,true)){
            clearPreviousPreferences(c);
            setAllDefaultValues(c,true);
            pref_default.edit().putBoolean(FIRST_START_30,false).commit();
        }
    }

}
