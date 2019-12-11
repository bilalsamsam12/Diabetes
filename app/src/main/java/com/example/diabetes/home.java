package com.example.diabetes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class home extends AppCompatActivity {
    public final static  String name="com.asmaebaala.myglysaliva.name";
    Button btn_testm,btn_list;
    String n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_testm = (Button)findViewById(R.id.testm);
        btn_list = (Button)findViewById(R.id.listtest);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        Intent intent=getIntent();
        n=intent.getStringExtra(MainActivity.fullname);
        btn_testm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gototestm(view);
            }
        });
        btn_list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), List.class);
                intent.putExtra(name,n);
                startActivity(intent);
            }
        });
    }

    public void gototestm(View view)
    {
        Intent intent = new Intent(this, test_manuel.class);
        intent.putExtra(name,n);
        startActivity(intent);

        }

}
