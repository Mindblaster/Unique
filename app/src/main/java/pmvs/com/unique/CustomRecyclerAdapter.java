package pmvs.com.unique;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by inot on 25.05.15.
 * adapter for handling operations on the RecyclerView without comments
 */
public class CustomRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {


    private List<Event> mData = new ArrayList<>(); //Collections.emptyList();

    public CustomRecyclerAdapter() {
        // Pass context or other static stuff that will be needed.
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        viewHolder.title.setText(mData.get(position).text);
        viewHolder.title2.setText(mData.get(position).text2);
        viewHolder.icon.setBackgroundColor(Color.parseColor(mData.get(position).color));
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


