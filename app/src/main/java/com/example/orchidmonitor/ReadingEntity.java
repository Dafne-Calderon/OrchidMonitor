package com.example.orchidmonitor;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "readings")
public class ReadingEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "fecha")
    public String fecha;

    @ColumnInfo(name = "humedad")
    public int humedad;

    @ColumnInfo(name = "temperatura")
    public double temperatura;

    public ReadingEntity(String fecha, int humedad, double temperatura) {
        this.fecha = fecha;
        this.humedad = humedad;
        this.temperatura = temperatura;
    }
}
