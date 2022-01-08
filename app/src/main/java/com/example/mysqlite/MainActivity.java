package com.example.mysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mysqlite.Adapters.SqliteHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

      SqliteHelper sqliteHelper;
      private RadioGroup radioGroup;
      private RadioButton radioButton;
      private EditText Name,Age;
      private Button signup;
      private String Radiovalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.editone);
        Age = findViewById(R.id.edittwo);
        radioGroup = findViewById(R.id.RadioGroupid1);
        signup= findViewById(R.id.Signup);

        sqliteHelper = new SqliteHelper(MainActivity.this);
        SQLiteDatabase sqLiteDatabase = sqliteHelper.getWritableDatabase();

        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String name = Name.getText().toString();
        String age = Age.getText().toString();
        if(radioGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(MainActivity.this,"Gender are required",Toast.LENGTH_LONG).show();
        }else{
            try {
                int selectedid = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedid);
                 Radiovalue =  radioButton.getText().toString();
            }catch (Exception e){}
        }

        if(view.getId() == R.id.Signup){
           long rowid =  sqliteHelper.insetData(name,age,Radiovalue);
           if(rowid>0){
               Toast.makeText(MainActivity.this,"Row "+rowid+"Successfully inserted",Toast.LENGTH_LONG).show();
               Name.setText("");
               Age.setText("");
               radioGroup.setSelected(false);
           }else{
               Toast.makeText(MainActivity.this,"Row "+rowid+"UnSuccessfull",Toast.LENGTH_LONG).show();
           }
        }


    }
}