package com.example.gson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
       if(AppHelper.requestQueue ==null) {
           AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
       }
    }
    public void sendRequest(){
//        String url = "https://www.google.co.kr";
        String url ="https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101";
            StringRequest request = new StringRequest(
                    Request.Method.GET, // 요청형식을 GET방식으로
                    url, //요청대상
                    new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response) {
                            //정상응답시 호출
                            println("응답 ㅡ>" + response);

                        }

                        //응답을 String객체로 얻는다.
                    },
                    new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {
                        println("에러:"+error.getMessage());
                        }
                    }
            ){
                //post형식으로 요청할시 중괄호

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String,String>();
                    return params;
                }
            };
            //매번 받은결과를 보여주세요 요청코드 바로아래
            request.setShouldCache(false); //이전에 처리한결과를 다시 보여줄수없게함
            AppHelper.requestQueue.add(request);
        }


    public void println(String data){
        textView.append(data+"\n");
    }

}



