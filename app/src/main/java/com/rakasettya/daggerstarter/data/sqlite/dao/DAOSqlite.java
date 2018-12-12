package com.rakasettya.daggerstarter.data.sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.rakasettya.daggerstarter.data.sqlite.model.token.Token;
import com.rakasettya.daggerstarter.data.sqlite.model.user.User;
import java.util.ArrayList;
import java.util.List;

public class DAOSqlite extends SQLiteOpenHelper {

  // All Static variables
  // Database Version
  private static final int DATABASE_VERSION = 1;

  // Database Name
  private static final String DATABASE_NAME = "BCDdata";

  // Contacts table name
  private static final String TABLE_TOKEN = "token";
  private static final String TABLE_USER = "user";

  // Contacts Table Columns names
  // Table Token
  private static final String KEY_TOKEN = "access_token";
  // Table User
  private static final String KEY_ID = "id";
  private static final String KEY_ROID = "role_id";
  private static final String KEY_FCTK = "fcm_token";
  private static final String KEY_USID = "userable_id";
  private static final String KEY_NAME = "name";
  private static final String KEY_UNME = "username";
  private static final String KEY_EMIL = "email";

  public DAOSqlite(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATE_TOKEN_TABLE =
        "CREATE TABLE " + TABLE_TOKEN + "("
            + KEY_TOKEN + " TEXT" + ");";
    db.execSQL(CREATE_TOKEN_TABLE);
    String CREATE_USER_TABLE =
        "CREATE TABLE " + TABLE_USER + "("
            + KEY_ID + " TEXT,"
            + KEY_ROID + " TEXT,"
            + KEY_USID + " TEXT,"
            + KEY_NAME + " TEXT,"
            + KEY_FCTK + " TEXT,"
            + KEY_UNME + " TEXT,"
            + KEY_EMIL + " TEXT"+ ");";
    db.execSQL(CREATE_USER_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    // Drop older table if existed
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOKEN);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
    // Create tables again
    onCreate(db);
  }
  // Adding new contact
  public void addToken(Token token) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(KEY_TOKEN, token.getToken());
    // Inserting Row
    db.insert(TABLE_TOKEN, null, values);
    db.close(); // Closing database connection
  }

  public void addUser(User user) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(KEY_EMIL, user.getEmail());
    values.put(KEY_FCTK, user.getFcmToken());
    values.put(KEY_NAME, user.getDisplayName());
    values.put(KEY_UNME, user.getUsername());
    values.put(KEY_ID, user.getId());
    values.put(KEY_ROID, user.getRoleId());
    values.put(KEY_USID,user.getUserableId());
    // Inserting Row
    db.insert(TABLE_USER, null, values);
    db.close(); // Closing database connection
  }

  // Getting single contact
  public Token getToken(String token) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.query(TABLE_TOKEN, new String[] { KEY_TOKEN }, KEY_TOKEN + "=?",
        new String[] { token }, null, null, null, null);
    if (cursor != null)
      cursor.moveToFirst();

    Token token1 = new Token(cursor.getString(0));
    // return contact
    return token1;
  }

  public User getUser(String id) {
    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.query(TABLE_USER, new String[] { KEY_USID,
            KEY_ROID, KEY_FCTK, KEY_ID, KEY_NAME, KEY_EMIL, KEY_UNME }, KEY_ID + "=?",
        new String[] { id }, null, null, null, null);
    if (cursor != null)
      cursor.moveToFirst();

    User user = new User(cursor.getString(0),
        cursor.getString(1)
        , cursor.getString(2)
        , cursor.getString(3)
        , cursor.getString(4)
        , cursor.getString(5)
        , cursor.getString(6));
    // return contact
    return user;
  }

  // Getting All Contacts
  public List<Token> getAllToken() {
    List<Token> TokenList = new ArrayList<Token>();
    // Select All Query
    String selectQuery = "SELECT  * FROM " + TABLE_TOKEN;

    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (cursor.moveToFirst()) {
      do {
        Token token = new Token();
        token.setToken(cursor.getString(0));
        // Adding contact to list
        TokenList.add(token);
      } while (cursor.moveToNext());
    }

    // return contact list
    return TokenList;
  }

  public List<User> getAllUser() {
    List<User> UserList = new ArrayList<User>();
    // Select All Query
    String selectQuery = "SELECT  * FROM " + TABLE_USER;

    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (cursor.moveToFirst()) {
      do {
        User user = new User();
        user.setUserableId(cursor.getString(0));
        user.setRoleId(cursor.getString(1));
        user.setFcmToken(cursor.getString(2));
        user.setId(cursor.getString(3));
        user.setDisplayName(cursor.getString(4));
        user.setEmail(cursor.getString(5));
        user.setUsername(cursor.getString(6));
        // Adding contact to list
        UserList.add(user);
      } while (cursor.moveToNext());
    }

    // return contact list
    return UserList;
  }

  // Updating single contact
  public int updateToken(Token token, String tokens) {
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(KEY_TOKEN, token.getToken());

    // updating row
    return db.update(TABLE_TOKEN, values, KEY_TOKEN + " =?",
        new String[] { tokens });
  }

  public int updateUser(User user, String users) {
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(KEY_EMIL, user.getEmail());
    values.put(KEY_FCTK, user.getFcmToken());
    values.put(KEY_NAME, user.getDisplayName());
    values.put(KEY_UNME, user.getUsername());
    values.put(KEY_ID, user.getId());
    values.put(KEY_ROID, user.getRoleId());
    values.put(KEY_USID,user.getUserableId());

    // updating row
    return db.update(TABLE_USER, values, KEY_ID + " =?",
        new String[] { users });
  }

  // Deleting single contact
  public void deleteToken(Token token, String tokens) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_TOKEN, KEY_TOKEN + " =?",
        new String[] { tokens });
    db.close();
  }

  public void deleteUser(User user, String users) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_USER, KEY_ID + " =?",
        new String[] { users });
    db.close();
  }

  // Getting contacts Count
  public int getTokenCount() {
    String countQuery = "SELECT  * FROM " + TABLE_TOKEN;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(countQuery, null);
    cursor.close();

    // return count
    return cursor.getCount();
  }

  public int getUserCount() {
    String countQuery = "SELECT  * FROM " + TABLE_USER;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(countQuery, null);
    cursor.close();

    // return count
    return cursor.getCount();
  }

  public void deleteAllToken() {
    SQLiteDatabase db = this.getWritableDatabase();
    db.execSQL("DELETE FROM " + TABLE_TOKEN);
    db.execSQL("VACUUM");
    db.close();
  }

  public void deleteAllUser() {
    SQLiteDatabase db = this.getWritableDatabase();
    db.execSQL("DELETE FROM " + TABLE_USER);
    db.execSQL("VACUUM");
    db.close();
  }
}
