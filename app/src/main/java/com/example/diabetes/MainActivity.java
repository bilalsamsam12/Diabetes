package com.example.diabetes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String fullname = "com.asmaebaala.myglysaliva.fullname";
    EditText full, password;
    Button btn_login;
    TextView txt_ins;
    TextView pass;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        full = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        txt_ins = (TextView) findViewById(R.id.tvSignUp);
        databaseHelper = new DatabaseHelper(this);
        btn_login = findViewById(R.id.buttonLogin);
        pass = findViewById(R.id.tvForgotPw);
        pass.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        txt_ins.setOnClickListener(this);


    }

    public void onClick(View v) {
        if (v.getId() == btn_login.getId()) {
            String fulll = full.getText().toString();
            String paww = password.getText().toString();

            if (fulll.equals("") || paww.equals("")){
                gotomaina(v);
            }else {
                Cursor cur = databaseHelper.select();
                while(cur.moveToNext()){
                    if (cur.getString(0).equals(fulll) && cur.getString(3).equals(paww)) {
                        Intent intent = new Intent(this, home.class);
                        intent.putExtra(fullname, fulll);
                        startActivity(intent);
                        break;

                    }else{
                        Toast.makeText(this, "le patient pas existe !!", Toast.LENGTH_SHORT).show();

                    }

                }
            }

        }

        if (v.getId() == txt_ins.getId()) {
            Intent intent = new Intent(this, sign_up.class);
            startActivity(intent);
        }
        if (v.getId() == pass.getId()) {
            Intent intent = new Intent(this, password.class);

            startActivity(intent);
        }
    }
    private boolean validatefull() {
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
        String pas = password.getText().toString().trim();
        if (pas.isEmpty()) {
            password.setError("Required");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
    public boolean validate(View v) {

        if (validatefull() | validatepass() | validatepass() ) {
            return true;

        }
        return false;
    }
    public void gotomaina(View view) {
        validate(view);
    }
}



