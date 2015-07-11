package pmvs.com.unique.View.RecyclerViewForUniques.ResourceViewForMyUnique;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pmvs.com.unique.R;
import pmvs.com.unique.model.Unique;

/**
 * Created by inot on 09.07.15.
 */
public class RecyclerViewAdapterForAllMyUniques extends RecyclerView.Adapter<RecyclerViewHolderForAllMyUniques> {

    private List<Unique> myuniques = new ArrayList<>();

    public RecyclerViewAdapterForAllMyUniques(List<Unique> uniqueList) {
        this.myuniques = uniqueList;
    }

    //inflate the correct view
    @Override
    public RecyclerViewHolderForAllMyUniques onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View eventItem = inflater.inflate(R.layout.my_unique_item, viewGroup, false);
        return new RecyclerViewHolderForAllMyUniques(eventItem, myuniques);
    }

    //set data to the correct layout elements
    @Override
    public void onBindViewHolder(RecyclerViewHolderForAllMyUniques viewHolder, int position) {
        Unique myuniqueForAllUniquesView = myuniques.get(position);
        viewHolder.name.setText(myuniqueForAllUniquesView.getName());
        viewHolder.message.setText(myuniqueForAllUniquesView.getText());
        viewHolder.fb.setText(myuniqueForAllUniquesView.getFacebookName());
        viewHolder.twitter.setText(myuniqueForAllUniquesView.getTwitterName());
        viewHolder.tel.setText(myuniqueForAllUniquesView.getPhoneNumber());


        //todo change Icon
        //viewHolder.unique_icon.setImageBitmap("");
        if (myuniqueForAllUniquesView.getFacebookName().length() > 1) {
            viewHolder.fb_icon.setVisibility(View.VISIBLE);
        } else {
            viewHolder.fb_icon.setVisibility(View.INVISIBLE);
        }
        if (myuniqueForAllUniquesView.getTwitterName().length() > 1) {

            viewHolder.twitter_icon.setVisibility(View.VISIBLE);
        } else {
            viewHolder.twitter_icon.setVisibility(View.INVISIBLE);
        }
        if (myuniqueForAllUniquesView.getPhoneNumber().length() > 1) {
            viewHolder.tel_icon.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tel_icon.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return myuniques.size();
    }

    public void updateList(List<Unique> uniqueList) {
        myuniques = uniqueList;
        notifyDataSetChanged();
    }

    public void addItem(int position, Unique unique) {
        position = 0;
        myuniques.add(position, unique);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        myuniques.remove(position);
        notifyItemRemoved(position);
    }
}
