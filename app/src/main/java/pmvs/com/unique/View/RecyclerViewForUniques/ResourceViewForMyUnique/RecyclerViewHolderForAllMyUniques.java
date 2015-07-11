package pmvs.com.unique.View.RecyclerViewForUniques.ResourceViewForMyUnique;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import pmvs.com.unique.R;
import pmvs.com.unique.View.DetailViewOfMyUniqueChildFragment;
import pmvs.com.unique.View.DetailViewOfUniqueChildFragment;
import pmvs.com.unique.model.Unique;

/**
 * Created by inot on 09.07.15.
 */
public class RecyclerViewHolderForAllMyUniques extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView name;
    public TextView message;
    public TextView fb;
    public TextView twitter;
    public TextView tel;


    public ImageView fb_icon;
    public ImageView twitter_icon;
    public ImageView tel_icon;

    public List<Unique> myuniques;
    public ImageView unique_icon;
    //initialize all view elemnts for the adapter

    public RecyclerViewHolderForAllMyUniques(View itemView, List<Unique> myuniqueList) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.my_unique_name);
        message = (TextView) itemView.findViewById(R.id.my_unique_description);
        unique_icon = (ImageView) itemView.findViewById(R.id.my_unique_photo);
        fb_icon = (ImageView) itemView.findViewById(R.id.facebook_icon);
        twitter_icon = (ImageView) itemView.findViewById(R.id.twitter_icon);
        tel_icon = (ImageView) itemView.findViewById(R.id.phone_icon);

        fb = (TextView) itemView.findViewById(R.id.my_unique_fb);
        twitter = (TextView) itemView.findViewById(R.id.my_unique_twitter);
        tel = (TextView) itemView.findViewById(R.id.my_unique_phone);

        myuniques = myuniqueList;

        // Define click listener for the ViewHolder's View.
        itemView.setOnClickListener(this);
    }

    //by clicking on one item log entry in logcat and new fragment will set.
    @Override
    public void onClick(View v) {
        Log.d("RecViewHolder reported:", "Element of MYUniqueList" + getPosition() + " clicked.");
        int myidOfUniqueInDB = myuniques.get(getPosition()).getLocalID();

        Intent intent = new Intent().putExtra(Intent.EXTRA_TEXT, myidOfUniqueInDB);

        DetailViewOfMyUniqueChildFragment fragment = new DetailViewOfMyUniqueChildFragment();
        fragment.setArguments(intent.getExtras());
        ((MaterialNavigationDrawer) v.getContext()).setFragmentChild(fragment, "Details Unique");


    }
}