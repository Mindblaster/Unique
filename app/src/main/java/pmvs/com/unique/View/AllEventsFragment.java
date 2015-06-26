package pmvs.com.unique.View;

import android.graphics.Outline;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import pmvs.com.unique.ChildFragment;
import pmvs.com.unique.R;
import pmvs.com.unique.View.RecyclerViewForEvents.RecyclerAdapterForFutureEvents;
import pmvs.com.unique.View.RecyclerViewForEvents.RecyclerAdapterForPastEvents;
import pmvs.com.unique.database.DataBaseHelper;
import pmvs.com.unique.model.Event;
import pmvs.com.unique.model.Unique;

/**
 * Created by inot on 15.06.15.
 */
public class AllEventsFragment extends android.support.v4.app.Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mFutureRecyclerView;
    private RecyclerView mPastRecyclerView;
    private RecyclerAdapterForFutureEvents mFutureAdapter;
    private RecyclerAdapterForPastEvents mPastAdapter;
    private RecyclerView.LayoutManager mFutureLayoutManager;
    private RecyclerView.LayoutManager mPastLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DataBaseHelper dataBaseHelper;

    private List<Event> mData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.d("MaterialNavigationDrawer Master-Child", "Master created");
        //initialize Buttons
        View view = inflater.inflate(R.layout.alleventsrecview, container, false);
        //((Button) view.findViewById(R.id.addItem2)).setOnClickListener(this);


        // Initializing views.
        mFutureRecyclerView = (RecyclerView) view.findViewById(R.id.futurerecycler);
        mPastRecyclerView = (RecyclerView) view.findViewById(R.id.pastrecycler);

        // Initializing SwipeWrapper for the RecyclerView
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        // If the size of views will not change as the data changes.
        mFutureRecyclerView.setHasFixedSize(true);
        mPastRecyclerView.setHasFixedSize(true);

        // Setting the LayoutManager.
        mFutureLayoutManager = new LinearLayoutManager(getActivity());
        mFutureRecyclerView.setLayoutManager(mFutureLayoutManager);

        //IMPORTANT !Does it work?

        mPastLayoutManager = new LinearLayoutManager(getActivity());
        mPastRecyclerView.setLayoutManager(mPastLayoutManager);


        dataBaseHelper = new DataBaseHelper(getActivity());
    ///    try {
     //       createList(6);
       ///    } catch (ParseException e) {
            // ignore this
       // }

        // Setting the adapter.
        mFutureAdapter = new RecyclerAdapterForFutureEvents(retrieveEvents());
        mPastAdapter = new RecyclerAdapterForPastEvents(retrieveEvents());
        dataBaseHelper.closeDB();

        mFutureRecyclerView.setAdapter(mFutureAdapter);
        mPastRecyclerView.setAdapter(mPastAdapter);
        //DEIN BUTTON: Listener auf Action Button um items zur Liste zu adden
        View addButton = (View) view.findViewById(R.id.addItem2);
        addButton.setOnClickListener(this);

        //Wichtig, da source Image viereckig ist und wir einen Runden Button wollen
        addButton.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int diameter = getResources().getDimensionPixelSize(R.dimen.add_button_radius);
                outline.setOval(0, 0, diameter, diameter);
            }
        });

        return view;
    }

    //OnclickListener for all Buttons
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //new child
            case R.id.addItem2:
                ((MaterialNavigationDrawer) this.getActivity()).setFragmentChild(new ChildFragment(), "one subwindow is open");

                break;
        }
    }

    public List<Event> createList(int size) throws ParseException {
        List<Event> result = new ArrayList<>();
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "22-01-2015 10:20:56";
        String dateInString2 = "22-01-2015 10:49:40";
        Date dateFrom = null, dateTill = null;
        try {
            dateFrom = simpleDate.parse(dateInString);
            dateTill = simpleDate.parse(dateInString2);
        } catch (Exception e) {
            // ignore this
        }

        for (int i = 1; i <= size; i++) {
            // int initId, String initTitle, Date initFrom, Date initTill, String initAddress, int initMyUniqueID, boolean initUniqueShared, List<Unique> initReceivedUniques, String initEventPic) {
            List<Unique> listUnique = new ArrayList<>();
            Event exampleEvent = new Event(
                    0, "ExpoHannover" + i, dateFrom, dateTill, "Strandstr 11,  Nichtmünchen", 0, false, listUnique, "kkk.jpg");
            result.add(exampleEvent);
            dataBaseHelper.createEventEntry(exampleEvent);
            Log.e("event count", "event count: " + dataBaseHelper.getEventCount());
        }

        return result;
    }

    public List<Event> retrieveEvents() {
        return dataBaseHelper.getAllEvents();
    }


    //animating the refresh with swipe and updates the RecyclerView
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        mFutureAdapter.notifyDataSetChanged();
        // stopping swipe refresh
        swipeRefreshLayout.setRefreshing(false);
    }
}