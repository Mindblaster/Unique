package pmvs.com.unique.View.RecyclerViewForEvents;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import pmvs.com.unique.CreateEventFragment;
import pmvs.com.unique.R;
import pmvs.com.unique.View.DetailsViewOfEventChildFragment;
import pmvs.com.unique.model.Event;

/**
 * Created by inot on 26.06.15.
 */
public class RecyclerViewHolderForPastEvents extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView title;
    public TextView address;
    public TextView amount;
    public List<Event> events;
    public ImageView icon;

    public RecyclerViewHolderForPastEvents(final View itemView, List<Event> eventList) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title_of_paste_event);
        address = (TextView) itemView.findViewById(R.id.address_of_paste_event);
        icon = (ImageView) itemView.findViewById(R.id.icon3);
        amount = (TextView) itemView.findViewById(R.id.amount_of_uniques);

        // Define click listener for the ViewHolder's View.
        itemView.setOnClickListener(this);
        events = eventList;
    }

    @Override
    public void onClick(View v) {
        Log.d("RecViewHolder reported:", "Element " + getPosition() + " clicked.");
        int idOfEventInDB = events.get(getPosition()).getId();

        Intent intent = new Intent().putExtra(Intent.EXTRA_TEXT, idOfEventInDB);

        DetailsViewOfEventChildFragment fragment = new DetailsViewOfEventChildFragment();
        fragment.setArguments(intent.getExtras());
        ((MaterialNavigationDrawer) v.getContext()).setFragmentChild(fragment, "Details Event");


                /*
                * Intent intent = new...
                    Intent(getApplicationContext(), SecondActivity.class);
                    intent.putExtra("myKey", AnyValue);
                    startActivity(intent);
                      * */

        //(MaterialNavigationDrawer) itemView.getContext().set.setFragmentChild(new DetailsViewOfEventChildFragment(), "Child Title");
    }
}