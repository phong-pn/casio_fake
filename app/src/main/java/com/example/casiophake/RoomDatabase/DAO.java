package com.example.casiophake.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.casiophake.Model.Model;

import java.util.List;

@Dao
interface DAO {
    @Query("SELECT * FROM model_table")
    LiveData<List<Model>> getAllModels();

    @Insert
    void insertModel(Model model);


}
