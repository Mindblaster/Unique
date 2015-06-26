package pmvs.com.unique.View.RecyclerViewForEvents;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pmvs.com.unique.R;

/**
 * Created by inot on 26.06.15.
 */
public class RecyclerViewHolderForPastEvents extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView address;
    public TextView amount;

    public ImageView icon;

    public RecyclerViewHolderForPastEvents(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title_of_paste_event);
        address = (TextView) itemView.findViewById(R.id.address_of_paste_event);
        icon = (ImageView) itemView.findViewById(R.id.icon3);
        amount = (TextView) itemView.findViewById(R.id.amount_of_uniques);

    }
}