package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int REQUEST_CODE= 1000;
    private EditText mAgeEditText;
    private EditText mNameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNameEditText=(EditText)findViewById(R.id.name_edit);
        mAgeEditText=(EditText)findViewById(R.id.age_edit);

        findViewById(R.id.submit_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        SecondActivity 전환예
            Intent intent= new Intent(this, SecondActivity.class);
//        이름,나이 가져와서 인텐트에 추가
            intent.putExtra("name",mNameEditText.getText().toString());
            intent.putExtra("age",mAgeEditText.getText().toString());
            startActivityForResult(intent,REQUEST_CODE);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == REQUEST_CODE
                &&resultCode ==RESULT_OK
                &&data !=null) {
            String result = data.getStringExtra("result");
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }

        }


    }

