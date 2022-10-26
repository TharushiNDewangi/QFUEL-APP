package ead.sliit.qfuel.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "register.db";

    public DBHelper(@Nullable Context context) {
        super(context, "register.db",  null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table vehiowners(username TEXT primary key, role TEXT, vehicletype TEXT, password TEXT)");
        sqLiteDatabase.execSQL("create table staowners(username TEXT primary key, role TEXT, location TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists vehiowners");
        sqLiteDatabase.execSQL("drop table if exists staowners");

    }
    public Boolean vownerinsertData(String username,String f_role, String vehicletype, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("role",f_role);
        contentValues.put("vehicletype",vehicletype);
        contentValues.put("password",password);
        long result = MyDB.insert("vehiowners",null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean sownerinsertData(String username,String f_role, String location, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("role",f_role);
        contentValues.put("location",location);
        contentValues.put("password",password);
        long result = MyDB.insert("staowners",null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean vownercheckUsername(String user_name){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from vehiowners where username = ?",new String[]{user_name});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean sownercheckUsername(String user_name){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from staowners where username = ?",new String[]{user_name});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else{
            return false;
        }
    }
}
