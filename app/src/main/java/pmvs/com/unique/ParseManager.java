package pmvs.com.unique;

import android.content.Context;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.ArrayList;
import com.google.android.gms.maps.model.LatLng;
import pmvs.com.unique.model.Unique;

/**
 * Created by Raphael on 31.05.2015.
 */
public class ParseManager {
    private Context context;
    private String LocationID;

/*
    public ParseManager(Context context) {
        this.context = context;
    }
*/
    //Uploads Unique and gets its ID from the Database and returns it as a String.
    public void uploadUnique(final Unique unique){
        final ParseObject uniqueUpload = new ParseObject("Uniques");
        uniqueUpload.put("Name", unique.getName());
        uniqueUpload.put("Text", unique.getText());
        uniqueUpload.put("Tag", unique.getTag());
        uniqueUpload.put("EMail", unique.geteMail());
        uniqueUpload.put("PhoneNumber", unique.getPhoneNumber());
        uniqueUpload.put("Twitter", unique.getTwitterName());
        uniqueUpload.put("Facebook",unique.getFacebookName());
        LatLng newPos = unique.getPosition();
        ParseGeoPoint point = new ParseGeoPoint(newPos.latitude, newPos.longitude);
        uniqueUpload.put("Location",point);
        uniqueUpload.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                unique.setServerID(uniqueUpload.getObjectId());
            }
        });
    }

    //Deletes Unique from Server
    public void deleteUnique(Unique unique, int id){

    }

    // Updates Location of Unique.
    public void updateLocation(Unique unique, int id){

    }

    //Gets Uniques in Proximity to current location defined by SystemVar RAD (in Meters)
    public ArrayList<Unique> getUniquesByRad(int position){
            return new ArrayList<Unique>();
    }

}
