package com.example.juliantolentino.laundromatic_2;

/**
 * Created by Robert on 3/14/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ClothingDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE clothes ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "type INTEGER, "+
                "color INTEGER, "+
                "name TEXT, " +
                "timesWorn INTEGER, "+
                "price REAL, "+
                "lastWorn DATE, "+
                "sweatValue INTEGER, "+
                "washing INTEGER, "+
                "drying INTEGER)";

        // create books table
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS books");

        // create fresh books table
        this.onCreate(db);
    }

    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    // Books table name
    private static final String TABLE_CLOTHES = "clothes";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_COLOR = "color";
    private static final String KEY_NAME = "name";
    private static final String KEY_TIMESWORN = "timesWorn";
    private static final String KEY_PRICE = "price";
    private static final String KEY_LASTWORN = "lastWorn";
    private static final String KEY_SWEATVALUE = "sweatValue";
    private static final String KEY_WASHING = "washing";
    private static final String KEY_DRYING = "drying";

    private static final String[] COLUMNS = {KEY_ID, KEY_TYPE, KEY_COLOR, KEY_NAME, KEY_TIMESWORN, KEY_PRICE, KEY_LASTWORN, KEY_SWEATVALUE, KEY_WASHING, KEY_DRYING};

    public void addClothing(Clothing clothing){
        Log.d("addClothing", clothing.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, clothing.getType()); // get type
        values.put(KEY_COLOR, clothing.getColor()); // get color
        values.put(KEY_NAME, clothing.getName());
        values.put(KEY_TIMESWORN, clothing.getTimesWorn());
        values.put(KEY_PRICE, clothing.getPrice());
        values.put(KEY_LASTWORN, clothing.getLastWorn());
        values.put(KEY_SWEATVALUE, clothing.getSweatValue());
        values.put(KEY_WASHING, clothing.getWashing());
        values.put(KEY_DRYING, clothing.getDrying());

        // 3. insert
        db.insert(TABLE_CLOTHES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Clothing getClothing(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_CLOTHES, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Clothing clothing = new Book();
        clothing.setId(Integer.parseInt(cursor.getString(0)));
        clothing.setType(Integer.parseInt(cursor.getString(1)));
        clothing.setColor(Integer.parseInt(cursor.getString(2)));
        clothing.setName(cursor.getString(3));
        clothing.setTimesWorn(Integer.parseInt(cursor.getString(4)));
        clothing.setPrice(cursor.getString(5));
        clothing.setLastWorn(cursor.getString(6));
        clothing.setSweatValue(Integer.parseInt(cursor.getString(7)));
        clothing.setWashing(Integer.parseInt(cursor.getString(8)));
        clothing.setDrying(Integer.parseInt(cursor.getString(9)));

        Log.d("getClothing(" + id + ")", clothing.toString());

        // 5. return book
        return clothing;
    }

    // Get All Books
    public List<Clothing> getAllBooks() {
        List<Clothing> clothes = new LinkedList<Clothing>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CLOTHES;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Clothing clothing = null;
        if (cursor.moveToFirst()) {
            do {
                clothing = new Clothing();
                clothing.setId(Integer.parseInt(cursor.getString(0)));
                clothing.setType(Integer.parseInt(cursor.getString(1)));
                clothing.setColor(Integer.parseInt(cursor.getString(2)));
                clothing.setName(cursor.getString(3));
                clothing.setTimesWorn(Integer.parseInt(cursor.getString(4)));
                clothing.setPrice(cursor.getString(5));
                clothing.setLastWorn(cursor.getString(6));
                clothing.setSweatValue(Integer.parseInt(cursor.getString(7)));
                clothing.setWashing(Integer.parseInt(cursor.getString(8)));
                clothing.setDrying(Integer.parseInt(cursor.getString(9)));

                // Add book to books
                clothes.add(clothing);
            } while (cursor.moveToNext());
        }

        Log.d("getAllClothes()", clothes.toString());

        // return books
        return clothes;
    }

    // Updating single book
    public int updateClothing(Clothing clothing) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("type", clothing.getType()); // get type
        values.put("color", clothing.getColor()); // get color
        values.put("name", clothing.getName());
        values.put("timesWorn", clothing.getTimesWorn());
        values.put("price", clothing.getPrice());
        values.put("lastWorn", clothing.getLastWorn());
        values.put("sweatValue", clothing.getSweatValue());
        values.put("washing", clothing.getWashing());
        values.put("drying", clothing.getDrying());

        // 3. updating row
        int i = db.update(TABLE_CLOTHES, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(clothing.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single book
    public void deleteClothing(Clothing clothing) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_CLOTHES,
                KEY_ID+" = ?",
                new String[] { String.valueOf(clothing.getId()) });

        // 3. close
        db.close();

        Log.d("deleteClothing", clothing.toString());

    }
}
