package pmvs.com.unique;

import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

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
