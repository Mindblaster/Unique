package pmvs.com.unique.View.RecyclerViewForUniques;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pmvs.com.unique.R;
import pmvs.com.unique.View.RecyclerViewForEvents.RecyclerViewHolderForPastEvents;
import pmvs.com.unique.model.Event;
import pmvs.com.unique.model.Unique;

/**
 * Adapter vor the list of all saved Uniques
 * Created by inot on 06.07.15.
 */
public class RecyclerViewAdapterForAllUniques extends RecyclerView.Adapter<RecyclerViewHolderForAllUniques> {

        private List<Unique> uniques = new ArrayList<>();

        public RecyclerViewAdapterForAllUniques(List<Unique> uniqueList) {
            this.uniques = uniqueList;
        }
        //inflate the correct view
        @Override
        public RecyclerViewHolderForAllUniques onCreateViewHolder(ViewGroup viewGroup, int position) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View eventItem = inflater.inflate(R.layout.unique_item, viewGroup, false);
            return new RecyclerViewHolderForAllUniques(eventItem, uniques);
        }
        //set data to the correct layout elements
        @Override
        public void onBindViewHolder(RecyclerViewHolderForAllUniques viewHolder, int position) {
            Unique uniqueForAllUniquesView = uniques.get(position);
            viewHolder.name.setText(uniqueForAllUniquesView.getName());
            viewHolder.message.setText(uniqueForAllUniquesView.getText());
            //todo change Icon
            //viewHolder.unique_icon.setImageBitmap("");
            if(uniqueForAllUniquesView.getFacebookName().length()>2){
                Log.e(uniqueForAllUniquesView.getFacebookName(), "facebook");
                viewHolder.fb_icon.setVisibility(View.VISIBLE);
            }
            else {
                viewHolder.fb_icon.setVisibility(View.INVISIBLE);
                }
            if(uniqueForAllUniquesView.getTwitterName().length()>2){

                viewHolder.twitter_icon.setVisibility(View.VISIBLE);
            }
            else {
                viewHolder.twitter_icon.setVisibility(View.INVISIBLE);
            }
            if(uniqueForAllUniquesView.getPhoneNumber().length()>2){
                viewHolder.tel_icon.setVisibility(View.VISIBLE);
            }
            else {
                viewHolder.tel_icon.setVisibility(View.INVISIBLE);
            }
            if(uniqueForAllUniquesView.getFavorite()){
                viewHolder.fav_icon.setVisibility(View.VISIBLE);
            }
            else {
                viewHolder.fav_icon.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return uniques.size();
        }

        public void updateList(List<Unique> uniqueList) {
            uniques = uniqueList;
            notifyDataSetChanged();
        }

        public void addItem(int position, Unique unique) {
            position = 0;
            uniques.add(position, unique);
            notifyItemInserted(position);
        }

        public void removeItem(int position) {
            uniques.remove(position);
            notifyItemRemoved(position);
        }
    }
