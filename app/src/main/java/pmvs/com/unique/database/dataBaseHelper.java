package pmvs.com.unique.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pmvs.com.unique.model.Event;
import pmvs.com.unique.model.Unique;

/**
 * Created by inot on 12.06.15.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Debug tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "uniqueDB";

    // all three tables
    private static final String TABLE_EVENTS = "events";
    private static final String TABLE_UNIQUES = "uniques";
    private static final String TABLE_UNIQUE_EVENT = "uniques_events";

    //  column names for both table
    private static final String KEY_ID = "id";

    // unique Table - columns
    private static final String KEY_UNIQUE_NAME = "name";
    private static final String KEY_TAG = "tag";
    private static final String KEY_TEXT = "text";
    private static final String KEY_PHONENUM = "phonenumber";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_FB = "facebookname";
    private static final String KEY_TWITTER = "twittername";
    private static final String KEY_ISFAVORITE = "favorite";
    private static final String KEY_SERVERID = "serverID";
    private static final String KEY_POSITION = "position";

    // EVENTS Table - column names
    private static final String KEY_EVENT_TITLE = "title";
    private static final String KEY_EVENT_FROMDATE = "fromDate";
    private static final String KEY_EVENT_TILLDATE = "tillDate";
    private static final String KEY_EVENT_ADDRESS = "address";
    private static final String KEY_EVENT_USEDUNIQUEID = "myuniqueid";
    private static final String KEY_EVENT_ISUNIQUESHARED = "uniqueShared";
    private static final String KEY_EVENT_PIC = "eventPic";


    // UNIQUE_EVENTS Table - column names
    private static final String KEY_UNIQUES_ID = "unique_id";
    private static final String KEY_EVENTS_ID = "event_id";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm", Locale.ENGLISH);

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_UNIQUES = "CREATE TABLE "
            + TABLE_UNIQUES + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_UNIQUE_NAME + " TEXT,"
            + KEY_TAG + " TEXT,"
            + KEY_TEXT + " TEXT,"
            + KEY_PHONENUM + " TEXT,"
            + KEY_EMAIL + " TEXT,"
            + KEY_FB + " TEXT,"
            + KEY_TWITTER + " TEXT,"
            + KEY_ISFAVORITE + " TEXT,"
//            + KEY_SERVERID + " TEXT,"
//            + KEY_POSITION + " TEXT"
            + ")";

    // Tag table create statement
    private static final String CREATE_TABLE_EVENTS = "CREATE TABLE "
            + TABLE_EVENTS +
            "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_EVENT_TITLE + " TEXT,"
            + KEY_EVENT_FROMDATE + " DATETIME"
            + KEY_EVENT_TILLDATE + " DATETIME"
            + KEY_EVENT_ADDRESS + " TEXT,"
            + KEY_EVENT_USEDUNIQUEID + " TEXT,"
            + KEY_EVENT_ISUNIQUESHARED + " INTEGER,"
            + KEY_EVENT_PIC + " TEXT,"
            + ")";

    // todo_tag table create statement
    private static final String CREATE_TABLE_UNIQUE_EVENT = "CREATE TABLE "
            + TABLE_UNIQUE_EVENT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_EVENTS_ID + " INTEGER," + KEY_UNIQUES_ID + " INTEGER,"
            +"ON DELETE CASCADE"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_EVENTS);
        db.execSQL(CREATE_TABLE_UNIQUES);
        db.execSQL(CREATE_TABLE_UNIQUE_EVENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIQUES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIQUE_EVENT);

        // create new tables
        onCreate(db);
    }
    /*
 * Creating a UniqueEntry
 */
    public long createUniqueEntry(Unique unique, int event_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UNIQUE_NAME, unique.getName());
        values.put(KEY_TAG, unique.getTag());
        values.put(KEY_TEXT, unique.getText());
        values.put(KEY_EMAIL, unique.geteMail());
        values.put(KEY_FB, unique.getFacebookName());
        values.put(KEY_TWITTER, unique.getTwitterName());
        values.put(KEY_ISFAVORITE, 0);
        values.put(KEY_PHONENUM, unique.getPhoneNumber());

        // insert row
        long unique_id = db.insert(TABLE_UNIQUES, null, values);
        unique.setLocalID((int)unique_id);


        // assigning tags to todo

       createUniqueEventEntry (unique_id, event_id);


        return unique_id;
    }

    /*
 * get single Unique
 */
    public Unique getUnique(long unique_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_UNIQUES + " WHERE "
                + KEY_ID + " = " + unique_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Unique unique = new Unique();
        unique.setLocalID(c.getInt(c.getColumnIndex(KEY_ID)));
        unique.setName((c.getString(c.getColumnIndex(KEY_UNIQUE_NAME))));
        unique.seteMail(c.getString(c.getColumnIndex(KEY_EMAIL)));
        unique.setTwitterName(c.getString(c.getColumnIndex(KEY_TWITTER)));
        unique.setFacebookName(c.getString(c.getColumnIndex(KEY_FB)));
        unique.setTag(c.getString(c.getColumnIndex(KEY_TAG)));
        unique.setText(c.getString(c.getColumnIndex(KEY_TEXT)));

        if(c.getInt(c.getColumnIndex(KEY_ISFAVORITE))==1)
            {
                unique.setFavorite(true);
            }
        return unique;
    }

   // SELECT * FROM uniques;
    /*
     * getting all uniques
     * */
    public List<Unique> getAllUniques() {
        List<Unique> uniques = new ArrayList<Unique>();
        String selectQuery = "SELECT  * FROM " + TABLE_UNIQUES;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Unique unique = new Unique();
                 unique.setLocalID (c.getInt((c.getColumnIndex(KEY_ID))));
                unique.setName((c.getString(c.getColumnIndex(KEY_UNIQUE_NAME))));
                unique.seteMail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                unique.setTwitterName(c.getString(c.getColumnIndex(KEY_TWITTER)));
                unique.setFacebookName(c.getString(c.getColumnIndex(KEY_FB)));
                unique.setTag(c.getString(c.getColumnIndex(KEY_TAG)));
                unique.setText(c.getString(c.getColumnIndex(KEY_TEXT)));
                if(c.getInt(c.getColumnIndex(KEY_ISFAVORITE))==1)
                {
                    unique.setFavorite(true);
                }

                // adding to uniq list
                uniques.add(unique);
            } while (c.moveToNext());
        }

        return uniques;
    }
   // SELECT * FROM uniques un, events ev, uniques_events unev WHERE ev.ev_name = ‘EXPO’ AND ev.id = unev.ev_id AND un.id = unev.unique_id;
    /*
     * getting all Unique of one Event
     * */
    public List<Unique> getAllUniquesOfEvent(String event_title) {
        List<Unique> uniques = new ArrayList<Unique>();

        String selectQuery = "SELECT  * FROM " + TABLE_UNIQUES + " un, "
                + TABLE_EVENTS+ " ev, " + TABLE_UNIQUE_EVENT + " unev WHERE ev."
                + KEY_EVENT_TITLE + " = '" + event_title + "'" + " AND ev." + KEY_ID
                + " = " + "unev." + KEY_EVENTS_ID + " AND un." + KEY_ID + " = "
                + "unev." + KEY_UNIQUES_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Unique unique = new Unique();
                unique.setLocalID(c.getInt((c.getColumnIndex(KEY_ID))));
                unique.setName((c.getString(c.getColumnIndex(KEY_UNIQUE_NAME))));
                unique.seteMail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                unique.setTwitterName(c.getString(c.getColumnIndex(KEY_TWITTER)));
                unique.setFacebookName(c.getString(c.getColumnIndex(KEY_FB)));
                unique.setTag(c.getString(c.getColumnIndex(KEY_TAG)));
                unique.setText(c.getString(c.getColumnIndex(KEY_TEXT)));
                if(c.getInt(c.getColumnIndex(KEY_ISFAVORITE))==1)
                {
                    unique.setFavorite(true);
                }

                // adding to unique list
                uniques.add(unique);
            } while (c.moveToNext());
        }

        return uniques;
    }
        /*
        * set unique favorite
        */
    public int setUniqueFavorite(Unique unique) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        if(unique.getFavorite()){
            values.put(KEY_ISFAVORITE, 1);
        }
        else{
            values.put(KEY_ISFAVORITE, 0);
        }


        // updating row
        return db.update(TABLE_UNIQUES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(unique.getLocalID()) });
    }
     /*
     * Deleting a unique
     */
        public void deleteUnique(long unique_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_UNIQUES, KEY_ID + " = ?",
                new String[] { String.valueOf(unique_id) });
    }

    /*
    * Creating evententry
    */
    public long createEventEntry(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EVENT_TITLE, event.getTitle());
        values.put(KEY_EVENT_ADDRESS, event.getAddress());
        values.put(KEY_EVENT_PIC, event.getEventPic());
        values.put(KEY_EVENT_FROMDATE, formatter.format(event.getFromDate()));
        values.put(KEY_EVENT_TILLDATE, formatter.format(event.getTillDate()));

       if( event.isUniqueShared()){
            values.put(KEY_EVENT_ISUNIQUESHARED, 1);
            values.put(KEY_EVENT_USEDUNIQUEID, event.getMyUniqueId());
        }
        else{
           values.put(KEY_EVENT_ISUNIQUESHARED, 0);
           values.put(KEY_EVENT_USEDUNIQUEID, 0);
       }

        // insert row
        long event_id = db.insert(TABLE_EVENTS, null, values);

        return event_id;
    }

    //SELECT * FROM EVENTS;
    /**
     * getting all tags
     * */
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<Event>();
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Event event = new Event();
                event.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                event.setTitle(c.getString(c.getColumnIndex(KEY_EVENT_TITLE)));
                event.setAddress(c.getString(c.getColumnIndex(KEY_EVENT_ADDRESS)));
                event.setEventPic(c.getString(c.getColumnIndex(KEY_EVENT_PIC)));
                event.setFromDate(formatter.parse(c.getString(c.getColumnIndex(KEY_EVENT_FROMDATE)), new ParsePosition(0)));
                event.setTillDate(formatter.parse(c.getString(c.getColumnIndex(KEY_EVENT_TILLDATE)), new ParsePosition(0)));
                event.setMyUniqueId(Integer.parseInt(c.getString(c.getColumnIndex(KEY_EVENT_USEDUNIQUEID))));
                //event.setUniqueShared(c.getString(c.getColumnIndex(KEY_EVENT_USEDUNIQUEID)))

                // adding to events list
                events.add(event);
            } while (c.moveToNext());
        }
        return events;
    }

    /*
 * Updating a tag
 */
    public int updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EVENT_TITLE, event.getTitle());
        //other fields follows next time
        //TODO

        // updating row
        return db.update(TABLE_EVENTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(event.getId()) });
    }


    /*
 * Deleting a tag
 */
    public void deleteEvent(Event event, boolean should_delete_all_event_uniques) {
        SQLiteDatabase db = this.getWritableDatabase();

        // before deleting event
        // check if uniques under this tag should also be deleted
        if (should_delete_all_event_uniques) {
            // get all uniques under this tag
            List<Unique> allEventUniques = getAllUniquesOfEvent(event.getTitle());

            // delete all Uniques
            for (Unique unique : allEventUniques) {
               // delete unique
               deleteUnique(unique.getLocalID());
                }
            }

        // now delete the tag
        db.delete(TABLE_EVENTS, KEY_ID + " = ?",
              new String[] { String.valueOf(event.getId()) });
    }

    /*
     * Creating todo_tag
     */
    public long createUniqueEventEntry(long unique_id, long event_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UNIQUES_ID, unique_id);
        values.put(KEY_EVENTS_ID, event_id);

        long id = db.insert(TABLE_UNIQUE_EVENT, null, values);

        return id;
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}

