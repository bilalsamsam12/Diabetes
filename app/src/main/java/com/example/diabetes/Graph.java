package com.example.diabetes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Graph extends AppCompatActivity {
    GraphView graphView;
    SQLiteDatabase sqLiteDatabase;
    LineGraphSeries<DataPoint> series=new LineGraphSeries<>(new DataPoint[0]);
    DatabaseHelper databaseHelper;

    String d2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        graphView=findViewById(R.id.graph);
        databaseHelper=new DatabaseHelper(this);
        sqLiteDatabase=databaseHelper.getWritableDatabase();
        graphView.addSeries(series);

        exinsert();
    }

    private void exinsert() {

         series.resetData(getDataPoint());
         graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){

         });
    }

    private DataPoint[] getDataPoint() {
        Intent intent= getIntent();
        d2=intent.getStringExtra(test_manuel.d);

        Cursor cur=databaseHelper.selecthtest(d2);
        DataPoint[]dp=new DataPoint[cur.getCount()];
        for(int i=0;i<cur.getCount();i++){
            cur.moveToNext();
            dp[i]=new DataPoint(cur.getInt(0),cur.getDouble(3));
        }
        return dp;
    }
}
