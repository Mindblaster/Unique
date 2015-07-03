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
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try{
            dateString = dateFormat.format( date );
            return dateString;
        }catch (Exception ex ){
            System.out.println(ex);
            return "";
        }
    }


    public String dateToStringDate(Date date){
        String dateString;
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try{
            dateString = dateFormat.format( date );
            return dateString;
        }catch (Exception ex ){
            System.out.println(ex);
            return "";
        }
    }
    public String dateToStringTime(Date date){
        String dateString;
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        try{
            dateString = dateFormat.format( date );
            return dateString;
        }catch (Exception ex ){
            System.out.println(ex);
            return "";
        }
    }


    public Date stringToDate(String inputDate){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try {
            Date date = dateFormat.parse(inputDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Date stringToDateDate(String inputDate){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = dateFormat.parse(inputDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Date stringToDateTime(String inputDate){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        try {
            Date date = dateFormat.parse(inputDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
