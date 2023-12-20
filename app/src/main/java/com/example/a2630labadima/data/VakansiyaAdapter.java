package com.example.a2630labadima.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2630labadima.R;
import com.example.a2630labadima.UpdateActivity;

import java.util.List;

public class VakansiyaAdapter extends RecyclerView.Adapter<VakansiyaAdapter.ViewHolder>{
    LayoutInflater inflater;
    List<Vakansiya> vakansiyas;
    DataBaseManager dataBaseManager;

    public VakansiyaAdapter(List<Vakansiya> vakansiyas, Context context, DataBaseManager dataBaseManager){
        this.vakansiyas = vakansiyas;
        this.inflater = LayoutInflater.from(context);
        this.dataBaseManager = dataBaseManager;
    }

    @NonNull
    @Override
    public VakansiyaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.vakansiya_list, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VakansiyaAdapter.ViewHolder holder, int position) {
        Vakansiya vakansiya = vakansiyas.get(position);
        holder.name.setText("Название: " + vakansiya.getName());
        holder.address.setText("Адрес: " + vakansiya.getAddress());
        holder.salary.setText("Зарплата: " + vakansiya.getSalary() + " ₽");
        holder.numTel.setText("Время: " + vakansiya.getNumTel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(inflater.getContext());
                builder.setTitle("Выберите действие");
                // Кнопка редактировать вакансию
                builder.setPositiveButton("Редактировать", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent editIntent = new Intent(v.getContext(), UpdateActivity.class);
                        editIntent.putExtra(Vakansiya.class.getSimpleName(), vakansiya);
                        v.getContext().startActivity(editIntent);
                    }
                });
                // Кнопка удалить вакансию
                builder.setNegativeButton("Удалить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dataBaseManager.openDB();
                        dataBaseManager.deleteVak(vakansiya);
                        dataBaseManager.closeDB();
                    }
                });
                // Кнопка отмена
                builder.setNeutralButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return vakansiyas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, address, salary, numTel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameVak);
            address = itemView.findViewById(R.id.addressVak);
            salary = itemView.findViewById(R.id.salaryVak);
            numTel = itemView.findViewById(R.id.numTelVak);
        }
    }
}
