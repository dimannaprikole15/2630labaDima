package com.example.a2630labadima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2630labadima.data.DataBaseManager;
import com.example.a2630labadima.data.Vakansiya;

public class UpdateActivity extends AppCompatActivity {

    EditText name, address, salary, numTel;
    Button save;
    DataBaseManager dataBaseManager;
    Vakansiya updateVakansiya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name = findViewById(R.id.updateName);
        address = findViewById(R.id.updateAddress);
        salary = findViewById(R.id.updateSalary);
        numTel = findViewById(R.id.updateNumTel);
        save = findViewById(R.id.saveUpdate);
        dataBaseManager = new DataBaseManager(this);
        dataBaseManager.openDB();
        Bundle items = getIntent().getExtras();
        if (items != null){
            updateVakansiya = (Vakansiya) items.getSerializable(Vakansiya.class.getSimpleName());
            name.setText(updateVakansiya.getName());
            address.setText(updateVakansiya.getAddress());
            salary.setText(String.valueOf(updateVakansiya.getSalary()));
            numTel.setText(updateVakansiya.getNumTel());
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = name.getText().toString();
                String newAddress = address.getText().toString();
                int newSalary = Integer.parseInt(salary.getText().toString());
                String newNumTel = numTel.getText().toString();
                if (newName.equals("") || newAddress.equals("") || newNumTel.equals("") || newSalary == 0){
                    Toast.makeText(UpdateActivity.this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                }
                else {
                    dataBaseManager.openDB();
                    updateVakansiya = new Vakansiya();
                    updateVakansiya.setName(newName);
                    updateVakansiya.setAddress(newAddress);
                    updateVakansiya.setSalary(newSalary);
                    updateVakansiya.setNumTel(newNumTel);
                    dataBaseManager.updateVak(updateVakansiya);
                    Toast.makeText(UpdateActivity.this, "Вакансия изменена", Toast.LENGTH_SHORT).show();
                    dataBaseManager.closeDB();
                }
            }
        });
    }
}