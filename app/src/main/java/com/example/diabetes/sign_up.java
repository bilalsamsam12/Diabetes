package com.example.diabetes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class sign_up extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText n, t, e, p;
    Button s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        n = (EditText) findViewById(R.id.name);
        t = (EditText) findViewById(R.id.tel);
        e = (EditText) findViewById(R.id.email);
        p = findViewById(R.id.pass);
        getSupportActionBar().setTitle("Inscription");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        s = (Button) findViewById(R.id.submit);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(n.getText().toString().equals("") || t.getText().toString().equals("") || e.getText().toString().equals("") || p.getText().toString().equals("")){
                    gotomaina(view);
                }else{
                    Cursor cur =databaseHelper.selectx(n.getText().toString());
                    if (cur.moveToNext()){
                            Toast.makeText(sign_up.this, "le fullname déja existe ", Toast.LENGTH_SHORT).show();

                    }else{

                        inscription i = new inscription();
                        i.setFullname(n.getText().toString());
                        i.setEmail(e.getText().toString());
                        i.setTelephone(t.getText().toString());
                        i.setPassword(p.getText().toString());

                        databaseHelper.innsertpatient(i);
                        Toast.makeText(sign_up.this, " patient est bien inserer.", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(sign_up.this,MainActivity.class);
                            startActivity(intent);

                    }

                }



            }
        });
        mydb = new DatabaseHelper(this);
    }

    public void gotomaina(View view) {
        validate(view);
    }

    private boolean validateFullname() {
        String fullname = n.getText().toString();
        if (fullname.isEmpty()) {
            n.setError("Required");
            return false;
        } else {
            n.setError(null);
            return true;
        }
    }

    private boolean validateTelephone() {
        String telephone = t.getText().toString().trim();
        if (telephone.isEmpty()) {
            t.setError("Required");
            return false;
        } else {
            t.setError(null);
            return true;
        }
    }



    private boolean validateEmail() {
        String Email = e.getText().toString().trim();
        if (Email.isEmpty()) {
            e.setError("Required");
            return false;
        } else {
            e.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String Password = p.getText().toString().trim();
        if (Password.isEmpty()) {
            p.setError("Required");
            return false;
        } else {
            p.setError(null);
            return true;
        }
    }

    public boolean validate(View v) {

        if (!validateFullname() | !validateTelephone() | !validatePassword() | !validateEmail()) {
            return false;
        }
        return false;
    }

}
