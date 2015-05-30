package pmvs.com.unique.model;

import java.util.Date;
import java.util.List;

/**
 * Created by inot on 30.05.15.
 */
public class Event {

    private int id;
    private String title;
    private Date from;
    private Date till;
    private String address;
    private int myMyUniqueId;
    private boolean uniqueShared;
    private String eventPic;
    private List<Unique> receivedUniques;
    private int countAllReceivedUniques;

    public Event(int initId, String initTitle, Date initFrom, Date initTill, String initAddress, int initMyUniqueID, boolean initUniqueShared, List<Unique> initReceivedUniques, String initEventPic) {
        this.id = initId;
        this.title = initTitle;
        this.from = initFrom;
        this.till = initTill;
        this.address = initAddress;
        this.myMyUniqueId = initMyUniqueID;
        this.uniqueShared = initUniqueShared;
        this.eventPic = initEventPic;
        this.receivedUniques = initReceivedUniques;
    }


    //instead of separate class event Description i will use two constructors and for the eventDescription function the next one will
    //be used.
    public Event(int initId, String initTitle, Date initFrom, Date initTill, int initCountAllReceivedUniques) {
        this.id = initId;
        this.title = initTitle;
        this.from = initFrom;
        this.till = initTill;
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

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTill() {
        return till;
    }

    public void setTill(Date till) {
        this.till = till;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMyMyUniqueId() {
        return myMyUniqueId;
    }

    public void setMyMyUniqueId(int myMyUniqueId) {
        this.myMyUniqueId = myMyUniqueId;
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
