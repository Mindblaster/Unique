package pmvs.com.unique;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.parse.Parse;
import com.parse.ParseException;

import java.util.ArrayList;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import pmvs.com.unique.model.Unique;

/**
 * Created by inot on 25.05.15.
 */

public class MainActivity extends MaterialNavigationDrawer {


    @Override
    public void init(Bundle savedInstanceState) {
        this.setDrawerHeaderImage(R.drawable.appnavbarpic);

        this.addSection(this.newSection("UniqueBook", new MasterFragment()));
        this.addSection(this.newSection("MyUniques", new MasterFragment()));
        this.addSection(this.newSection("Eventlist", new EventListFragment()));

        //Intialization of Parse with API Key
        Parse.initialize(this, "SjQPgPKS1km7nX6jEhTMJ8rRefDKzuS1gK7VQYeK", "twMXyl6pKRL3AyIrHz7vigUNDkp00rEe0ofpv95X");

        //Start Service
        Intent intent = new Intent(MainActivity.this, UniqueService.class);
        intent.putExtra("FLAG",2);
        startService(intent);

        //testUnique
        Unique unique = new Unique("MyUnique", 123, "personal", "Please meet me!", "08912345678", "maxmustermann@test.de", "max", "mmuster", false);
        unique.setPosition(new LatLng(48.163327, 11.565246));
        ParseManager parseManager = new ParseManager(getApplicationContext());

        try {
            unique.setServerID(parseManager.uploadUnique(unique));
        } catch (ParseException pe) {
            Log.e("Failure", "Error Failed to Upload Unique");
        }

        /*
        //parseManager.updateLocation(unique.getServerID(),new LatLng(48.163327, 11.00000));
        try {
            ArrayList<Unique> uniques = parseManager.getUniquesByRad(5000, new LatLng(48.163327, 11.565245), unique.getServerID());
            //System.out.println("how many : " + uniques.size());
        } catch (ParseException pe) {

        }        */
        // Test Event
        Intent newIntent = new Intent(MainActivity.this, UniqueService.class);
        newIntent.putExtra("FLAG",0);
        newIntent.putExtra("from","20-06-2015 16:25:00");
        newIntent.putExtra("till","20-06-2015 16:27:00");
        newIntent.putExtra("Unique_ServerID",unique.getServerID());
        startService(newIntent);
    }


    //dont change onStart. Just use init for all you want to have in onStart
    @Override
    protected void onStart() {
        super.onStart();
        // set the indicator for child fragments
        // N.B. call this method AFTER the init() to leave the time to instantiate the ActionBarDrawerToggle
        this.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
    }




    //not used yet
    @Override
    public void onHomeAsUpSelected() {
        // when the back arrow is selected this method is called
        //do nothing
    }
}