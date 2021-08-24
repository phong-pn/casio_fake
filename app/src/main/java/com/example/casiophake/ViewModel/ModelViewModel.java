package com.example.casiophake.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.casiophake.Model.Expression;
import com.example.casiophake.RoomDatabase.Repository;

import java.util.List;

public class ModelViewModel extends AndroidViewModel {
    private static Repository repository;
    public static LiveData<List<Expression>> allExpressions;

    public ModelViewModel(@NonNull @org.jetbrains.annotations.NotNull Application application) {
        super(application);
        repository = new Repository(application);
        allExpressions = repository.getAllExpressions();
    }

/** Call when the equal_button is clicked: it will insert expression is showing on the screen into database*/
    public static void insertExpression(Expression expression){
        repository.insertExpression(expression);
    }
}
