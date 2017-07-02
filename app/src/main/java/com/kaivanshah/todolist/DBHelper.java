package com.kaivanshah.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by kaivanrasiklal.s on 17-03-2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ToDoListDB_NEW";

    // Contacts table name
    private static final String TABLE_TODOLIST = "ToDoList_TBL";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_DATE = "DueDate";
    private static final String KEY_STATUS = "status";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TODOLIST + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_STATUS + " INTEGER )";

        db.execSQL(CREATE_CONTACTS_TABLE);


        ToDoList_CLASS obj=new ToDoList_CLASS(1,"Pay Bill", "Credit Card Bill", "06/06/2017", 1);
        setDeafultValues(db, obj);
        obj=new ToDoList_CLASS(1,"Pay Bill", "Electricity Bill", "10/06/2017", 1);
        setDeafultValues(db, obj);
        obj=new ToDoList_CLASS(1,"Thursday", "Session at 11:00 PM", "15/06/2017", 1);
        setDeafultValues(db, obj);





    }

    public void setDeafultValues(SQLiteDatabase db, ToDoList_CLASS oToDoList)
    {
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, oToDoList._title); // Title
        values.put(KEY_DESCRIPTION, oToDoList._Description); // Description
        values.put(KEY_DATE, oToDoList._DueDate); // Due Date
        values.put(KEY_STATUS, oToDoList._status); // Status
        // Inserting Row
        db.insert(TABLE_TODOLIST, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODOLIST);
        // Create tables again
        onCreate(db);
    }


    // Adding new product
    void addToDoItem(ToDoList_CLASS oToDoList) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, oToDoList._title); // Title
        values.put(KEY_DESCRIPTION, oToDoList._Description); // Description
        values.put(KEY_DATE, oToDoList._DueDate); // Due Date
        values.put(KEY_STATUS, oToDoList._status); // Status
        // Inserting Row
        db.insert(TABLE_TODOLIST, null, values);
        db.close();

    }

    // Getting All ToDoList
    public ArrayList<ToDoList_CLASS> getAllItems() {
        ArrayList<ToDoList_CLASS> oToDoList = new ArrayList<ToDoList_CLASS>();
        // Select All Query
        String selectQuery = "SELECT " + KEY_TITLE + ", " +  KEY_DESCRIPTION + ", " + KEY_DATE +  ", " + KEY_STATUS + " FROM " + TABLE_TODOLIST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ToDoList_CLASS objToDoList = new ToDoList_CLASS(cursor.getString(0), cursor.getString(1),cursor.getString(2), Integer.parseInt(cursor.getString(3)));
                oToDoList.add(objToDoList);
            } while (cursor.moveToNext());
        }

        // return To Do list\
        db.close();
        return oToDoList;
    }
}
