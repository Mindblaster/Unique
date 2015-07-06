package pmvs.com.unique.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Raphael on 07.06.2015.
 */
public class ScheduledEvent {
    private String uniqueServerID;
    private Date from;
    private Date till;
    private long local_eventID;
    private boolean isActive;

    public ScheduledEvent(Date from, Date till, String uniqueServerID, long local_eventID){
            this.from=from;
            this.till=till;
            this.uniqueServerID=uniqueServerID;
            this.local_eventID=local_eventID;
    }


    public String getUniqueServerID(){
        return this.uniqueServerID;
    }
    public Date getFrom(){
        return this.from;
    }
    public Date getTill(){
        return this.till;
    }

    public long getLocal_eventID(){
        return this.local_eventID;
    }
}
