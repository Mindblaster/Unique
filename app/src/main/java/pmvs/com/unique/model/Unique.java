package pmvs.com.unique.model;

/**
 * Created by inot on 30.05.15.
 */
public class Unique {
    private String name;
    private int localID;
    private String tag;
    private String text;
    private String phoneNumber;
    private String eMail;
    private String facebookName;
    private String twitterName;
    private Boolean favorite;

    public Unique (String initName, int initLocalID, String initTag,
                  String initText, String initPhoneNumber, String initEMail,
                  String initFacebookName, String initTwitterName, boolean initFavorite) {
        this.name = initName;
        this.localID = initLocalID;
        this.tag = initTag;
        this.text = initText;
        this.phoneNumber = initPhoneNumber;
        this.eMail = initEMail;
        this.facebookName=initFacebookName;
        this.twitterName=initTwitterName;
        this.favorite=initFavorite;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocalID() {
        return localID;
    }

    public void setLocalID(int localID) {
        this.localID = localID;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getFacebookName() {
        return facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }

    public String getTwitterName() {
        return twitterName;
    }

    public void setTwitterName(String twitterName) {
        this.twitterName = twitterName;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}
