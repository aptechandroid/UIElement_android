package com.uits.register.sqliteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.lang.UScript;

import androidx.annotation.Nullable;

import com.uits.register.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseHelper
 * <p>
 * Copyright © 2019 UITS CO.,LTD
 * Created PHUQUY on 2019-12-19.
 **/
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "manager_user_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(User.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        onCreate(db);
    }

    /**
     * insert a row on the tables
     *
     * @param user
     * @return
     */
    public long insertUser(User user) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(User.COLUMN_USERNAME, user.getUserName());
        values.put(User.COLUMN_FIRSTNAME, user.getFirstName());
        values.put(User.COLUMN_LASTNAME, user.getLastName());
        values.put(User.COLUMN_EMAIL, user.getEmail());
        values.put(User.COLUMN_NUMBERPHONE, user.getNumberPhone());
        values.put(User.COLUMN_PASSWORD, user.getPassword());

        // insert row
        long id = db.insert(User.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    /**
     * list user
     *
     * @return
     */
    public List<User> getAllUser() {
        List<User> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + User.TABLE_NAME + " ORDER BY " +
                User.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User note = new User();
                note.setId(cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)));
                note.setUserName(cursor.getString(cursor.getColumnIndex(User.COLUMN_USERNAME)));
                note.setFirstName(cursor.getString(cursor.getColumnIndex(User.COLUMN_FIRSTNAME)));
                note.setLastName(cursor.getString(cursor.getColumnIndex(User.COLUMN_LASTNAME)));
                note.setEmail(cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)));
                note.setNumberPhone(cursor.getString(cursor.getColumnIndex(User.COLUMN_NUMBERPHONE)));
                note.setPassword(cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(User.COLUMN_TIMESTAMP)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    /**
     * get information of user
     *
     * @param username
     * @return
     */
    public User getUser(String username) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID, User.COLUMN_USERNAME, User.COLUMN_FIRSTNAME,
                        User.COLUMN_LASTNAME, User.COLUMN_EMAIL, User.COLUMN_NUMBERPHONE, User.COLUMN_PASSWORD,
                        User.COLUMN_TIMESTAMP},
                User.COLUMN_USERNAME + "=?",
                new String[]{String.valueOf(username)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        User note = new User();
        note.setId(cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)));
        note.setUserName(cursor.getString(cursor.getColumnIndex(User.COLUMN_USERNAME)));
        note.setFirstName(cursor.getString(cursor.getColumnIndex(User.COLUMN_FIRSTNAME)));
        note.setLastName(cursor.getString(cursor.getColumnIndex(User.COLUMN_LASTNAME)));
        note.setEmail(cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)));
        note.setNumberPhone(cursor.getString(cursor.getColumnIndex(User.COLUMN_NUMBERPHONE)));
        note.setPassword(cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)));
        note.setTimestamp(cursor.getString(cursor.getColumnIndex(User.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return note;
    }

    /**
     * upgrade of a user
     *
     * @param user
     * @return
     */
    public int updateNote(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_USERNAME, user.getUserName());
        values.put(User.COLUMN_FIRSTNAME, user.getFirstName());
        values.put(User.COLUMN_LASTNAME, user.getLastName());
        values.put(User.COLUMN_EMAIL, user.getEmail());
        values.put(User.COLUMN_NUMBERPHONE, user.getNumberPhone());
        values.put(User.COLUMN_PASSWORD, user.getPassword());
        // updating row
        return db.update(User.TABLE_NAME, values, User.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
    }

    /**
     * delete of a user
     *
     * @param user
     */
    public void deleteNote(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(User.TABLE_NAME, User.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
}
