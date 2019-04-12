package com.example.broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_sms extends AppCompatActivity {

    EditText edittextnumber;
    EditText edittextcontents;
    EditText edittextdate;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        edittextcontents=(EditText)findViewById(R.id.editTextcontents);
        edittextnumber =(EditText)findViewById(R.id.editTextnumber);
        edittextdate=(EditText)findViewById(R.id.editTexttime);
        button=(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent passedIntent= getIntent();
        processCommand(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }

    private void processCommand(Intent intent){
        if(intent !=null){
            String sender = intent.getStringExtra("sender");
            String contents = intent.getStringExtra("contents");
            String receivedDate = intent.getStringExtra("receivedDate");

            edittextcontents.setText(contents);
            edittextdate.setText(receivedDate);
            edittextnumber.setText(sender);
        }
    }
}
