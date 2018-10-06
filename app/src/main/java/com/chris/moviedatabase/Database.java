package com.chris.moviedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {

    private Context context;
    private static final String name_bd = "MI_BD";
    private static final int version = 1;
    private SQLiteDatabase sqLiteDatabase;
    private OpenHelper openHelper;


    public Database(Context context) {
        this.context = context;
    }

    public Database open(){
        openHelper = OpenHelper.singleOpenHelper(context);
        sqLiteDatabase = openHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        sqLiteDatabase.close();
        openHelper.close();
    }

    public Cursor read(String sql){
        return sqLiteDatabase.rawQuery(sql,null);
    }

    public void insert(String table,ContentValues contentValues){
        sqLiteDatabase.insertWithOnConflict(table,null,contentValues,SQLiteDatabase.CONFLICT_IGNORE);
    }
    public void update(String table,ContentValues contentValues, String where){
        sqLiteDatabase.update(table,contentValues,where,null);
    }
    public void delete(String table, String where){
        sqLiteDatabase.delete(table,where,null);
    }

    private static class OpenHelper extends SQLiteOpenHelper{

        private static OpenHelper dbHelper1;


        public static synchronized OpenHelper singleOpenHelper(Context context){
            if(dbHelper1 == null){
                dbHelper1 = new OpenHelper(context);
                return dbHelper1;
            }
            return dbHelper1;
        }


        public OpenHelper(Context context) {
            super(context, name_bd, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS Favoritos (" +
                    "id INTEGER PRIMARY KEY, " +
                    "url  VARCHAR(250)," +
                    "name VARCHAR(250))");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Favoritos");
        }
    }
}