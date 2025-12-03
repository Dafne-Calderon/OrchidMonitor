package com.example.orchidmonitor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.ViewHolder> {

    private List<ReadingEntity> listaLecturas;

    public ReadingAdapter(List<ReadingEntity> listaLecturas) {
        this.listaLecturas = listaLecturas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reading, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReadingEntity r = listaLecturas.get(position);

        holder.txtFecha.setText(r.fecha);

        holder.txtHumedad.setText("Humedad: " + r.humedad + " %");

        holder.txtTemperatura.setText("Temperatura: " + String.format("%.1f", r.temperatura) + " °C");

        Log.d("ADAPTER", "Fecha: " + r.fecha + " | Temp: " + r.temperatura + " | Hum: " + r.humedad);
    }


    @Override
    public int getItemCount() {
        return listaLecturas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtFecha, txtHumedad, txtTemperatura;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFecha = itemView.findViewById(R.id.txtFecha);
            txtHumedad = itemView.findViewById(R.id.txtHumedad);
            txtTemperatura = itemView.findViewById(R.id.txtTemperatura);
        }
    }
}
