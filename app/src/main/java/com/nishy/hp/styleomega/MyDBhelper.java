package com.nishy.hp.styleomega;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MyDBhelper extends SQLiteOpenHelper {
    private static final String DBNAME = "mydb.db";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "Customers";
    public static final String ID = "id";
    public static final String NAME = "Name";
    public static final String PASSWORD = "Password";
    public static final String EMAIL = "email";
    public static final String PHONE_NUM = "phone_num";

    SQLiteDatabase myDB;

    public MyDBhelper(Context context) {
        super(context, DBNAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTable = " CREATE TABLE " + TABLE_NAME + " ( " +
                ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT NOT NULL, " +
                PASSWORD + " TEXT NOT NULL, " +
                EMAIL + " TEXT NOT NULL, " +
                PHONE_NUM + " TEXT NOT NULL "
                + " ) ";
        db.execSQL(queryTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDB() {
        myDB = getWritableDatabase();

    }

    public void closeDB() {
        if (myDB != null && myDB.isOpen()) {
            myDB.close();
        }
    }

    public long insert(int id, String name, String password, String email, String phone_num) {
        ContentValues values = new ContentValues();
        if (id != -1)
            values.put(ID, id);
        values.put(NAME, name);
        values.put(PASSWORD, password);
        values.put(EMAIL, email);
        values.put(PHONE_NUM, phone_num);

        return myDB.insert(TABLE_NAME, null, values);
    }

    public long update(int id, String name, String password, String email, String phone_num) {
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(PASSWORD, password);


        values.put(EMAIL, email);
        values.put(PHONE_NUM, phone_num);
        String where = ID + "=" + id;

        return myDB.update(TABLE_NAME, values, where, null);
    }

    public long delete(int id) {
        String where = ID + "=" + id;
        return myDB.delete(TABLE_NAME, where, null);
    }

    public Cursor getAllRecords() {
     //   myDB.query(TABLE_NAME,null,null,null,null,null,null);
        String query= "SELECT * FROM "  + TABLE_NAME;
        return myDB.rawQuery(query,null);
    }


}
