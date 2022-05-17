package com.example.successful_balanced_life.percistince;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.successful_balanced_life.model.Goal;

@Database(entities = {Goal.class}, version = 2, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class GoalDatabase extends RoomDatabase {

    private static GoalDatabase instance;
    public abstract GoalDao goalDao();

    public static synchronized GoalDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    GoalDatabase.class,"goal_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
