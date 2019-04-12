package com.example.app5;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       int permissionCheck = ContextCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS);
       if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
           Toast.makeText(this, "SMS 수신권한 주어져 있음", Toast.LENGTH_LONG).show();
       } else{
           Toast.makeText(this, "SMS 수신권한 없음", Toast.LENGTH_LONG).show();
           if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.RECEIVE_SMS)){
               Toast.makeText(this, "SMS 권한 설명 필요함", Toast.LENGTH_LONG).show();
       }else{
               ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.RECEIVE_SMS},1);
           }// 시스템에게 권한요청상자 요청
       }

    }

    @Override
    //권한 부여됐는지 알수잇음
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length>0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "SMS 수신권한을 사용자가 승인함", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this, "SMS  수신권한을 거부함", Toast.LENGTH_LONG).show();
                }


        }
    }
}
