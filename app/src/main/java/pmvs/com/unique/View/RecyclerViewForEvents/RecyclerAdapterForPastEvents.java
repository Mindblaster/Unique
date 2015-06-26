package pmvs.com.unique.View.RecyclerViewForEvents;

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
import pmvs.com.unique.model.Event;

/**
 * Created by inot on 26.06.15.
 */
public class RecyclerAdapterForPastEvents  extends RecyclerView.Adapter<RecyclerViewHolderForPastEvents> {

    private List<Event> mData = new ArrayList<>();

    public RecyclerAdapterForPastEvents (List<Event> eventList) {
        this.mData = eventList;
    }

    @Override
    public RecyclerViewHolderForPastEvents onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View eventItem = inflater.inflate(R.layout.event_past_item, viewGroup, false);
        return new RecyclerViewHolderForPastEvents(eventItem);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderForPastEvents viewHolder, int position) {
        Event eventForRecyclerView = mData.get(position);
        viewHolder.title.setText(eventForRecyclerView.getTitle());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        viewHolder.address.setText(dateFormat.format(eventForRecyclerView.getTillDate()));
        viewHolder.icon.setBackgroundColor(Color.parseColor("#cccccc"));
        viewHolder.amount.setText("32");
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



