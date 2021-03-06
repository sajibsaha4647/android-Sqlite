package com.example.mysqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    private EditText Name, Age,id;
    private Button signup, sawid,updated,deleted;
    private String Radiovalue;

    private AlertDialog.Builder alertDialogBuilder;

    private TextView TextOne,Texttwo,Textthree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.editone);
        Age = findViewById(R.id.edittwo);
        id = findViewById(R.id.editid);
        radioGroup = findViewById(R.id.RadioGroupid1);

        signup = findViewById(R.id.Signup); //insert
        sawid = findViewById(R.id.seeid); //show
        updated = findViewById(R.id.Updated); //updated
        deleted = findViewById(R.id.Delated); //delete

        TextOne = findViewById(R.id.renderName);
        Texttwo = findViewById(R.id.renderage);
        Textthree = findViewById(R.id.renderGender);


        sqliteHelper = new SqliteHelper(MainActivity.this);
        SQLiteDatabase sqLiteDatabase = sqliteHelper.getWritableDatabase();

        signup.setOnClickListener(this);
        sawid.setOnClickListener(this);
        updated.setOnClickListener(this);
        deleted.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String name = Name.getText().toString();
        String age = Age.getText().toString();
        String ataId = id.getText().toString();
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

        }else if (view.getId() == R.id.seeid) {
            Cursor cursor = sqliteHelper.DisplayAllData();
            if (cursor.getCount() != 0) {
                StringBuffer stringBuffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    stringBuffer.append("_id: " + cursor.getString(0)+"\n");
                    stringBuffer.append("Name: " + cursor.getString(1)+"\n");
                    stringBuffer.append("Age: " + cursor.getString(2)+"\n");
                    stringBuffer.append("Gender: " + cursor.getString(3)+"\n");
                }

                ShowAllData("Result set",stringBuffer.toString());

            } else {
                return;
            }

        }else if(view.getId() == R.id.Updated){

            Boolean IsUpdated = sqliteHelper.UpdatedData(ataId,name,age,Radiovalue);

            if(IsUpdated == true){
                Toast.makeText(MainActivity.this,  "Data updated successful", Toast.LENGTH_LONG).show();
                Name.setText("");
                Age.setText("");
                id.setText("");
            }else{
                Toast.makeText(MainActivity.this,  "Data not updated", Toast.LENGTH_LONG).show();
            }

        }else if(view.getId() == R.id.Delated){
            int val =  sqliteHelper.Deletedata(ataId);
            if(val > 0){
                Toast.makeText(MainActivity.this,  "Deleted successfull", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this,  "Can not Deleted", Toast.LENGTH_LONG).show();
            }

        }

    }

    public void ShowAllData(String title, String result){ //show all data

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(result);
        builder.setCancelable(true);
        builder.show();

    }
}