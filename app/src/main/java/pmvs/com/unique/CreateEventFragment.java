package pmvs.com.unique;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


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

        dateStringConverter =new DateStringConverter();
        startDate.setText(dateStringConverter.dateToStringDate(new Date()));
        endDate.setText(dateStringConverter.dateToStringDate(new Date()));

        startTime.setText(dateStringConverter.dateToStringTime(new Date()));
        endTime.setText(dateStringConverter.dateToStringTime(new Date()));

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


        //Listners for Start and End Date

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCaller=START_DATE;
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCaller=END_DATE;
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCaller=START_TIME;
                new TimePickerDialog(getActivity(),time,hours,minutes,true).show();
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCaller=END_TIME;
                new TimePickerDialog(getActivity(),time,hours,minutes,true).show();
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


}
