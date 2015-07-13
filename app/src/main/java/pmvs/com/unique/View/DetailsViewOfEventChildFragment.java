package pmvs.com.unique.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pmvs.com.unique.R;
import pmvs.com.unique.View.RecyclerViewForUniques.RecyclerViewAdapterForAllUniques;
import pmvs.com.unique.View.RecyclerViewForUniques.RecyclerViewForUniquesBelowEvent.RecyclerViewAdapterForUniquesOfEvent;
import pmvs.com.unique.database.DataBaseHelper;
import pmvs.com.unique.model.Event;
import pmvs.com.unique.model.Unique;

import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by inot on 26.05.15.
 * Fragment to show details of an event
 */


public class DetailsViewOfEventChildFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private TextView titel;
    private TextView adress;
    private TextView dateOfEvent;
    private TextView sharedUnique;
    private TextView nameOfUnique;
    private TextView textOfUnique;
    private DataBaseHelper dataBaseHelper;
    private Event event;
    private Date fromDateOfEvent;
    private Date tillDateOfEvent;

    private RecyclerView uniquesForEventRecView;

    private RecyclerViewAdapterForUniquesOfEvent mAllUniquesAdapter;

    private RecyclerView.LayoutManager mAllUniquesLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Unique> uniques = new ArrayList<>();
    private Unique myUnique;
    private long idOfEvent;

    /**
     * Setting the view elements
     */
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //  Log.d("MaterialNavigationDrawer Master-Child", "Child created");
        Bundle extras = this.getArguments();
        int tmp = extras.getInt(Intent.EXTRA_TEXT);
        idOfEvent= Long.valueOf(tmp).longValue();
        Integer idOfEventAsInteger=(Integer)tmp;
        String idOfEventInString=idOfEventAsInteger.toString();
        View detailsView= inflater.inflate(R.layout.event_detailed_view, container, false);
        titel = (TextView)detailsView.findViewById(R.id.event_title_eventdetails);
        adress = (TextView)detailsView.findViewById(R.id.address_of_detailedevent);
        dateOfEvent = (TextView)detailsView.findViewById(R.id.date_of_detailedevent);
        sharedUnique = (TextView)detailsView.findViewById(R.id.sharedUnique);
        nameOfUnique = (TextView)detailsView.findViewById(R.id.name_of_unique);
        textOfUnique = (TextView)detailsView.findViewById(R.id.descripton_of_unique);

        dataBaseHelper = new DataBaseHelper(getActivity());
        event= dataBaseHelper.getEvent(idOfEvent);
        myUnique=dataBaseHelper.getMYUniqueOfEvent(idOfEvent);
        // System.out.println(myUnique.getName());
        dataBaseHelper.closeDB();

        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        fromDateOfEvent = event.getFromDate();
        String eventFromDate = sdf.format(fromDateOfEvent);

        SimpleDateFormat secondsdf = new SimpleDateFormat("HH:mm");
        tillDateOfEvent = event.getTillDate();
        String eventTillHM = secondsdf.format(tillDateOfEvent);
        String wholeDateOfEvent=eventFromDate +" - " +eventTillHM;

        titel.setText(event.getTitle());
        adress.setText(event.getAddress());
        sharedUnique.setText("shared unique");
        nameOfUnique.setText(myUnique.getName());
        textOfUnique.setText(myUnique.getText());

        dateOfEvent.setText(wholeDateOfEvent);

        //Log.d("unique list created");

        // Initializing views.
        uniquesForEventRecView = (RecyclerView) detailsView.findViewById(R.id.uniquelistrecyclerview);

        // Initializing SwipeWrapper for the RecyclerView
        swipeRefreshLayout = (SwipeRefreshLayout) detailsView.findViewById(R.id.swipe_refresh_layout_by_all_uniques);
        swipeRefreshLayout.setOnRefreshListener(this);

        // If the size of views will not change as the data changes.
        uniquesForEventRecView.setHasFixedSize(true);


        mAllUniquesLayoutManager = new LinearLayoutManager(getActivity());
        uniquesForEventRecView.setLayoutManager(mAllUniquesLayoutManager);


        dataBaseHelper = new DataBaseHelper(getActivity());

        // Setting the adapter.
        mAllUniquesAdapter = new RecyclerViewAdapterForUniquesOfEvent(retrieveAllUniques());
        dataBaseHelper.closeDB();

        uniquesForEventRecView.setAdapter(mAllUniquesAdapter);
        return detailsView;
    }


    //for all uniques from DB
    public List<Unique> retrieveAllUniques() {
        return dataBaseHelper.getAllUniquesOfEvent(idOfEvent);
    }


    //animating the refresh with swipe and updates the RecyclerView
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        mAllUniquesAdapter = new RecyclerViewAdapterForUniquesOfEvent(retrieveAllUniques());
        dataBaseHelper.closeDB();

        uniquesForEventRecView.setAdapter(mAllUniquesAdapter);

        mAllUniquesAdapter.notifyDataSetChanged();
        // stopping swipe refresh
        swipeRefreshLayout.setRefreshing(false);


    }


}
