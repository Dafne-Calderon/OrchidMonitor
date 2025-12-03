package com.example.orchidmonitor;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Button btnGuardar;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        timePicker = findViewById(R.id.timePickerRiego);
        btnGuardar = findViewById(R.id.btnGuardarRiego);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        btnGuardar.setOnClickListener(v -> programarAlarma());
    }

    private void programarAlarma() {
        int hour;
        int minute;

        if (Build.VERSION.SDK_INT >= 23) {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        } else {
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
        }

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        if (c.getTimeInMillis() < System.currentTimeMillis()) {
            c.add(Calendar.DAY_OF_YEAR, 1);
        }

        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                c.getTimeInMillis(),
                pendingIntent
        );

        Toast.makeText(this, "Recordatorio de riego programado", Toast.LENGTH_SHORT).show();
    }
}
