package pmvs.com.unique;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Raphael on 07.06.2015.
 */

//Class for Shared Preferences. Not needed right now but maybe should store serverIds
public class SharedPreferencesManager {

    private Context context;

    public SharedPreferencesManager(Context context) {
        this.context = context;
    }


    public void saveKey(String key) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LocalUniqueID", key);
        editor.commit();
    }

    public String getKey() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String name = sharedPreferences.getString("LocalUniqueID", "none");
        return name;
    }

    public boolean KeyExists() {
        String serverID = getKey();
        if (serverID.equals("none")) {
            return false;
        } else return true;
    }

    public void removeKey(){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

}
