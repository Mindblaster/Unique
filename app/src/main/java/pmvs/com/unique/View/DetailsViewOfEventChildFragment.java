package pmvs.com.unique.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pmvs.com.unique.R;
import pmvs.com.unique.database.DataBaseHelper;
import pmvs.com.unique.model.Event;
import pmvs.com.unique.model.Unique;

import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by inot on 26.05.15.
 */


public class DetailsViewOfEventChildFragment extends android.support.v4.app.Fragment {

    private TextView titel;
    private TextView adress;
    private TextView dateOfEvent;
    private TextView titelOfUnique;
    private DataBaseHelper dataBaseHelper;
    private Event event;
    private Date fromDateOfEvent;
    private Date tillDateOfEvent;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //  Log.d("MaterialNavigationDrawer Master-Child", "Child created");
        Bundle extras = this.getArguments();
        int tmp = extras.getInt(Intent.EXTRA_TEXT);
        long idOfEvent= Long.valueOf(tmp).longValue();
      //  Log.d("TESTTESTEST!!!!! ", tmp);
        View detailsView= inflater.inflate(R.layout.event_detailed_view, container, false);
        titel = (TextView)detailsView.findViewById(R.id.event_title_eventdetails);
        adress = (TextView)detailsView.findViewById(R.id.address_of_detailedevent);
        dateOfEvent = (TextView)detailsView.findViewById(R.id.date_of_detailedevent);
        titelOfUnique = (TextView)detailsView.findViewById(R.id.left_text_button2);
        dataBaseHelper = new DataBaseHelper(getActivity());
        Unique fortestDB =new Unique();
        fortestDB.setName("TestName");
        fortestDB.seteMail("test@mail.de");
        fortestDB.setTag("1");
        fortestDB.setText("langer Textblablablabla");
        fortestDB.setFacebookName("testfbname");
        fortestDB.setPhoneNumber("000000222222");
        fortestDB.setServerID("123");
        fortestDB.setTwitterName("twitttestname");
        LatLng bla=new LatLng(Double.parseDouble("12.34"),Double.parseDouble("44.44"));
        fortestDB.setPosition((bla));
        dataBaseHelper.createUniqueEntry(fortestDB, idOfEvent);

        System.out.println(dataBaseHelper.isUniqueInDB("123"));



        event= dataBaseHelper.getEvent(idOfEvent);
        dataBaseHelper.closeDB();

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy  hh:mm");
        fromDateOfEvent = event.getFromDate();
        String eventFromDate = sdf.format(fromDateOfEvent);

        SimpleDateFormat secondsdf = new SimpleDateFormat("hh:mm");
        tillDateOfEvent = event.getTillDate();
        String eventTillHM = secondsdf.format(tillDateOfEvent);
        String wholeDateOfEvent=eventFromDate +" - " +eventTillHM;

        titel.setText(event.getTitle());
        adress.setText(event.getAddress());
        titelOfUnique.setText("shared unique");
        dateOfEvent.setText(wholeDateOfEvent);
        return detailsView;



    }


}
