package com.example.mysqlite.Adapters;

import android.content.Context;
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
    public static final int VERSION_NAME = 1;
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VRCHAR (255), "+AGE+" INTEGER)";

    private Context context;

    public SqliteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, VERSION_NAME);
        this.context = context ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try{
                Toast.makeText(context,"Database create successfully",Toast.LENGTH_LONG).show();
                sqLiteDatabase.execSQL(CREATE_TABLE);
            }catch (Exception e){
                Log.d("exception",""+e);
            }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
