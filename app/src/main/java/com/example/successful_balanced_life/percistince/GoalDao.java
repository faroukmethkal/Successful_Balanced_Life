package com.example.successful_balanced_life.percistince;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import androidx.room.Update;

import com.example.successful_balanced_life.model.Goal;

import java.util.List;

@Dao
public interface GoalDao {

    @Query("select * from goal_table where categoryName =:name")
    LiveData<List<Goal>> getAllGoalWhereCategoryIs(String name);

    @Query("select * from goal_table")
    LiveData<List<Goal>> getAllGoal();

    @Query("select * from goal_table where id is :id")
    LiveData<List<Goal>> getGoalByName(int id);


    @Insert
    void insert(Goal goal);

    @Delete
    void delete(Goal goal);

    @Query("update goal_table set progress =:progress where id =:id")
    void updateProgress( int id, int progress);

    @Update
    void update(Goal goal);

}
