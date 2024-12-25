package com.example.dbconnection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database version and name
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserData5.db";

    // Table and columns
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_AGE = "age";

    // Create table SQL query
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_AGE + " INTEGER "
            + ");";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    // Called when database version is upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Insert data into users table
    public void insertUser(String name, String email, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertQuery = "INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_AGE + ") "
                + "VALUES ('" + name + "', '" + email + "', " + age + ");";
        db.execSQL(insertQuery);
        db.close();
    }

    // Read all users from the database
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
    }
}
