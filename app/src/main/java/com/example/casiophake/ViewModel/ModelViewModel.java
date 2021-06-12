package com.example.casiophake.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.casiophake.Model.Model;
import com.example.casiophake.RoomDatabase.Repository;

import java.util.List;

public class ModelViewModel extends AndroidViewModel {
    private static Repository repository;
    public static LiveData<List<Model>> allModels;

    public ModelViewModel(@NonNull @org.jetbrains.annotations.NotNull Application application) {
        super(application);
        repository = new Repository(application);
        allModels = repository.getAllModels();
    }

    public static void insertModel(Model model){
        repository.insertModel(model);
    }
}
