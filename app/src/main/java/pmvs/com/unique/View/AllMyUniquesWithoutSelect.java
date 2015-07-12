package pmvs.com.unique.View;

import android.graphics.Outline;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import pmvs.com.unique.R;
import pmvs.com.unique.View.RecyclerViewForUniques.ResourceViewForMyUnique.RecyclerViewAdapterForAllMyUniques;
import pmvs.com.unique.View.RecyclerViewForUniques.ResourceViewForMyUnique.RecyclerViewAdapterMyAllUniques;
import pmvs.com.unique.database.DataBaseHelper;
import pmvs.com.unique.model.Unique;

/**
 * Created by inot on 12.07.15.
 */
public class AllMyUniquesWithoutSelect extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {


    private RecyclerView myAllUniquesRecyclerView;
    private RecyclerViewAdapterMyAllUniques myAllUniquesAdapter;
    private RecyclerView.LayoutManager myAllUniquesLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DataBaseHelper dataBaseHelper;

    private List<Unique> myuniques = new ArrayList<>();





    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.d("unique list created");

        View view = inflater.inflate(R.layout.allmyuniques_without_select, container, false);
        dataBaseHelper = new DataBaseHelper(getActivity());

        View addButton = view.findViewById(R.id.addItem2);
        addButton.setOnClickListener(this);

        //Wichtig, da source Image viereckig ist und wir einen Runden Button wollen
        addButton.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                // int diameter = getResources().getDimensionPixelSize(R.dimen.add_button_radius);
                outline.setOval(5, 5, 85, 85);
            }
        });



        // Initializing views.
        myAllUniquesRecyclerView = (RecyclerView) view.findViewById(R.id.myunique);

        // Initializing SwipeWrapper for the RecyclerView
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_by_all_my_uniques);
        swipeRefreshLayout.setOnRefreshListener(this);

        // If the size of views will not change as the data changes.
        myAllUniquesRecyclerView.setHasFixedSize(true);


        myAllUniquesLayoutManager = new LinearLayoutManager(getActivity());
        myAllUniquesRecyclerView.setLayoutManager(myAllUniquesLayoutManager);


        dataBaseHelper = new DataBaseHelper(getActivity());

        // Setting the adapter.
        myAllUniquesAdapter = new RecyclerViewAdapterMyAllUniques(retrieveAllMyUniques());
        dataBaseHelper.closeDB();

        myAllUniquesRecyclerView.setAdapter(myAllUniquesAdapter);
        return view;
    }


    //for all uniques from DB
    public List<Unique> retrieveAllMyUniques() {
        return dataBaseHelper.getAllMyUniques();
    }


    //animating the refresh with swipe and updates the RecyclerView
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        myAllUniquesAdapter = new RecyclerViewAdapterMyAllUniques(retrieveAllMyUniques());
        dataBaseHelper.closeDB();

        myAllUniquesRecyclerView.setAdapter(myAllUniquesAdapter);

        myAllUniquesAdapter.notifyDataSetChanged();

        // stopping swipe refresh
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //new child
            case R.id.addItem2:
                ((MaterialNavigationDrawer) this.getActivity()).setFragmentChild(new CreateUniqueFragment(), "Create New Unique");
                break;
        }
    }
}