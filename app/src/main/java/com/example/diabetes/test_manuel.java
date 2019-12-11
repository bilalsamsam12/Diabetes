package com.example.diabetes;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class test_manuel extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    Button btn_sbm, bshaw_grave, bsend;
    EditText taux;
    RadioGroup g1, g2;
    RadioButton t1, t2, f1, f2;
    String f;
    public final static String d = "com.asmaebaala.myglysaliva.d";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_manuel);
        btn_sbm = findViewById(R.id.testma);
        bsend = findViewById(R.id.send);
        bshaw_grave = findViewById(R.id.show_grave);
        taux = findViewById(R.id.tx);
        t1 = findViewById(R.id.tp1);
        t2 = findViewById(R.id.tp2);
        g1 = findViewById(R.id.g1);
        g2 = findViewById(R.id.g2);
        f1 = findViewById(R.id.fy);
        f2 = findViewById(R.id.fn);
        btn_sbm.setOnClickListener(this);
        bsend.setOnClickListener(this);
        bshaw_grave.setOnClickListener(this);
        Intent intent = getIntent();
        f = intent.getStringExtra(home.name);
        getSupportActionBar().setTitle("Test Manuel");


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == bshaw_grave.getId()) {

            Intent intent = new Intent(this, Graph.class);
            intent.putExtra(d, f);
            startActivity(intent);
        }
//------------------------------------------------------------------------------------------------------------------------------------
        if (v.getId() == bsend.getId()) {

            Cursor curr = databaseHelper.selecthtest(f);
            if (curr.getCount() > 0) {
                if (curr.move(curr.getCount())) {
                }
                int type = curr.getInt(2);
                double value = curr.getDouble(3);
                String dat = curr.getString(4);
                String ms = curr.getString(5);
                String fas = curr.getString(6);

                try {
                    Intent emailintent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                    emailintent.setType("text/plain");
                    emailintent.putExtra(Intent.EXTRA_EMAIL, "ggggg");
                    emailintent.putExtra(Intent.EXTRA_SUBJECT, "Rsultat de test de cette jour");
                    emailintent.putExtra(Intent.EXTRA_TEXT, "Bonjour docteur,\n\n Voici mon test d'aujourd'hui :\n\n" + "Nom : " + f + "\n" + "Date de test: " + dat + "\n" + "Type : " + type + "\n" + "Diabetes valuer : " + value + " mlg" + "\n" + "Fast : " + fas + "\n" + "Message de test est : " + ms);
                    startActivity(emailintent);
                    Toast.makeText(this, "message is sending...", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(this, "message not send ", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "error: accer internet! installes/activer Email§", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "aucun test", Toast.LENGTH_SHORT).show();
            }


        }

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------
        if (v.getId() == btn_sbm.getId()) {
            if (taux.getText().toString().equals("")) {
                Toast.makeText(this, "Entre la valeur like 00.0", Toast.LENGTH_SHORT).show();
            } else {

                double t = Double.parseDouble(taux.getText().toString());
                inscription i = new inscription();
                if (t1.isChecked() && f1.isChecked()) {
                    if (t >= 70 && t < 120) {
                        String mess = "le diabite est : Normal";
                        AlertTest alertTest = new AlertTest(mess);
                        alertTest.show(getSupportFragmentManager(), "this");
                        i.setType(1);
                        i.setMessage(mess);
                        i.setFullname(f);
                        i.setValeur(t);
                        i.setFast("yes");
                        databaseHelper.innserthistorique(i);
                        Cursor cur = databaseHelper.selecthtest(f);
                        while (cur.moveToNext()) {
                            Log.e("log", "id : " + cur.getInt(0));
                            Log.e("log", "full : " + cur.getString(1));
                            Log.e("log", "type : " + cur.getInt(2));
                            Log.e("log", "value : " + cur.getDouble(3));
                            Log.e("log", "date : " + cur.getString(4));
                            Log.e("log", "message : " + cur.getString(5));
                            Log.e("log", "faste : " + cur.getString(6));
                        }


                    } else if (t < 70) {
                        Intent intent = getIntent();
                        f = intent.getStringExtra(home.name);
                        String mess = " Hyperglycemie";
                        AlertTest alertTest = new AlertTest(mess);
                        alertTest.show(getSupportFragmentManager(), "this");
                        i.setType(1);
                        i.setMessage(mess);
                        i.setFullname(f);
                        i.setValeur(t);
                        i.setFast("yes");
                        databaseHelper.innserthistorique(i);
                    } else {
                        Intent intent = getIntent();
                        f = intent.getStringExtra(home.name);
                        String mess = " Hypoglycemie";
                        AlertTest alertTest = new AlertTest(mess);
                        alertTest.show(getSupportFragmentManager(), "this");
                        i.setType(1);
                        i.setMessage(mess);
                        i.setFullname(f);
                        i.setValeur(t);
                        i.setFast("yes");
                        databaseHelper.innserthistorique(i);

                    }
                } else if (t1.isChecked() && f2.isChecked()) {
                    if (t < 160) {
                        Intent intent = getIntent();
                        f = intent.getStringExtra(home.name);
                        String mess = " Normal";
                        AlertTest alertTest = new AlertTest(mess);
                        alertTest.show(getSupportFragmentManager(), "this");
                        i.setType(1);
                        i.setMessage(mess);
                        i.setFullname(f);
                        i.setValeur(t);
                        i.setFast("non");
                        databaseHelper.innserthistorique(i);
                    } else if (t >= 160) {
                        Intent intent = getIntent();
                        f = intent.getStringExtra(home.name);
                        String mess = " Hypoglycemie";
                        AlertTest alertTest = new AlertTest(mess);
                        alertTest.show(getSupportFragmentManager(), "this");
                        i.setType(1);
                        i.setMessage(mess);
                        i.setFullname(f);
                        i.setValeur(t);
                        i.setFast("non");
                        databaseHelper.innserthistorique(i);
                    }
                } else if (t2.isChecked() && f1.isChecked()) {
                    if (t >= 70 && t < 120) {
                        Intent intent = getIntent();
                        f = intent.getStringExtra(home.name);
                        String mess = " Normal";
                        AlertTest alertTest = new AlertTest(mess);
                        alertTest.show(getSupportFragmentManager(), "this");
                        i.setType(2);
                        i.setMessage(mess);
                        i.setFullname(f);
                        i.setValeur(t);
                        i.setFast("yes");
                        databaseHelper.innserthistorique(i);
                    } else if (t < 70) {
                        Intent intent = getIntent();
                        f = intent.getStringExtra(home.name);
                        String mess = " Hyperglycemie";
                        AlertTest alertTest = new AlertTest(mess);
                        alertTest.show(getSupportFragmentManager(), "this");
                        i.setType(2);
                        i.setMessage(mess);
                        i.setFullname(f);
                        i.setValeur(t);
                        i.setFast("yes");
                        databaseHelper.innserthistorique(i);
                    } else {
                        Intent intent = getIntent();
                        f = intent.getStringExtra(home.name);
                        String mess = " Hypoglycemie";
                        AlertTest alertTest = new AlertTest(mess);
                        alertTest.show(getSupportFragmentManager(), "this");
                        i.setType(2);
                        i.setMessage(mess);
                        i.setFullname(f);
                        i.setValeur(t);
                        i.setFast("yes");
                        databaseHelper.innserthistorique(i);
                    }
                } else if (t2.isChecked() && f2.isChecked()) {
                    if (t < 180) {
                        Intent intent = getIntent();
                        f = intent.getStringExtra(home.name);
                        String mess = " Normal";
                        AlertTest alertTest = new AlertTest(mess);
                        alertTest.show(getSupportFragmentManager(), "this");
                        i.setType(2);
                        i.setMessage(mess);
                        i.setFullname(f);
                        i.setValeur(t);
                        i.setFast("non");
                        databaseHelper.innserthistorique(i);
                    } else if (t >= 180) {
                        Intent intent = getIntent();
                        f = intent.getStringExtra(home.name);
                        String mess = " Hypoglycemie";
                        AlertTest alertTest = new AlertTest(mess);
                        alertTest.show(getSupportFragmentManager(), "this");
                        i.setType(2);
                        i.setMessage(mess);
                        i.setFullname(f);
                        i.setValeur(t);
                        i.setFast("non");
                        databaseHelper.innserthistorique(i);
                    }
                }
            }
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.infoo) {
            Intent intent = new Intent(this, type1.class);
            startActivity(intent);
        }
        return true;
    }
}
