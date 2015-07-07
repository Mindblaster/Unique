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
import pmvs.com.unique.CreateEventFragment;
import pmvs.com.unique.R;
import pmvs.com.unique.View.RecyclerViewForUniques.RecyclerViewAdapterForAllUniques;
import pmvs.com.unique.database.DataBaseHelper;
import pmvs.com.unique.model.Event;
import pmvs.com.unique.model.Unique;

/**
 * Created by inot on 06.07.15.
 */
public class AllUniquesFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView mAllUniquesRecyclerView;

    private RecyclerViewAdapterForAllUniques mAllUniquesAdapter;

    private RecyclerView.LayoutManager mAllUniquesLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DataBaseHelper dataBaseHelper;

    private List<Unique> uniques = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.d("unique list created");

        View view = inflater.inflate(R.layout.alluniquesresview, container, false);


        // Initializing views.
        mAllUniquesRecyclerView = (RecyclerView) view.findViewById(R.id.uniquelistrecyclerview);

        // Initializing SwipeWrapper for the RecyclerView
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_by_all_uniques);
        swipeRefreshLayout.setOnRefreshListener(this);

        // If the size of views will not change as the data changes.
        mAllUniquesRecyclerView.setHasFixedSize(true);


        mAllUniquesLayoutManager = new LinearLayoutManager(getActivity());
        mAllUniquesRecyclerView.setLayoutManager(mAllUniquesLayoutManager);


        dataBaseHelper = new DataBaseHelper(getActivity());

        // Setting the adapter.
        mAllUniquesAdapter = new RecyclerViewAdapterForAllUniques(retrieveAllUniques());
        dataBaseHelper.closeDB();

        mAllUniquesRecyclerView.setAdapter(mAllUniquesAdapter);
        return view;
    }


    //for all uniques from DB
    public List<Unique> retrieveAllUniques() {
        return dataBaseHelper.getAllUniques();
    }


    //animating the refresh with swipe and updates the RecyclerView
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        mAllUniquesAdapter = new RecyclerViewAdapterForAllUniques(retrieveAllUniques());
        dataBaseHelper.closeDB();

        mAllUniquesRecyclerView.setAdapter(mAllUniquesAdapter);

        mAllUniquesAdapter.notifyDataSetChanged();
        // stopping swipe refresh
        swipeRefreshLayout.setRefreshing(false);
    }
}