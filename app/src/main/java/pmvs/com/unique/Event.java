package pmvs.com.unique;

/**
 * Created by inot on 25.05.15.
 * very simple mockup for working with recycle view
 */
public class Event {

    public String text;
    public String color;
    public String text2;

    public Event(String text, String text2, String color) {
        this.text = text;
        this.color = color;
        this.text2 = text2;
    }

    //mock for set/get not yet used
    public String getName() {

        return text;
    }

    public String getSurname() {

        return text2;
    }

    public String getColor() {

        return color;
    }
    public void setName(String initText) {
        text = initText;
    }


}
