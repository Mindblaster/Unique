package pmvs.com.unique;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * Created by Inot 25.05.15
 * A simple  Fragment subclass.
 * Mock for other Framents
 */

public class MasterFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("MaterialNavigationDrawer Master-Child", "Master created");

        View view = inflater.inflate(R.layout.masterchild_master, container, false);
        ((Button) view.findViewById(R.id.master_button)).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        ((MaterialNavigationDrawer) this.getActivity()).setFragmentChild(new ChildFragment(), "Child Title");
    }
}
