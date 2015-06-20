package pmvs.com.unique;

import android.content.Context;
import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import pmvs.com.unique.model.Unique;

/**
 * Created by Raphael on 31.05.2015.
 */
public class ParseManager {
    private Context context;
    private String LocationID;



    public ParseManager(Context context) {
        this.context = context;
    }

    //Uploads Unique and gets its ID from the Database and returns it as a String.
    public String uploadUnique(final Unique unique)throws ParseException{
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
        uniqueUpload.save();
        return uniqueUpload.getObjectId();
    }

    //Gets Uniques in Proximity to current location defined by radius (in Meters)
    public ArrayList<Unique> getUniquesByRad(int radius,LatLng myPosition,String myUniqueServerID)throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Uniques");
        ParseGeoPoint point = new ParseGeoPoint(myPosition.latitude, myPosition.longitude);
        Double meterToKilometer = radius / 1000.0;
        query.whereWithinKilometers("Location", point, meterToKilometer);
        query.whereNotEqualTo("objectID",myUniqueServerID); //needed if we want to filter own unique there
        List<ParseObject> uniquePositions = query.find();
        ArrayList<Unique> uniques = new ArrayList();
        Unique defaultUnique;
        for (int i = 0; i < uniquePositions.size(); i++) {
            defaultUnique = new Unique(uniquePositions.get(i).getString("Name"), 0, uniquePositions.get(i).getString("Tag"), uniquePositions.get(i).getString("Text"), uniquePositions.get(i).getString("PhoneNumber"), uniquePositions.get(i).getString("EMail"), uniquePositions.get(i).getString("Facebook"), uniquePositions.get(i).getString("Twitter"), false);
            uniques.add(i, defaultUnique);
        }
        return uniques;
    }
    //gets Unique from server by ID (This Method is not asynchronous
    private ParseObject getUniqueByServerID(String ServerID)throws ParseException{
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Uniques");
        return query.get(ServerID);
    }

    //Deletes Unique from Server
    public void deleteUnique(String ServerID){
        try {
            ParseObject po = getUniqueByServerID(ServerID);
            po.deleteInBackground();
        }catch(ParseException pe){}
    }

//ASYNCHRONOUS Methods

    // Updates Location of Unique.
    public void updateLocation(String ServerID, final LatLng myPosition){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Uniques");
        query.getInBackground(ServerID, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                ParseGeoPoint point = new ParseGeoPoint(myPosition.latitude, myPosition.longitude);
                parseObject.put("Location",point);
                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        System.out.println("updated position");
                    }
                });
            }
        });
    }

}
