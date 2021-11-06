package com.first.kathainpocket1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BusinessDatabase extends SQLiteOpenHelper
{
    private static final String BUSINESS_DATABASE = "business_database";
    private static final int VERSION = 1;

    public BusinessDatabase( Context context)
    {
        super(context,BUSINESS_DATABASE,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("create table apna_business_list(_id INTEGER primary key autoincrement," +
                "name TEXT, type TEXT, image TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL("drop table if exists apna_business_list");
    }
    public boolean insertdata(String name, String type, String image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("type",type);
        values.put("image",image);
        long result = db.insert("apna_business_list",null,values);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean Business_name_check(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from apna_business_list where name=?",new String[]{name});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
