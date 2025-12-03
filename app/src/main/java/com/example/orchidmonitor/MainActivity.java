package com.example.orchidmonitor;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private CardView btnSensor;
    private CardView btnCalendario;
    private CardView btnHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSensor = findViewById(R.id.btnSensor);
        btnCalendario = findViewById(R.id.btnCalendario);
        btnHistorial = findViewById(R.id.btnHistorial);


        btnSensor.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, SensorActivity.class);
            startActivity(i);
        });


        btnCalendario.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(i);
        });


        btnHistorial.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(i);
        });
    }
}



