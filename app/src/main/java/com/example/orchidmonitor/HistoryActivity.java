package com.example.orchidmonitor;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private ReadingAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recycler = findViewById(R.id.recyclerHistorial);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        db = AppDatabase.getInstance(this);

        cargarHistorial();
    }

    private void cargarHistorial() {

        List<ReadingEntity> lista = db.readingDao().getAllReadings();

        if (lista == null || lista.isEmpty()) {
            Log.e("HISTORIAL", "No hay registros en la base de datos");
        } else {
            Log.e("HISTORIAL", "Registros encontrados: " + lista.size());
        }

        adapter = new ReadingAdapter(lista);
        recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
