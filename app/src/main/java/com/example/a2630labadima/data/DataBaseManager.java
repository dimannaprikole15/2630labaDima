package com.example.a2630labadima.data;

import static com.example.a2630labadima.data.DataBaseConst.VAKANSIYA_ADDRESS;
import static com.example.a2630labadima.data.DataBaseConst.VAKANSIYA_ID;
import static com.example.a2630labadima.data.DataBaseConst.VAKANSIYA_NAME;
import static com.example.a2630labadima.data.DataBaseConst.VAKANSIYA_NUM_TEL;
import static com.example.a2630labadima.data.DataBaseConst.VAKANSIYA_SALARY;
import static com.example.a2630labadima.data.DataBaseConst.VAKANSIYA_TABLE_NAME;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private Context context;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context){
        this.context = context;
        dbHelper = new DataBaseHelper(this.context);
    }

    public void openDB(){
        db = dbHelper.getWritableDatabase();
    }

    public void closeDB(){
        db.close();
    }

    @SuppressLint("Range")
    public List<Vakansiya> getAllVakans(){
        List<Vakansiya> vakansiyas = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + VAKANSIYA_TABLE_NAME, null);
        while (cursor.moveToNext()){
            Vakansiya vakansiya = new Vakansiya();
            vakansiya.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(VAKANSIYA_ID))));
            vakansiya.setName(cursor.getString(cursor.getColumnIndex(VAKANSIYA_NAME)));
            vakansiya.setAddress(cursor.getString(cursor.getColumnIndex(VAKANSIYA_ADDRESS)));
            vakansiya.setSalary(Integer.parseInt(cursor.getString(cursor.getColumnIndex(VAKANSIYA_SALARY))));
            vakansiya.setNumTel(cursor.getString(cursor.getColumnIndex(VAKANSIYA_NUM_TEL)));
            vakansiyas.add(vakansiya);
        }
        cursor.close();
        return vakansiyas;
    }

    public void addNewVak(Vakansiya vakansiya){
        ContentValues cv = new ContentValues();
        cv.put(VAKANSIYA_ID, vakansiya.getId());
        cv.put(VAKANSIYA_NAME, vakansiya.getName());
        cv.put(VAKANSIYA_ADDRESS, vakansiya.getAddress());
        cv.put(VAKANSIYA_SALARY, vakansiya.getSalary());
        cv.put(VAKANSIYA_NUM_TEL, vakansiya.getNumTel());
        db.insert(VAKANSIYA_TABLE_NAME, null, cv);
    }

    public void updateVak(Vakansiya vakansiya){
        ContentValues cv = new ContentValues();
        cv.put(VAKANSIYA_ID, vakansiya.getId());
        cv.put(VAKANSIYA_NAME, vakansiya.getName());
        cv.put(VAKANSIYA_ADDRESS, vakansiya.getAddress());
        cv.put(VAKANSIYA_SALARY, vakansiya.getSalary());
        cv.put(VAKANSIYA_NUM_TEL, vakansiya.getNumTel());
        db.update(VAKANSIYA_TABLE_NAME, cv, VAKANSIYA_ID + " = " + vakansiya.getId(), null);
    }

    public void deleteVak(Vakansiya vakansiya){
        db.delete(VAKANSIYA_TABLE_NAME, VAKANSIYA_ID + " = " + vakansiya.getId(), null);
    }
}
