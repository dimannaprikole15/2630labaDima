package com.example.a2630labadima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2630labadima.data.DataBaseManager;
import com.example.a2630labadima.data.Vakansiya;

public class AddNewActivity extends AppCompatActivity {
    EditText name, address, salary, numTel;
    Button save;
    DataBaseManager dataBaseManager;
    Vakansiya newVakansiya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        name = findViewById(R.id.editName);
        address = findViewById(R.id.editAddress);
        salary = findViewById(R.id.editSalary);
        numTel = findViewById(R.id.editNumTel);
        save = findViewById(R.id.saveNew);
        dataBaseManager = new DataBaseManager(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = name.getText().toString();
                String newAddress = address.getText().toString();
                int newSalary = Integer.parseInt(salary.getText().toString());
                String newNumTel = numTel.getText().toString();
                if (newName.equals("") || newAddress.equals("") || newNumTel.equals("") || newSalary == 0){
                    Toast.makeText(AddNewActivity.this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                }
                else {
                    dataBaseManager.openDB();
                    newVakansiya = new Vakansiya();
                    newVakansiya.setName(newName);
                    newVakansiya.setAddress(newAddress);
                    newVakansiya.setSalary(newSalary);
                    newVakansiya.setNumTel(newNumTel);
                    dataBaseManager.addNewVak(newVakansiya);
                    Toast.makeText(AddNewActivity.this, "Вакансия сохранена", Toast.LENGTH_SHORT).show();
                    dataBaseManager.closeDB();
                }
            }
        });
    }
}