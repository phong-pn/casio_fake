package com.example.casiophake.RoomDatabase;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.casiophake.Model.Expression;

import java.util.List;

public class Repository {
    public DAO dao;

    public Repository(Context context) {
        Database db = Database.getInstance(context);
        dao= db.dao();
    }

    public LiveData<List<Expression>> getAllExpressions(){
        return dao.getAllExpressions();
    }

    public void insertExpression(Expression expression){
        Database.executorService.execute(()->dao.insertExpression(expression));
    }

}
