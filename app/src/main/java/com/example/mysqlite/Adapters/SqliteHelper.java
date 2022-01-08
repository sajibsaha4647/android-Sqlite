package com.example.mysqlite.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "Student_details";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String GENDER = "gender";
    public static final int VERSION_NAME = 1;
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ( "+ID+" INTEGER  PRIMARY KEY AUTOINCREMENT, "+NAME+" VRCHAR (255), "+AGE+" INTEGER, "+GENDER+" VRCHAR (15) )";
    public static final String SELECT_ALL = "SELECT * FROM " +TABLE_NAME ;
    public static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;


    private Context context;

    public SqliteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, VERSION_NAME);
        this.context = context ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try{
                Toast.makeText(context,"Database create successfully", Toast.LENGTH_SHORT).show();
                sqLiteDatabase.execSQL(CREATE_TABLE);
            }catch (Exception e){
                Log.d("exception",""+e);
            }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
           try{
               Toast.makeText(context,"Database updated successfully",Toast.LENGTH_LONG).show();
               sqLiteDatabase.execSQL(DROP_TABLE);
               onCreate(sqLiteDatabase);
           }catch (Exception e){
               Log.d("e",""+e);
           }
    }

    public long insetData(String name , String age, String gender){ //insert data
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME,name);
            contentValues.put(AGE,age);
            contentValues.put(GENDER,gender);
            long status = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
            return status;
    }

    public Cursor DisplayAllData (){ //Display data
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL,null);
        return  cursor;
    }
}
