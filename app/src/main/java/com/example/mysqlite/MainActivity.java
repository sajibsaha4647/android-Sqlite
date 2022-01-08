package com.example.mysqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysqlite.Adapters.SqliteHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SqliteHelper sqliteHelper;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText Name, Age;
    private Button signup, sawid;
    private String Radiovalue;

    private TextView TextOne,Texttwo,Textthree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.editone);
        Age = findViewById(R.id.edittwo);
        radioGroup = findViewById(R.id.RadioGroupid1);
        signup = findViewById(R.id.Signup);
        sawid = findViewById(R.id.seeid);

        TextOne = findViewById(R.id.renderName);
        Texttwo = findViewById(R.id.renderage);
        Textthree = findViewById(R.id.renderGender);


        sqliteHelper = new SqliteHelper(MainActivity.this);
        SQLiteDatabase sqLiteDatabase = sqliteHelper.getWritableDatabase();

        signup.setOnClickListener(this);
        sawid.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String name = Name.getText().toString();
        String age = Age.getText().toString();
        SQLiteDatabase sqLiteDatabase = sqliteHelper.getWritableDatabase();



        try {
            int selectedid = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedid);
            Radiovalue = radioButton.getText().toString();
        } catch (Exception e) {
        }

        if (view.getId() == R.id.Signup) {
            long rowid = sqliteHelper.insetData(name, age, Radiovalue);
            if (rowid > 0) {
                Toast.makeText(MainActivity.this, "Row " + rowid + "Successfully inserted", Toast.LENGTH_LONG).show();
                Name.setText("");
                Age.setText("");
                radioGroup.setSelected(false);
            } else {
                Toast.makeText(MainActivity.this, "Row " + rowid + "UnSuccessfull", Toast.LENGTH_LONG).show();
            }

        }


        if (view.getId() == R.id.seeid) {
            Cursor cursor = sqliteHelper.DisplayAllData();
            if (cursor.getCount() != 0) {
                StringBuffer stringBuffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    stringBuffer.append("_id" + cursor.getString(0)+"\n");
                    stringBuffer.append("Name" + cursor.getString(1)+"\n");
                    stringBuffer.append("Age" + cursor.getString(2)+"\n");
                    stringBuffer.append("Gender" + cursor.getString(3)+"\n");
                }

                ShowAllData("Result set",stringBuffer.toString());

            } else {
                return;
            }

        }

    }

    public void ShowAllData(String title, String result){

//            if(result.length() != 0 ){
//                for(int i= 0; i <result.length(); i++){
//
//                    Log.d("tag","M"+result.name);
//                }
//            }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(result);
        builder.setCancelable(true);
        builder.show();

    }
}