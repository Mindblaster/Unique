package pmvs.com.unique;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Raphael on 15.06.2015.
 */
public class DateStringConverter {


    public String dateToString(Date date){
        String dateString;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try{
            dateString = dateFormat.format( date );
            return dateString;
        }catch (Exception ex ){
            System.out.println(ex);
            return "";
        }
    }
    public Date stringToDate(String inputDate){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            Date date = dateFormat.parse(inputDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
