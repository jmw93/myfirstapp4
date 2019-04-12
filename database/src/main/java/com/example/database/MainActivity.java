package com.example.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edittext1;
    EditText edittext2;
    EditText edittext3;
    EditText edittext4;
    EditText edittext5;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView textView1;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);
        edittext1 = (EditText) findViewById(R.id.editText1);
        edittext2 = (EditText) findViewById(R.id.edittext2);
        edittext3 = (EditText) findViewById(R.id.edittext3);
        edittext4 = (EditText) findViewById(R.id.edittext4);
        edittext5 = (EditText) findViewById(R.id.edittext5);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databaseName = edittext1.getText().toString();
                openDatabase(databaseName);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName = edittext2.getText().toString();
                createTable(tableName);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edittext3.getText().toString().trim();
                String ageStr = edittext4.getText().toString().trim();
                String mobile = edittext5.getText().toString().trim();
                String tableName = edittext2.getText().toString();
                int age= -1;
                try {
                    age = Integer.parseInt(ageStr);
                } catch(Exception e){}
                insertData(tableName,name,age,mobile);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName = edittext2.getText().toString();
                selectData(tableName);

            }
        });
    }

    public void openDatabase(String databaseName) {
        println("openDatabase()호출됨");
        database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
        if (database != null) {
            println("database 오픈됨");
        }
    }

    public void println(String data) {
        textView1.append(data + "\n");
    }


    public void createTable(String tableName) {
        println("createTable() 호출됨.");
       if(database != null){
        String sql ="create table " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)";

        database.execSQL(sql);
        println("테이블 생성됨");
       }else{
           println("테이블을 생성하세요");
       }
    }

    public void insertData(String tableName,String name, int age,String mobile){
        println("insertData() 호출됨.");
        if(database !=null) {
            String sql = "insert into customer(name,age,mobile) values(?, ?, ?)";
            Object[] params = {name, age, mobile};

            database.execSQL(sql);
            println("데이터 추가함.");
        }else{
            println("먼저 DB를 오픈하세요");
        }
    }

    public void selectData(String tableName){
        println("selectData()호출됨");
        if(database !=null){
            String sql = "select name, age, mobile from " + tableName;
            Cursor cursor = database.rawQuery(sql,null);
           println("조회된 데이터갯수:"+cursor.getCount());

           for(int i=0; i<cursor.getCount(); i++){
               cursor.moveToNext();
               String name =cursor.getString(0);
               int age = cursor.getInt(1);
               String mobile = cursor.getString(2);

               println("# " + i + "->  " + name + ", " + age + ", " + mobile);

           }
            cursor.close();
        }
    }
}