package com.example.successful_balanced_life.viewmodel;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.successful_balanced_life.model.Goal;
import com.example.successful_balanced_life.repository.GoalRepository;

import java.util.List;


public class GoalViewModel extends AndroidViewModel {

    private GoalRepository repository;
    private LiveData<List<Goal>> allGoal;

    public GoalViewModel(@Nullable Application application) {
        super(application);
        repository = GoalRepository.getInstance(application);
    }

    public LiveData<List<Goal>> getAllGoalWhereCategoryNameIs(String category) {
        allGoal = repository.getAllGoalWhereCategoryNameIs(category);
        return allGoal;
    }

    public void insert(Goal goal){
        repository.insert(goal);
    }

    public void update(Goal goal){
        repository.update(goal);
    }

    public LiveData<List<Goal>> getGoalByName(int id){
        return repository.getGoalByName(id);
    }

    public void updateProgress( int id, int progress){
        repository.updateProgress(id, progress);
    }

    public LiveData<List<Goal>> getAllGoal(){
      return   repository.getAllGoal();
    }



}