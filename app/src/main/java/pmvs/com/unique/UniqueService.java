package pmvs.com.unique;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import pmvs.com.unique.model.*;

/**
 * Created by Raphael on 07.06.2015.
 */
public class UniqueService extends Service{
    ArrayList<ScheduledEvent> scheduledEvents;
    public final int NEW_EVENT=0;
    public final int DELETE_EVENT=1;
    public final int START_SERVICE=2;
    private DateStringConverter dateStringConverter;
    private Timer timer;
    public LocationManager locationManager;


    public UniqueService(){
    }


    @Override
    public void onCreate() {
        Log.d("Service", "onCreate called");
        scheduledEvents=new ArrayList<>();
        timer = new Timer();
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service", "onstartCommand called");
        dateStringConverter= new DateStringConverter();
        switch(intent.getIntExtra("FLAG",3)) {
            case (NEW_EVENT):
                ScheduledEvent scheduledEvent = new ScheduledEvent(dateStringConverter.stringToDate(intent.getStringExtra("from")),dateStringConverter.stringToDate(intent.getStringExtra("till")),intent.getStringExtra("Unique_ServerID"),intent.getLongExtra("Local_EventID",0));
                scheduledEvents.add(scheduledEvent);

                //StartEventTime
                long toStartDiffInMs = scheduledEvent.getFrom().getTime() - new Date().getTime();
                long toStartDiffInSec = TimeUnit.MILLISECONDS.toSeconds(toStartDiffInMs);

                System.out.println("startTime: "+ toStartDiffInSec);
                MyTimerTask myTimerTask = new MyTimerTask(scheduledEvent.getUniqueServerID(),scheduledEvent.getFrom(),scheduledEvent.getTill(),scheduledEvent.getLocal_eventID());

                //If Event Start is in the Past Task is not Scheduled (Should either be caught by the UI or we can set the task to Start immediately
                if(toStartDiffInSec>0) {
                    timer.schedule(myTimerTask, scheduledEvent.getFrom());
                }

                break;
            case (DELETE_EVENT):
                for(int i=0;i<scheduledEvents.size();i++){
                    if(scheduledEvents.get(i).getUniqueServerID().equals(intent.getStringExtra("Unique_ServerID"))){
                        scheduledEvents.remove(i);
                    }
                }
                break;
            case START_SERVICE:
                System.out.println("Service Started");
                break;
        }
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private class MyTimerTask extends TimerTask {
        private String uniqueServerID;
        private long localEventID;
        private Date startingDate;
        private Date endingDate;

        public MyTimerTask(String uniqueServerID,Date startingDate,Date endingDate,long localEventID){
            this.uniqueServerID=uniqueServerID;
            this.startingDate=startingDate;
            this.endingDate=endingDate;
            this.localEventID=localEventID;
        }

        @Override
        public void run() {
            //StartEventTime
            long execTimeInMs = endingDate.getTime() - startingDate.getTime();
            long execTimeInNS = TimeUnit.MILLISECONDS.toNanos(execTimeInMs);

            long startingTimeInNS=System.nanoTime();

            System.out.println("Task begins!");
            boolean toFinish = false;

            LocationManager locationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);

            LocationListener locationListener = new UniqueLocationlistener(getApplicationContext(),uniqueServerID,localEventID);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1,locationListener, Looper.getMainLooper());
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 1,locationListener, Looper.getMainLooper());
            long sleepinterval=execTimeInMs/100;

            while (!toFinish)
            {
                try {
                    Thread.sleep(sleepinterval);
                }
                catch(Exception e){
                    System.out.println("sleeping failed");
                }
                if(System.nanoTime()-startingTimeInNS>=execTimeInNS){
                    toFinish=true;
                    System.out.println("Task finished!");
                    locationManager.removeUpdates(locationListener);
                }
            }


        }

    }
}
