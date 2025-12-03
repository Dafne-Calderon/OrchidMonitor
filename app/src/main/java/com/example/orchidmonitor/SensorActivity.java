package com.example.orchidmonitor;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;


public class SensorActivity extends AppCompatActivity {

    private static final String TAG = "SensorActivity";

    // API
    private static final String API_URL =
            "https://api.open-meteo.com/v1/forecast?latitude=-33.45&longitude=-70.66&current=temperature_2m,relative_humidity_2m";


    //  DECLARACIÓN DE VARIABLES
    private TextView txtEstadoConexion;
    private TextView txtHumedad;
    private TextView txtTemperatura;
    private Button btnConectarSensor;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);


        txtEstadoConexion = findViewById(R.id.txtEstadoConexion);
        txtHumedad = findViewById(R.id.txtHumedad);
        txtTemperatura = findViewById(R.id.txtTemperatura);
        btnConectarSensor = findViewById(R.id.btnConectarSensor);

        //  INICIALIZAR BASE DE DATOS
        db = AppDatabase.getInstance(this);

        //  TEXTO INICIAL
        txtEstadoConexion.setText("Estado: Esperando datos...");
        btnConectarSensor.setText("Obtener datos desde API");

        //  BOTÓN PARA CONSULTAR API
        btnConectarSensor.setOnClickListener(v -> obtenerDatosDesdeAPI());
    }

    private void obtenerDatosDesdeAPI() {

        txtEstadoConexion.setText("Estado: Consultando API...");
        txtHumedad.setText("Humedad: --");
        txtTemperatura.setText("Temperatura: --");

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                response -> {
                    try {
                        JSONObject current = response.getJSONObject("current");

                        double temperatura = current.getDouble("temperature_2m");
                        int humedad = current.getInt("relative_humidity_2m");


                        txtEstadoConexion.setText("Estado: Datos recibidos ✔");
                        txtHumedad.setText("Humedad: " + humedad + " %");
                        txtTemperatura.setText("Temperatura: " + temperatura + " °C");


                        // 2) GUARDAR EN BASE DE DATOS
                        String fecha = new java.text.SimpleDateFormat(
                                "dd-MM-yyyy HH:mm",
                                java.util.Locale.getDefault()
                        ).format(new java.util.Date());

                        ReadingEntity lectura = new ReadingEntity(
                                fecha,
                                humedad,
                                temperatura
                        );

                        db.readingDao().insert(lectura);

                    } catch (Exception e) {
                        txtEstadoConexion.setText("Estado: Error procesando datos");
                    }
                },
                error -> txtEstadoConexion.setText("Error al conectar con la API")
        );

        queue.add(request);
    }

}