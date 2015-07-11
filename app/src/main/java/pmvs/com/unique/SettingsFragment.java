package pmvs.com.unique;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * Created by inot on 11.07.15.
 */
public class SettingsFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.impressum, container, false);
            return view;
        }

        @Override
        public void onClick(View v) {
            ((MaterialNavigationDrawer) this.getActivity()).setFragmentChild(new CreateEventFragment(), "Create Event");
        }
    }