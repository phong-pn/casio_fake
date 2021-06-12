package com.example.casiophake.RoomDatabase;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.casiophake.Model.Model;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Model.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static Database instance;
    private static final String DATABASE_NAME = "model_table";
    public static final int NUMBER_OF_THREAD = 4;
    public static ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREAD);

    public abstract DAO dao();

    public static Database getInstance(Context context) {
        if(instance==null)
        synchronized (Database.class){
            if(instance==null){
                if(!context.getDatabasePath(DATABASE_NAME).exists()) {
                    instance = Room.databaseBuilder(context, Database.class, DATABASE_NAME).build();
                    Log.d("ccc","ddd");
                }
                else {
                    instance=Room.databaseBuilder(context, Database.class, DATABASE_NAME)
                            .createFromAsset(context.getDatabasePath(DATABASE_NAME).getPath())
                            .build();
                    Log.d("ccc","ggg");
                }
            }
        }
        return instance;
    }
}
