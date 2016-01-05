package com.qingluan.darkh.wificontroll.Config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by darkh on 1/5/16.
 */
public class DBHandler extends SQLiteOpenHelper{
    private static final String DATA_NAME = "WIFI_CONTROL";
    private static final String TABLE_NAME = "WC_DB";
    private static final int DATA_VERSION = 1;

    private static final String CREATE_DB_STRING = "create table " + TABLE_NAME + " (" +
            "id integer primary key autoincrement ," +
            "ip varchar(100) ," +
            "tag varchar(100) " +
            ");";

    private Context context;

    public DBHandler(Context context){
        super(context,DATA_NAME,null,DATA_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        SQLiteDatabase  sql  = db;

        ContentValues defalt_value = new ContentValues();
        defalt_value.put("ip",arguments.HOST);
        defalt_value.put("tag",arguments.DEFAULT_TAG);

        sql.execSQL(CREATE_DB_STRING);
        sql.insert(TABLE_NAME,null,defalt_value);
    }

    public List<String[]> getAllIpList(){
        SQLiteDatabase sql = this.getReadableDatabase();
        Cursor c = sql.query(TABLE_NAME, null, null, null, null, null, null, null);
        List <String[]> result = new ArrayList<String[]>();
        c.moveToFirst();
        result.add(new String[]{
                c.getString(c.getColumnIndex("tag")),
                c.getString(c.getColumnIndex("ip")),
        });
        while(c.moveToNext()){
            result.add(new String[]{
                    c.getString(c.getColumnIndex("tag")),
                    c.getString(c.getColumnIndex("ip")),
            });
        }
        c.close();
        sql.close();
        return  result;

    }

    public  boolean removeOneByTag(String tag){
        SQLiteDatabase sql = this.getWritableDatabase();
        boolean result =  sql.delete(TABLE_NAME, "tag=" + tag, null) > 0;
        sql.close();
        return  result;
    }

    public boolean change(String tag ,String ip){
        ContentValues cv = new ContentValues();
        cv.put("tag",tag);
        cv.put("ip",ip);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = db.update(TABLE_NAME,cv,null,null) > 0;
        db.close();
        return  result;

    }

    public long addOne(String tag,String ip){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ip",ip);
        cv.put("tag",tag);
        long re = db.insert(TABLE_NAME,null,cv);
        db.close();
        return  re;
    }

    private void addOneItemToList(List<String[]> result,Cursor c){

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
