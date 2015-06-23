package pmvs.com.unique.View.RecyclerViewForEvents;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pmvs.com.unique.R;

/**
 * Created by inot on 15.06.15.
 */
public class RecyclerViewHolderForEvents extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView address;

    public ImageView icon;

    public RecyclerViewHolderForEvents(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.titletitle);
        address = (TextView) itemView.findViewById(R.id.address);
        icon = (ImageView) itemView.findViewById(R.id.icon2);
    }
}