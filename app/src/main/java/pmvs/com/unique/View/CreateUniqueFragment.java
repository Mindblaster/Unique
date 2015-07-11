package pmvs.com.unique.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import pmvs.com.unique.R;
import pmvs.com.unique.database.DataBaseHelper;
import pmvs.com.unique.model.Unique;

/**
 * Created by inot on 11.07.15.
 */
public class CreateUniqueFragment extends DialogFragment {

    private Button createButton;
    private Button cancelButton;
    private EditText uniqueName;
    private EditText uniqueMessage;
    private EditText uniqueTwitter;
    private EditText uniqueFB;
    private EditText uniqueTel;
    private ImageView imageView;


    private pmvs.com.unique.model.Unique unique;
    private DataBaseHelper dataBaseHelper;


    public CreateUniqueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.createunique, container, false);

        createButton = (Button) view.findViewById(R.id.create_button);
        cancelButton = (Button) view.findViewById(R.id.cancel_button);

        imageView = (ImageView) view.findViewById(R.id.my_unique_photo);

        uniqueName = (EditText) view.findViewById(R.id.my_unique_name);
        uniqueMessage = (EditText) view.findViewById(R.id.my_unique_description);
        uniqueFB = (EditText) view.findViewById(R.id.my_unique_fb);
        uniqueTwitter = (EditText) view.findViewById(R.id.my_unique_twitter);
        uniqueTel = (EditText) view.findViewById(R.id.my_unique_phone);

        dataBaseHelper = new DataBaseHelper(getActivity());

        setListeners();
        return view;
    }

    private void setListeners() {
        //Listeners for buttons
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkIfEmpty()) {

                    unique = new Unique(uniqueName.getText().toString(), 123, "photopath", uniqueMessage.getText().toString(), uniqueTel.getText().toString(), "conference@test.de", uniqueFB.getText().toString(), uniqueTwitter.getText().toString(), false);
                    long createdId = dataBaseHelper.createMyUniqueEntry(unique);
                    // unique.setPosition(new LatLng(48.163327, 11.565246));

                    dataBaseHelper.closeDB();

                    if (createdId == -1) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("An error is occurred. Unique was not created")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        getActivity().onBackPressed();
                                    }
                                }).create().show();
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Unique was Created")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    getActivity().onBackPressed();
                                }
                            }).create().show();
                }
            }

        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


    }


    private boolean checkIfEmpty() {
        if (uniqueName.getText().toString().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Please set your name! ")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    }).create().show();
            return true;
        } else if (uniqueMessage.getText().toString().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Please set your message")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    }).create().show();
            return true;
        }
        return false;
    }

}
