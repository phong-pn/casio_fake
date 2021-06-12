package com.example.casiophake.RoomDatabase;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.casiophake.Model.Model;

import java.util.List;

public class Repository {
    public DAO dao;

    public Repository(Context context) {
        Database db = Database.getInstance(context);
        dao= db.dao();
    }

    public LiveData<List<Model>> getAllModels(){
        return dao.getAllModels();
    }

    public void insertModel(Model model){
        Database.executorService.execute(()->dao.insertModel(model));
    }

}
