package pmvs.com.unique;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by inot on 25.05.15.
 * view for RecyclerView
 * initialize the layout
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView title2;

    public ImageView icon;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        title2 = (TextView) itemView.findViewById(R.id.title2);

        icon = (ImageView) itemView.findViewById(R.id.icon);
    }
}