package pmvs.com.unique.View.RecyclerViewForEvents;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pmvs.com.unique.R;
import pmvs.com.unique.database.DataBaseHelper;
import pmvs.com.unique.model.Event;

/**
 * Created by inot on 26.06.15.
 */
public class RecyclerAdapterForPastEvents extends RecyclerView.Adapter<RecyclerViewHolderForPastEvents> {
   DataBaseHelper dataBaseHelper;
    Activity mActivity;


    private List<Event> mData = new ArrayList<>();

    public RecyclerAdapterForPastEvents(List<Event> eventList, Activity activity) {
        this.mData = eventList;
        this.mActivity=activity;
    }

    @Override
    public RecyclerViewHolderForPastEvents onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View eventItem = inflater.inflate(R.layout.event_past_item, viewGroup, false);
        return new RecyclerViewHolderForPastEvents(eventItem, mData);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderForPastEvents viewHolder, int position) {
        dataBaseHelper = new DataBaseHelper(mActivity);
        Event eventForRecyclerView = mData.get(position);
        viewHolder.title.setText(eventForRecyclerView.getTitle());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        viewHolder.address.setText(dateFormat.format(eventForRecyclerView.getTillDate()));
        //todo retrieve real amount from db
        Integer idForCast = position;
        long positionInLong = idForCast.longValue();
        int amountOfAllUniques  = dataBaseHelper.getCountOfAllUniquesOfEvent(eventForRecyclerView.getId());
        System.out.println(amountOfAllUniques);
        Integer amountInt = amountOfAllUniques;

        viewHolder.amount.setText(amountInt.toString());
        // viewHolder.icon.setBackgroundColor(Color.parseColor(mData.get(position).color));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateList(List<Event> events) {
        mData = events;
        notifyDataSetChanged();
    }

    public void addItem(int position, Event event) {
        position = 0;
        mData.add(position, event);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}



