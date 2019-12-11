package com.example.diabetes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class password extends AppCompatActivity {
    EditText full,newpass,confpass;
    Button btn_pass;
    DatabaseHelper databaseHelper;
    inscription i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        full=findViewById(R.id.full);
        newpass=findViewById(R.id.newpas);
        confpass=findViewById(R.id.conpas);
        databaseHelper=new DatabaseHelper(this);
       btn_pass = (Button) findViewById(R.id.pf);
        btn_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ful=full.getText().toString();
                String pas=newpass.getText().toString();
                String con=confpass.getText().toString();
                if(ful.equals("") || pas.equals("") || con.equals("")){
                    gotomaina(view);
                }else {
                    if (pas.equals(con)){
                        Cursor cur= databaseHelper.selectx(ful);
                        if(cur.moveToNext()){
                            i=new inscription();
                            i.setFullname(ful);
                            i.setPassword(pas);
                            databaseHelper.updatepas(i);
                            Toast.makeText(password.this, "pasword is updater", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(password.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(password.this, "ne pas trouver le full name", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(password.this, "dois insere le méme password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private boolean validateFullname() {
        String fullname = full.getText().toString();
        if (fullname.isEmpty()) {
            full.setError("Required");
            return false;
        } else {
            full.setError(null);
            return true;
        }
    }

    private boolean validatepass() {
        String fullname = newpass.getText().toString();
        if (fullname.isEmpty()) {
            newpass.setError("Required");
            return false;
        } else {
            newpass.setError(null);
            return true;
        }
    }

    private boolean validateconfpass() {
        String fullname = confpass.getText().toString();
        if (fullname.isEmpty()) {
            confpass.setError("Required");
            return false;
        } else {
            confpass.setError(null);
            return true;
        }
    }
    public boolean validate(View v) {

        if (validateFullname() | validatepass() | validateconfpass() ) {
            return true;

        }
        return false;
    }
    public void gotomaina(View view) {
        validate(view);
    }
}
