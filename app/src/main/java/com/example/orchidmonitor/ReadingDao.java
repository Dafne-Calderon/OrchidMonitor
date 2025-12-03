package com.example.orchidmonitor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReadingDao {

    @Insert
    void insert(ReadingEntity reading);

    @Query("SELECT * FROM readings ORDER BY id DESC")
    List<ReadingEntity> getAllReadings();
}

