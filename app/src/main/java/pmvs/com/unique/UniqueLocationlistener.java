package pmvs.com.unique;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import pmvs.com.unique.model.Unique;

/**
 * Created by Raphael on 20.06.2015.
 */
public class UniqueLocationlistener implements LocationListener {
    private ParseManager parseManager;
    private String uniqueServerID;
    private Context context;


    public UniqueLocationlistener(Context context,String uniqueServerID){
        this.context=context;
        this.parseManager=new ParseManager(context);
        this.uniqueServerID=uniqueServerID;
    }
    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
        parseManager.updateLocation(uniqueServerID,latLng);
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
