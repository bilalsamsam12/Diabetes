package com.example.diabetes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class List extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    DatabaseHelper databaseHelper;
    ListAdapter listAdapter, l;
    String f;
    TextView textView;
    Button bs;
    Dataprovider dataprovider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        bs = findViewById(R.id.bs);
        textView = findViewById(R.id.ts);
        listView = findViewById(R.id.list_view);
        databaseHelper = new DatabaseHelper(this);
        listAdapter = new ListAdapter(getApplicationContext(), R.layout.row_layout);
        getSupportActionBar().setTitle("List Des Tests");
        listView.setAdapter(listAdapter);
        Intent intent = getIntent();
        f = intent.getStringExtra(home.name);
        bs.setOnClickListener(this);
        Cursor cur = databaseHelper.selecthtest(f);
        while (cur.moveToNext()) {
            int ty = cur.getInt(2);
            double value = cur.getDouble(3);
            String date = cur.getString(4);
            String mess = cur.getString(5);
            String fas = cur.getString(6);
            dataprovider = new Dataprovider(ty, value, date, mess, fas);
            listAdapter.add(dataprovider);

        }


    }


    @Override
    public void onClick(View v) {

        if (v.getId() == bs.getId()) {
            l = new ListAdapter(getApplicationContext(), R.layout.row_layout);
            listView.setAdapter(l);
            String dat = textView.getText().toString();
            Cursor cur = databaseHelper.selecthtestx(f, dat);
            while (cur.moveToNext()) {
                int ty = cur.getInt(2);
                double value = cur.getDouble(3);
                String date = cur.getString(4);
                String mess = cur.getString(5);
                String fas = cur.getString(6);
                Dataprovider dataprovider = new Dataprovider(ty, value, date, mess, fas);
                l.add(dataprovider);

            }
        }

    }
}
