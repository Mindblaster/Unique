package pmvs.com.unique;

import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.parse.Parse;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import pmvs.com.unique.model.Unique;

/**
 * Created by inot on 25.05.15.
 */

public class MainActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {
        Parse.initialize(this, "SjQPgPKS1km7nX6jEhTMJ8rRefDKzuS1gK7VQYeK", "twMXyl6pKRL3AyIrHz7vigUNDkp00rEe0ofpv95X");
        this.setDrawerHeaderImage(R.drawable.appnavbarpic);

        this.addSection(this.newSection("UniqueBook", new MasterFragment()));
        this.addSection(this.newSection("MyUniques", new MasterFragment()));
        this.addSection(this.newSection("Eventlist", new EventListFragment()));


        //testUnique
        Unique unique = new Unique("MyUnique",123,"personal","Please meet me!","08912345678","maxmustermann@test.de","max","mmuster",false);

        unique.setPosition(new LatLng(48.163327, 11.565246));
        // Parse initialisation with keys
        //Parse.enableLocalDatastore(this);

        ParseManager parseManager =new ParseManager();
        parseManager.uploadUnique(unique);



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
