package pmvs.com.unique.View.RecyclerViewForUniques.RecyclerViewForUniquesBelowEvent;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pmvs.com.unique.R;
import pmvs.com.unique.View.RecyclerViewForUniques.RecyclerViewHolderForAllUniques;
import pmvs.com.unique.model.Unique;

/**
 * Created by inot on 07.07.15.
 */
public class RecyclerViewAdapterForUniquesOfEvent extends RecyclerView.Adapter<RecyclerViewHolderForUniquesOfEvent> {

        private List<Unique> uniques = new ArrayList<>();

        public RecyclerViewAdapterForUniquesOfEvent(List<Unique> uniqueList) {
            this.uniques = uniqueList;
        }
        //inflate the correct view
        @Override
        public RecyclerViewHolderForUniquesOfEvent onCreateViewHolder(ViewGroup viewGroup, int position) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View eventItem = inflater.inflate(R.layout.unique_item, viewGroup, false);
            return new RecyclerViewHolderForUniquesOfEvent(eventItem, uniques);
        }
        //set data to the correct layout elements
        @Override
        public void onBindViewHolder(RecyclerViewHolderForUniquesOfEvent viewHolder, int position) {
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



