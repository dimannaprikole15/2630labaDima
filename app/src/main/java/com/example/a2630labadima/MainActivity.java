package com.example.a2630labadima;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a2630labadima.data.DataBaseManager;
import com.example.a2630labadima.data.Vakansiya;
import com.example.a2630labadima.data.VakansiyaAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button add;
    RecyclerView recView;
    DataBaseManager dataBaseManager;
    List<Vakansiya> vakansiyas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.addNew);
        recView = findViewById(R.id.recyclerView);
        dataBaseManager = new DataBaseManager(this);
        dataBaseManager.openDB();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(linearLayoutManager);
        vakansiyas = dataBaseManager.getAllVakans();
        if (vakansiyas.isEmpty()){
            Toast.makeText(MainActivity.this, "Вакансий нет!", Toast.LENGTH_SHORT).show();
            recView.setVisibility(View.GONE);
        }
        else{
            recView.setVisibility(View.VISIBLE);
            VakansiyaAdapter adapter = new VakansiyaAdapter(vakansiyas, this, dataBaseManager);
            recView.setAdapter(adapter);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(MainActivity.this, AddNewActivity.class);
                startActivity(addIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDB();
    }
}