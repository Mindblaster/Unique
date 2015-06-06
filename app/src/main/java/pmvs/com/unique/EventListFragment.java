package pmvs.com.unique;


import android.graphics.Outline;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;


/**
 * inot
 * A simple  Fragment subclass.
 * Will be event list
 */
public class EventListFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private CustomRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private EditText mText;
    private EditText mText2;
    private EditText mColor;

    private List<Event> mData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.d("MaterialNavigationDrawer Master-Child", "Master created");
        //initialize Buttons
        View view = inflater.inflate(R.layout.fragment_eventlist, container, false);
        ((Button) view.findViewById(R.id.addItem)).setOnClickListener(this);


        // Initializing views.

        mText = (EditText) view.findViewById(R.id.textEt);
        mText2 = (EditText) view.findViewById(R.id.textEt2);
        mColor = (EditText) view.findViewById(R.id.colorEt);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        // If the size of views will not change as the data changes.
        mRecyclerView.setHasFixedSize(true);

        // Setting the LayoutManager.
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Setting the adapter.
        mAdapter = new CustomRecyclerAdapter(createList(10));
        mRecyclerView.setAdapter(mAdapter);

        //DEIN BUTTON: Listener auf Action Button um items zur Liste zu adden
        View addButton = (View) view.findViewById(R.id.add_button);
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

    // Called when add button is clicked.
    public void addItem(View view) {

        // Add data locally to the list.
        Event dataToAdd = new Event(
                mText.getText().toString(),
                mText2.getText().toString(),
                mColor.getText().toString());
        mData.add(dataToAdd);

        // Update adapter.
        mAdapter.addItem(mData.size() - 1, dataToAdd);
    }

    //OnclickListener for all Buttons
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //add to list
            case R.id.addItem:
                addItem(v);
                break;
            //new child
            case R.id.add_button:
                ((MaterialNavigationDrawer) this.getActivity()).setFragmentChild(new ChildFragment(), "one subwindow is open");

                break;

        }
    }

    private List<Event> createList(int size) {

        List<Event> result = new ArrayList<Event>();
        for (int i = 1; i <= size; i++) {
            Event exampleEvent = new Event("Name" + i, "Nachname" + i, "#cccccc");
            result.add(exampleEvent);

        }
        return result;
    }
}

