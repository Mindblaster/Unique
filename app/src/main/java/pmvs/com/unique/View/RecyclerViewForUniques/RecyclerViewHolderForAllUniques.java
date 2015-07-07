package pmvs.com.unique.View.RecyclerViewForUniques;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import pmvs.com.unique.R;
import pmvs.com.unique.View.DetailViewOfUniqueChildFragment;
import pmvs.com.unique.View.DetailsViewOfEventChildFragment;
import pmvs.com.unique.model.Unique;

/**
 * Created by inot on 06.07.15.
 * viewHolder for fragment with all uniques
 */
public class RecyclerViewHolderForAllUniques extends RecyclerView.ViewHolder  implements View.OnClickListener {

    public TextView name;
    public TextView message;
    public ImageView fb_icon;
    public ImageView twitter_icon;
    public ImageView tel_icon;
    public ImageView fav_icon;

    public List<Unique> uniques;
    public ImageView unique_icon;
    //initialize all view elemnts for the adapter
    public RecyclerViewHolderForAllUniques(View itemView,  List<Unique> uniqueList) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name_of_unique);
        message = (TextView) itemView.findViewById(R.id.message_of_unique);
        unique_icon = (ImageView) itemView.findViewById(R.id.unique_icon);
        fb_icon= (ImageView) itemView.findViewById(R.id.facebook_icon);
        twitter_icon= (ImageView) itemView.findViewById(R.id.twitter_icon);
        tel_icon= (ImageView) itemView.findViewById(R.id.phone_icon);
        fav_icon= (ImageView) itemView.findViewById(R.id.favo_icon);


        uniques =uniqueList;

        // Define click listener for the ViewHolder's View.
        itemView.setOnClickListener(this);
    }
    //by clicking on one item log entry in logcat and new fragment will set.
    @Override
    public void onClick(View v) {
        Log.d("RecViewHolder reported:", "Element of UniqueList" + getPosition() + " clicked.");
        int idOfUniqueInDB = uniques.get(getPosition()).getLocalID();

        Intent intent = new Intent().putExtra(Intent.EXTRA_TEXT, idOfUniqueInDB);

        DetailViewOfUniqueChildFragment fragment = new DetailViewOfUniqueChildFragment();
        fragment.setArguments(intent.getExtras());
       ((MaterialNavigationDrawer) v.getContext()).setFragmentChild(fragment, "Details Unique");


    }
}