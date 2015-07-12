package pmvs.com.unique.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pmvs.com.unique.R;

/**
 * Created by inot on 08.06.15.
 */
public class createNewEventFragment extends android.support.v4.app.Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Log.i("MaterialNavigationDrawer Master-Child", "Child created");
            return inflater.inflate(R.layout.masterchild_child, container, false);
        }

    }

