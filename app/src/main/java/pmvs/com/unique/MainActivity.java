package pmvs.com.unique;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;
import com.parse.Parse;
import com.parse.ParseException;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import pmvs.com.unique.View.AllEventsFragment;
import pmvs.com.unique.View.AllUniquesFragment;
import pmvs.com.unique.database.DataBaseHelper;
import pmvs.com.unique.model.*;
import pmvs.com.unique.model.Event;

/**
 * Created by inot on 25.05.15.
 */

public class MainActivity extends MaterialNavigationDrawer implements CreateEventFragment.CreateFragmentImageInterface {
    private final int SELECT_PHOTO = 1;
    private ImageView imageView;

    @Override
    public void init(Bundle savedInstanceState) {
        this.setDrawerHeaderImage(R.drawable.appnavbarpic);


//        this.addSection(this.newSection("MyUniques", new MasterFragment()));
          this.addSection(this.newSection("Events", R.drawable.eventnavicon, new AllEventsFragment()));
          this.addSection(this.newSection("UniqueBook", R.drawable.uniquebookicon, new AllUniquesFragment()));
          this.addSection(this.newSection("My Uniques", R.drawable.uniquecardsicon, new EventListFragment()));
          this.addBottomSection(newSection("Bottom Section",R.drawable.ic_settings_black_24dp, new MasterFragment()));




        Parse.initialize(this, "SjQPgPKS1km7nX6jEhTMJ8rRefDKzuS1gK7VQYeK", "twMXyl6pKRL3AyIrHz7vigUNDkp00rEe0ofpv95X");
            //Start Service
            Intent intent = new Intent(MainActivity.this, UniqueService.class);
            intent.putExtra("FLAG", 2);
            startService(intent);

        //Check if internet Connection is available
        if(isConnectingToInternet()) {


        }
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

    public boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }


    @Override
    public void setImage(ImageView imageView) {
        this.imageView=imageView;
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }
}