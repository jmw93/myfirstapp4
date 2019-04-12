package com.example.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button button2;
    TextView textView;
    ValueHandler handler= new ValueHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         button=(Button)findViewById(R.id.button);
         textView=(TextView)findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });

    }

    class BackgroundThread extends Thread{
        int value=0;
        boolean running= false;
        public void run(){
            running=true;
            while(running){
                value+=1;
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value",value);
                message.setData(bundle);
                handler.sendMessage(message);
                try {
                 Thread.sleep(1000);
                }catch(Exception e){}
                }
        }
    }

    class ValueHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
          int value= bundle.getInt("value");
          textView.setText("현재 값:"+value);
            super.handleMessage(msg); //콜백메소드
        }
    }

}
