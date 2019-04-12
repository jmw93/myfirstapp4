package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG= "SmsReceiver";

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm");
    @Override
    public void onReceive(Context context, Intent intent) {
       Log.d(TAG,"onReceiver() 호출됨.");
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages.length>0){
          String sender=  messages[0].getOriginatingAddress();
          String contents=messages[0].getMessageBody().toString();
          Date receivedDate = new Date(messages[0].getTimestampMillis());

          sendToActivity(context,sender,contents,receivedDate);


        }
    }

    private void sendToActivity(Context context ,String sender, String contents,Date receivedDate){
        Intent intent= new Intent(context,activity_sms.class);
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK|
        intent.FLAG_ACTIVITY_SINGLE_TOP|
        intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sender",sender);
        intent.putExtra("contents",contents);
        intent.putExtra("receivedDate",format.format(receivedDate));
        context.startActivity(intent);
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        //인텐트로 뽑아도되고 번들로뽑아도된다
        Object[] objs=(Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        for(int i=0; i<objs.length;i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            }else{
                    messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
                }
            }
                return messages;
        }
    }

