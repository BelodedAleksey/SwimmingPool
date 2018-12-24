package com.alexey.beloded.swimmingpool;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SeanceDao {

    @Insert
    void insert(Seance seance);

    @Update
    void update(Seance seance);

    @Delete
    void delete(Seance seance);

    @Query("DELETE FROM seance_table")
    void deleteAllSeances();

    @Query("SELECT * FROM seance_table ORDER BY id DESC")
    LiveData<List<Seance>> getAllSeances();

    @Query("SELECT * FROM seance_table WHERE day =:day ORDER BY time ASC")
    LiveData<List<Seance>> getSeancesByDay(int day);

    @Query("SELECT time FROM seance_table WHERE day =:day ORDER BY time ASC")
    int[] getTimesByDay(int day);
}