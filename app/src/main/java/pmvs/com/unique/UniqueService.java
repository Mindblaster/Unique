package pmvs.com.unique;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import pmvs.com.unique.model.*;

/**
 * Created by Raphael on 07.06.2015.
 */
public class UniqueService extends Service {
    ArrayList<ScheduledEvent> scheduledEvents;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        ScheduledEvent scheduledEvent = new ScheduledEvent((Date)intent.getSerializableExtra("FROM_DATE"),(Date)intent.getSerializableExtra("TILL_DATE"),intent.getStringExtra("UniqueServerID"));
        scheduledEvents.add(scheduledEvents.size()-1,scheduledEvent);
        return Service.START_STICKY;//has to be set to START_STICKY later
        //return Service.START_NOT_STICKY;
    }
}
