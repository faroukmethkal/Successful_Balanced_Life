package com.example.successful_balanced_life.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.successful_balanced_life.model.Goal;
import com.example.successful_balanced_life.percistince.GoalDao;
import com.example.successful_balanced_life.percistince.GoalDatabase;

import java.util.List;
import java.util.Optional;

public class GoalRepository {
    public final GoalDao goalDao;
    public static GoalRepository instance;
    private LiveData<List<Goal>> allGoal;

    public GoalRepository(Application application){
        GoalDatabase database = GoalDatabase.getInstance(application);
        goalDao = database.goalDao();
    }

    public static GoalRepository getInstance(Application application){
        if (instance == null){
            instance = new GoalRepository(application);
        }

        return instance;
    }

    public LiveData<List<Goal>> getAllGoalWhereCategoryNameIs(String categoryName){
        allGoal = goalDao.getAllGoalWhereCategoryIs(categoryName);
        return allGoal;
    }

    public LiveData<List<Goal>> getAllGoal(){
        return goalDao.getAllGoal();
    }

    public void insert(Goal goal){
        new InsertGoalAsync(goalDao).execute(goal);
    }

    private static class InsertGoalAsync extends AsyncTask<Goal,Void,Void>{
        private final GoalDao goalDao;
        private  InsertGoalAsync(GoalDao goalDao){
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(Goal... goals) {
            goalDao.insert(goals[0]);
            return null;
        }
    }

    public void update(Goal goal){
        new UpdateGoalAsync(goalDao).execute(goal);
    }

    private static class UpdateGoalAsync extends AsyncTask<Goal,Void,Void>{
        private final GoalDao goalDao;
        private  UpdateGoalAsync(GoalDao goalDao){
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(Goal... goals) {
            goalDao.update(goals[0]);
            return null;
        }
    }

    public void updateProgress( int id, int progress){
        new UpdateProgressGoalAsync(goalDao).execute(id,progress);
    }

    private static class UpdateProgressGoalAsync extends AsyncTask<Integer,Void,Void>{
        private final GoalDao goalDao;

        private  UpdateProgressGoalAsync(GoalDao goalDao){
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            int id = integers[0];
            int progress = integers[1];
            goalDao.updateProgress(id, progress);
            return null;
        }
    }

    public void delete(Goal goal){
        goalDao.delete(goal);
    }

    public LiveData<List<Goal>> getGoalByName(int id){
        return goalDao.getGoalByName(id);
    }


}
