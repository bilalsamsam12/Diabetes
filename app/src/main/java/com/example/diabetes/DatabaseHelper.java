package com.example.diabetes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    Date date = new Date();
    SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yy");
    String dateh = sf.format(date);

    public DatabaseHelper(Context context) {
        super(context, "d.db", null,1);

    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table patient " + "(  fullnamep text   PRIMARY KEY ,telephone text ,EMAIL text,password text );");
       db.execSQL("create table historique " + "(  idh INTEGER PRIMARY KEY  Autoincrement,fullp text,type number , valeur double ,date text ,message text ,fast text ,FOREIGN KEY (fullp) references patient(fullnamep));");
   }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  patient");
        db.execSQL("DROP TABLE IF EXISTS  historique");
        onCreate(db);

    }

    public boolean innsertpatient(inscription i) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues ligne = new ContentValues();
        ligne.put("fullnamep", i.getFullname());
        ligne.put("telephone", i.getTelephone());
        ligne.put("EMAIL", i.getEmail());
        ligne.put("password", i.getPassword());
        db.insert("patient", null, ligne);
        return true;
    }


    public boolean innserthistorique(inscription i) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues ligne = new ContentValues();
        ligne.put("fullp", i.getFullname());
        ligne.put("valeur", i.getValeur());
        ligne.put("date",dateh );
        ligne.put("type",i.getType());
        ligne.put("message", i.getMessage());
        ligne.put("fast",i.getFast());
        db.insert("historique", null, ligne);
        return true;
    }
    public  Cursor select(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from patient",null);
        return res;
    }

    public  Cursor selectx(String f){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from patient where fullnamep=?",new String []{f});
        return res;
    }

    public  Cursor selecthtest(String full){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from historique where   fullp=?",new String []{full});
        return res;
    }
    public int numberRowh(){
        SQLiteDatabase db=this.getReadableDatabase();
        int numrow=(int) DatabaseUtils.queryNumEntries(db,"historique");
        return  numrow;
    }

    public  boolean updatepas(inscription i){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues ligne=new ContentValues();
        ligne.put("password",i.getPassword() );
        db.update("patient",ligne,"fullnamep=?",new String[]{ i.getFullname()});
        return  true;
    }

    public  Cursor selecthtestx(String full,String date){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from historique where   fullp=? and date=?",new String []{full,date});
        return res;
    }

}

