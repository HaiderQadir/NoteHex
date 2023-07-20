package com.hq.notesapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * @author HaiderQadir
 **/

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static final String DB_NAME = "notes_db";
    private static final int DB_VERSION = 9;
    private static final String TABLE_NAME = "mytable";
    private static final String TIME = "time";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "note_title";
    private static final String DESCRIPTION_COL = "note_description";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY, " + NAME_COL + " TEXT," + DESCRIPTION_COL + " TEXT," + TIME + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @SuppressLint("Range")
    public ArrayList getNotes() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = db.rawQuery("Select id,note_description from mytable", null);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < resultSet.getCount(); i++) {
            if (resultSet.moveToNext()) {
                arrayList.add(resultSet.getString(resultSet.getColumnIndex("note_description")));
            }
        }
        return arrayList;
    }

    @SuppressLint("Range")
    public ArrayList getTime() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = db.rawQuery("Select id,time from mytable", null);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < resultSet.getCount(); i++) {
            if (resultSet.moveToNext()) {
                arrayList.add(resultSet.getString(resultSet.getColumnIndex("time")));
            }
        }
        return arrayList;
    }

    public void updatenotes(String id, String desc) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DESCRIPTION_COL, desc);

        db.update(TABLE_NAME, values, DESCRIPTION_COL + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteItem(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, DESCRIPTION_COL + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void addNewNote(String courseDescription, String time) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(TIME, time);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
