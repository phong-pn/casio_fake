package com.example.casiophake.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.casiophake.Model.Expression;

import java.util.List;

@Dao
interface DAO {
    @Query("SELECT * FROM expression_table")
    LiveData<List<Expression>> getAllExpressions();

    @Insert
    void insertExpression(Expression expression);


}
