package com.nishy.hp.styleomega;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    MyDBhelper dBhelper;
    EditText name, password,email,phone_num,id;
    Button reg, delete, update,load;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        dBhelper = new MyDBhelper(RegisterPage.this);
        init();

    }

    private void init()  {
        id= findViewById(R.id.id1);
        load= findViewById(R.id.load);
        delete= findViewById(R.id.delete);
        update=findViewById(R.id.update);
        name= findViewById(R.id.name);
        password = findViewById(R.id.password);
        email= findViewById(R.id.email);
        textView =findViewById(R.id.textview);
        phone_num = findViewById(R.id.Phonenum);
        reg = findViewById(R.id.register);
        reg.setOnClickListener(dbButtonsListner);
        update.setOnClickListener(dbButtonsListner);
        load.setOnClickListener(dbButtonsListner);
        delete.setOnClickListener(dbButtonsListner);

    }
    private View.OnClickListener dbButtonsListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.register: long resultInsert =  dBhelper.insert(Integer.parseInt(getValue(id)),getValue(name),getValue(password),getValue(email),getValue(phone_num));
                   if(resultInsert == -1){
                       Toast.makeText(RegisterPage.this, "ERROR", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       Toast.makeText(RegisterPage.this, "customer  registered suceesfully " +resultInsert, Toast.LENGTH_SHORT).show();
                   }
                    break;
                case R.id.update:
                    long resultUpdate=  dBhelper.update(Integer.parseInt(getValue(id)),getValue(name),getValue(password),getValue(email),getValue(phone_num));
                    if(resultUpdate == 0){
                        Toast.makeText(RegisterPage.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }

                    else if(resultUpdate==1) {
                        Toast.makeText(RegisterPage.this, "Custmer details updated suceesfully ", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(RegisterPage.this, "multiple records  updated: error", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.delete:
                    long resultDelete =  dBhelper.delete(Integer.parseInt(getValue(id)));
                    if(resultDelete == 0){
                        Toast.makeText(RegisterPage.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterPage.this, "Customer deleted suceesfully " , Toast.LENGTH_SHORT).show();
                    }

                    break;
                case  R.id.load:
                    Cursor cursor = dBhelper.getAllRecords();
                    StringBuffer finalData = new StringBuffer();
                    for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        finalData.append(cursor.getInt(cursor.getColumnIndex(MyDBhelper.ID)));
                        finalData.append("-");
                        finalData.append(cursor.getString(cursor.getColumnIndex(MyDBhelper.NAME)));
                        finalData.append("-");
                        finalData.append(cursor.getString(cursor.getColumnIndex(MyDBhelper.PASSWORD)));
                        finalData.append("-");
                        finalData.append(cursor.getString(cursor.getColumnIndex(MyDBhelper.EMAIL)));
                        finalData.append("-");
                        finalData.append(cursor.getInt(cursor.getColumnIndex(MyDBhelper.PHONE_NUM)));
                        finalData.append("\n");
                    }

                        textView.setText(finalData);

                        break;







            }

        }
    };
    public String getValue(EditText editText){
        return editText.getText().toString().trim();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dBhelper.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dBhelper.closeDB();
    }
}
