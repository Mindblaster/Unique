package pmvs.com.unique;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseException;

import java.util.ArrayList;

import pmvs.com.unique.database.DataBaseHelper;
import pmvs.com.unique.model.Unique;

/**
 * Created by Raphael on 20.06.2015.
 */
public class UniqueLocationlistener implements LocationListener {
    private ParseManager parseManager;
    private String uniqueServerID;
    private Context context;
    private ArrayList<Unique> uniques;
    private long localEventID;
    DataBaseHelper dataBaseHelper;


    public UniqueLocationlistener(Context context,String uniqueServerID,long localEventID){
        this.context=context;
        this.parseManager=new ParseManager(context);
        this.uniqueServerID=uniqueServerID;
        this.localEventID=localEventID;
        dataBaseHelper= new DataBaseHelper(context);
    }
    @Override
    public void onLocationChanged(Location location) {

        //Updates Location
        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
        parseManager.updateLocation(uniqueServerID,latLng);

        //Downloads Uniques
        try {
            uniques = parseManager.getUniquesByRad(10000, latLng, uniqueServerID);
        }
        catch(ParseException pe){
            System.out.println("Donwloading Uniques failed");
        }

        for(int i=0;i<uniques.size();i++){
            //If unique is not in Database
            if(!dataBaseHelper.isUniqueInMyDB(uniqueServerID)) {
                if (!dataBaseHelper.isUniqueInDB(uniques.get(i).getServerID())) {
                    dataBaseHelper.createUniqueEntry(uniques.get(i), localEventID);
                }
            }
        }
        dataBaseHelper.closeDB();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
