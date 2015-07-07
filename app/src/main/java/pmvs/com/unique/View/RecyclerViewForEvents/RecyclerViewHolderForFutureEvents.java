package pmvs.com.unique.View.RecyclerViewForEvents;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import pmvs.com.unique.R;
import pmvs.com.unique.View.DetailsViewOfEventChildFragment;
import pmvs.com.unique.model.Event;

/**
 * Created by inot on 15.06.15.
 */
public class RecyclerViewHolderForFutureEvents extends RecyclerView.ViewHolder  implements View.OnClickListener {


    public TextView title;
    public TextView address;

    public List<Event> events;
    public ImageView icon;

    public RecyclerViewHolderForFutureEvents(View itemView,  List<Event> eventList) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.titletitle);
        address = (TextView) itemView.findViewById(R.id.address);
        icon = (ImageView) itemView.findViewById(R.id.eventstandart_icon);
        events =eventList;

    // Define click listener for the ViewHolder's View.
        itemView.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
        Log.d("RecViewHolder reported:", "Element of FutureList" + getPosition() + " clicked.");
        int idOfEventInDB = events.get(getPosition()).getId();

        Intent intent = new Intent().putExtra(Intent.EXTRA_TEXT, idOfEventInDB);

        DetailsViewOfEventChildFragment fragment = new DetailsViewOfEventChildFragment();
        fragment.setArguments(intent.getExtras());
        ((MaterialNavigationDrawer) v.getContext()).setFragmentChild(fragment, "Details Event");


    }
}