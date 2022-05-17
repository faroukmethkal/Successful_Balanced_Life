package com.example.successful_balanced_life.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.successful_balanced_life.model.Category;
import com.example.successful_balanced_life.percistince.CategoryDao;
import com.example.successful_balanced_life.percistince.CategoryDatabase;

import java.util.Arrays;
import java.util.List;

public class CategoryRepository {
    public final CategoryDao categoryDao;
    public static CategoryRepository instance;
    private LiveData<List<Category>> allCategory;

    public CategoryRepository(Application application) {
        CategoryDatabase database = CategoryDatabase.getInstance(application);
        categoryDao = database.categoryDao();
        allCategory = categoryDao.getAllCategory();
    }

    public static CategoryRepository getInstance(Application application){
        if(instance == null){
            instance = new CategoryRepository(application);
        }

        return instance;
    }

    public LiveData<List<Category>> getAllCategory(){
        return allCategory;
    }

    public void insert(Category category){
        new InsertCategoryAsync(categoryDao).execute(category);
    }

    private static class InsertCategoryAsync extends AsyncTask<Category, Void, Void>{

        private final CategoryDao categoryDao;

        private InsertCategoryAsync(CategoryDao categoryDao){
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.insert(categories[0]);
            return null;
        }
    }

    public void deleteAllData(){
        categoryDao.deleteAllCategory();
    }





}
