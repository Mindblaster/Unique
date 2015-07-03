package pmvs.com.unique.model;

import java.util.Date;
import java.util.List;

/**
 * Created by inot on 30.05.15.
 */
public class Event {

    private int id;
    private String title;
    private Date fromDate;
    private Date tillDate;
    private String address;
    private int myUniqueId;
    private boolean uniqueShared;
    private String eventPic;
    private List<Unique> receivedUniques;
    private int countAllReceivedUniques;

    //constructor for dB
    public Event(){

    }
    // Constructor with all parameters
    public Event(int initId, String initTitle, Date initFrom, Date initTill, String initAddress, int initMyUniqueID, boolean initUniqueShared, List<Unique> initReceivedUniques, String initEventPic) {
        this.id = initId;
        this.title = initTitle;
        this.fromDate = initFrom;
        this.tillDate = initTill;
        this.address = initAddress;
        this.myUniqueId = initMyUniqueID;
        this.uniqueShared = initUniqueShared;
        this.eventPic = initEventPic;
        this.receivedUniques = initReceivedUniques;
    }


    //instead of separate class event Description i will use two constructors and for the eventDescription function the next one will
    //be used.
    public Event(int initId, String initTitle, Date initFrom, Date initTill, int initCountAllReceivedUniques) {
        this.id = initId;
        this.title = initTitle;
        this.fromDate = initFrom;
        this.tillDate = initTill;
        this.countAllReceivedUniques = initCountAllReceivedUniques;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getTillDate() {
        return tillDate;
    }

    public void setTillDate(Date tillDate) {
        this.tillDate = tillDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMyUniqueId() {
        return myUniqueId;
    }

    public void setMyUniqueId(int myUniqueId) {
        this.myUniqueId = myUniqueId;
    }

    public boolean isUniqueShared() {
        return uniqueShared;
    }

    public void setUniqueShared(boolean uniqueShared) {
        this.uniqueShared = uniqueShared;
    }

    public String getEventPic() {
        return eventPic;
    }

    public void setEventPic(String eventPic) {
        this.eventPic = eventPic;
    }

    public List<Unique> getReceivedUniques() {
        return receivedUniques;
    }

    public void setReceivedUniques(List<Unique> receivedUniques) {
        this.receivedUniques = receivedUniques;
    }

    public int getCountAllReceivedUniques() {
        return countAllReceivedUniques;
    }

    public void setCountAllReceivedUniques(int countAllReceivedUniques) {
        this.countAllReceivedUniques = countAllReceivedUniques;
    }
}
