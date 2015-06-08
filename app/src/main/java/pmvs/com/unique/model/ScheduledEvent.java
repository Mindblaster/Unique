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
    private boolean isActive;

    public ScheduledEvent(Date from, Date till, String uniqueServerID){
            this.from=from;
            this.till=till;
            this.uniqueServerID=uniqueServerID;
    }


}
