package com.example.mysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.mysqlite.Adapters.SqliteHelper;

public class MainActivity extends AppCompatActivity {

      SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqliteHelper = new SqliteHelper(MainActivity.this);
        SQLiteDatabase sqLiteDatabase = sqliteHelper.getWritableDatabase();
    }
}