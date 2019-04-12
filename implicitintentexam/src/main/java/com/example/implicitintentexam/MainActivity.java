package com.example.implicitintentexam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void dialPhone(View view){
        EditText editText =(EditText)findViewById(R.id.phone_number_edit);
        dialPhoneNumber(editText.getText().toString());
    }

    public void dialPhoneNumber(String phoneNumber ){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phoneNumber));

        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }

    }
}
