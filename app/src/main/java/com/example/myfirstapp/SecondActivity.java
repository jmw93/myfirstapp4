package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mMessageTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String age=intent.getStringExtra("age");
        mMessageTextView= (TextView)findViewById(R.id.message_edit_text);
        mMessageTextView.setText(age+"살"+name);
//        버튼이벤트 연결
        findViewById(R.id.result_button).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent =new Intent();
        intent.putExtra("result",mMessageTextView.getText().toString());
//        결과전달
        setResult(RESULT_OK, intent);
        finish();
    }
}
