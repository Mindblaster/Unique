package pmvs.com.unique.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import pmvs.com.unique.R;
import pmvs.com.unique.database.DataBaseHelper;
import pmvs.com.unique.model.Unique;
import android.widget.CompoundButton.OnCheckedChangeListener;



/**
 * Created by inot on 06.07.15.
 */

public class DetailViewOfUniqueChildFragment  extends android.support.v4.app.Fragment  {

    private TextView name;
    private TextView twitter;
    private TextView phoneNum;
    private TextView fB;
    private TextView message;
    private Unique unique;
    private DataBaseHelper dataBaseHelper;
    private ToggleButton toggleFavorites;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //  Log.d("MaterialNavigationDrawer Master-Child", "Child created");
        Bundle extras = this.getArguments();
        int tmp = extras.getInt(Intent.EXTRA_TEXT);
        long idOfUnique = Long.valueOf(tmp).longValue();
        //  Log.d("TESTTESTEST!!!!! ", tmp);
        View detailsView = inflater.inflate(R.layout.unique_detailed_view, container, false);
        name = (TextView) detailsView.findViewById(R.id.name_detailedview);
        phoneNum = (TextView) detailsView.findViewById(R.id.phone_of_detailedunique);
        twitter = (TextView) detailsView.findViewById(R.id.twitter_of_detailedunique);
        message = (TextView) detailsView.findViewById(R.id.text_of_detailedunique);

        toggleFavorites = (ToggleButton) detailsView.findViewById(R.id.toggle_fav_unique);

        fB = (TextView) detailsView.findViewById(R.id.fb_of_detailedunique);
        dataBaseHelper = new DataBaseHelper(getActivity());


        System.out.println(dataBaseHelper.isUniqueInDB("123") + "is the server Id unique ???");


        unique = dataBaseHelper.getUnique(idOfUnique);
        dataBaseHelper.closeDB();


        name.setText(unique.getName());
        twitter.setText(unique.getTwitterName());
        fB.setText(unique.getFacebookName());
        message.setText(unique.getText());
        phoneNum.setText(unique.getPhoneNumber());

        if( unique.getFavorite()) {

                toggleFavorites.setChecked(true);
                toggleFavorites.setBackgroundResource(R.drawable.favbuttoncolor);

        } else
            {
                toggleFavorites.setChecked(false);
                toggleFavorites.setBackgroundResource(R.drawable.favbutton_grey);


            }
            toggleFavorites.setOnCheckedChangeListener(listener);

        return detailsView;


    }
    OnCheckedChangeListener listener = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {
                toggleFavorites.setBackgroundResource(R.drawable.favbuttoncolor);
                unique.setFavorite(true);
                dataBaseHelper.setUniqueFavorite(unique);
                dataBaseHelper.closeDB();


            }
            else {
                unique.setFavorite(false);
                dataBaseHelper.setUniqueFavorite(unique);
                dataBaseHelper.closeDB();
                toggleFavorites.setBackgroundResource(R.drawable.favbutton_grey);


            }
            }
        };



}


