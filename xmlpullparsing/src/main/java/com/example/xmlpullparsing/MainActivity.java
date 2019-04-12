package com.example.xmlpullparsing;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        ArrayList<Student> list = xmlParsing();
        Log.d("parser","list모두 생성 완료/파싱완료!!!!!!!");
        String[] data = new String[list.size()];

        for(int i = 0; i < list.size(); i++){
            data[i] = list.get(i).getName() +" "
                    + list.get(i).getAge() + " "
                    + list.get(i).getAddress();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
    }

    private ArrayList<Student> xmlParsing() {
        ArrayList<Student> allList = new ArrayList<Student>();

        try {

            AssetManager am = getResources().getAssets();
            InputStream is =null;

            is=am.open("file.xml");
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            parser.setInput(is,"UTF-8");
            Log.d("parser","데이터읽기 성공");

            int eventType = parser.getEventType();
            Student student = null;

            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String startTag = parser.getName();

                        if(startTag.equals("student")){
                            student = new Student();
                            Log.d("parser","파서 첫 객체생성");
                        }
                        if (startTag.equals("name")){
                            student.setName(parser.nextText());
                        }
                        if (startTag.equals("age")){
                            student.setAge(parser.nextText());
                        }
                        if (startTag.equals("address")){
                            student.setAddress(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = parser.getName();
                        if(endTag.equals("student")){
                            allList.add(student);
                        }
                        break;
                }

                // 다음 엘리먼트 읽기
                eventType = parser.next();
            }

        } catch (Exception e) {
            Log.e("MyTag", e.toString());
        }
        return allList;
    }


}

