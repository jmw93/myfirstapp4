package com.example.myninepatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView=(ListView) findViewById(R.id.listView);

        SingerAdapter adapter = new SingerAdapter();

        adapter.addItem(new SingerItem("소녀시대","010-1000-1000"));
        adapter.addItem(new SingerItem("소녀시대","010-1000-1002"));
        adapter.addItem(new SingerItem("소녀시대","010-1000-1003"));
        adapter.addItem(new SingerItem("소녀시대","010-1000-1004"));
        adapter.addItem(new SingerItem("소녀시대","010-1000-1005"));

        listView.setAdapter(adapter);

    }

    class SingerAdapter extends BaseAdapter{
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        @Override
        public int getCount() {
            return items.size();
        }
        public void addItem(SingerItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingeritemView view = new SingeritemView(getApplicationContext());

            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());


            return view;
        }
    }

}
