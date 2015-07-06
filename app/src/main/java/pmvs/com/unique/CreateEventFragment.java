package pmvs.com.unique;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseException;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import pmvs.com.unique.database.DataBaseHelper;
import pmvs.com.unique.model.*;
import pmvs.com.unique.model.Event;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends DialogFragment {
    Calendar myCalendar = Calendar.getInstance();

    private int hours;
    private int minutes;

    private final int START_DATE=0;
    private final int START_TIME=2;
    private final int END_DATE=1;
    private final int END_TIME=3;
    private int dialogCaller;

    private EditText startDate;
    private EditText startTime;
    private EditText endDate;
    private EditText endTime;
    private DatePickerDialog.OnDateSetListener date;
    private TimePickerDialog.OnTimeSetListener time;
    private DateStringConverter dateStringConverter;
    private Button createButton;
    private Button cancelButton;
    private EditText eventTitle;
    private EditText eventLocation;
    private ImageView imageView;

    private Date startingDate;
    private Date endingDate;

    private pmvs.com.unique.model.Event event;
    private DataBaseHelper dataBaseHelper;
    private ParseManager parseManager;
    private CreateFragmentImageInterface createFragmentImageInterface;



    public CreateEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_create_event, container, false);
        startDate = (EditText)view.findViewById(R.id.event_starting_date);
        endDate = (EditText)view.findViewById(R.id.event_ending_date);
        startTime =(EditText) view.findViewById(R.id.event_starting_time);
        endTime =(EditText) view.findViewById(R.id.event_ending_time);
        createButton=(Button)view.findViewById(R.id.create_button);
        cancelButton=(Button)view.findViewById(R.id.cancel_button);

        imageView=(ImageView) view.findViewById(R.id.photo);

        eventTitle=(EditText)view.findViewById(R.id.event_title);
        eventLocation=(EditText)view.findViewById(R.id.event_location_name);

        dataBaseHelper= new DataBaseHelper(getActivity());

        dateStringConverter =new DateStringConverter();
        startDate.setText(dateStringConverter.dateToStringDate(new Date()));
        endDate.setText(dateStringConverter.dateToStringDate(new Date()));

        startTime.setText(dateStringConverter.dateToStringTime(new Date()));
        endTime.setText(dateStringConverter.dateToStringTime(new Date()));

        parseManager = new ParseManager(getActivity());

        setListeners();
        return view;
    }

    private void setListeners(){

        //One Listener for the DatePicker
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                updateLabel();
            }
        };

        //One Listener for the TimePicker


        time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hours=selectedHour;
                minutes=selectedMinute;
                updateLabel();
            }
        };


        //Listeners for Start and End Date

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCaller = START_DATE;
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCaller = END_DATE;
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCaller = START_TIME;
                new TimePickerDialog(getActivity(), time, hours, minutes, true).show();
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCaller = END_TIME;
                new TimePickerDialog(getActivity(), time, hours, minutes, true).show();
            }
        });

        //Listeners for buttons
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkifEmpty()){
                    if (!checkDates()) {
                        //TODO: Check Unique
// public Event(int initId, String initTitle, Date initFrom, Date initTill, String initAddress, int initMyUniqueID, boolean initUniqueShared, List<Unique> initReceivedUniques, String initEventPic) {
                        List<Unique> listUnique = new ArrayList<>();
                        event= new Event(0, eventTitle.getText().toString(),startingDate,endingDate,eventLocation.getText().toString(),0,false,listUnique,"kkk.jpg");


                        ////testUnique
                        Unique unique = new Unique("MyUnique", 123, "personal", "Please meet me!", "08912345678", "maxmustermann@test.de", "max", "mmuster", false);
                        unique.setPosition(new LatLng(48.163327, 11.565246));

                        try {
                            unique.setServerID(parseManager.uploadUnique(unique));
                        } catch (ParseException pe) {
                            Log.e("Failure", "Error Failed to Upload Unique");
                        }


                        //TODO: set serverID in Local Database
                        long localEventID=dataBaseHelper.createEventEntry(event);
                        dataBaseHelper.closeDB();

                        Intent newIntent = new Intent(getActivity(), UniqueService.class);
                        newIntent.putExtra("FLAG", 0);
                        newIntent.putExtra("from", dateStringConverter.dateToString(startingDate));
                        newIntent.putExtra("till", dateStringConverter.dateToString(endingDate));
                        newIntent.putExtra("Unique_ServerID", unique.getServerID());
                        newIntent.putExtra("Local_EventID", localEventID);
                        getActivity().startService(newIntent);
                        getActivity().getSupportFragmentManager().popBackStack();


                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Event was Created")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        getActivity().onBackPressed();
                                    }
                                }).create().show();

                    }
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFragmentImageInterface.setImage(imageView);
            }
        });


    }


    private void updateLabel() {
        String myFormat = "dd.MM.yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.GERMAN);

        switch(dialogCaller){
            case START_DATE:
                startDate.setText(sdf.format(myCalendar.getTime()));
                break;
            case END_DATE:
                endDate.setText(sdf.format(myCalendar.getTime()));
                break;
            case START_TIME:
                if(minutes==0){
                    startTime.setText(hours + ":" + minutes + minutes);
                }
                else{
                    startTime.setText(hours + ":" + minutes);
                }
                break;

            case END_TIME:
                if(minutes==0){
                    endTime.setText(hours + ":" + minutes + minutes);
                }
                else{
                    endTime.setText(hours + ":" + minutes);
                }
                break;
            default:
                break;
        }
        dialogCaller=5;

    }

    private boolean checkifEmpty(){

        if(eventTitle.getText().toString().equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Please set an Event Title! ")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    }).create().show();
            return (true);
        }
        else if(eventLocation.getText().toString().equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Please set Event Location")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    }).create().show();
            return (true);
        }
        return (false);
    }

    private boolean checkDates(){
        // get values from editTexts and create Date objects
        String startingDateString = startDate.getText().toString() + " " + startTime.getText().toString();
        startingDate = dateStringConverter.stringToDate(startingDateString);

        String endingDateString = endDate.getText().toString() + " " + endTime.getText().toString();
        endingDate = dateStringConverter.stringToDate(endingDateString);

        //Check if starting date is at least 5 mins in future
        Date currentDateTime = new Date();
        //1) check if starting date is today or in the future
        System.out.println(currentDateTime.compareTo(startingDate));
        if ( startingDate.getTime()- System.currentTimeMillis() < 300000) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Please set a Starting Date and Time at least 5 Minutes from now")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        }).create().show();
                return (true);
        }
        //2.)check if endingDate is after startingDate
        else if(endingDate.getTime() - startingDate.getTime()<=0) {
            if (startingDate.getTime() - System.currentTimeMillis() < 300000) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Please set an Ending Date and Time after the starting Time")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        }).create().show();
                return (true);
            }
        }
        return false;
    }
    @Override
         public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            createFragmentImageInterface = (CreateFragmentImageInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must createfragmentinterface");
        }
    }


    public interface CreateFragmentImageInterface{
        public void setImage(ImageView imageView);
    }

}
