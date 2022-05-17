package com.example.successful_balanced_life.viewmodel;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.successful_balanced_life.model.Category;
import com.example.successful_balanced_life.repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private CategoryRepository repository;
    private LiveData<List<Category>> allCategory;


    public CategoryViewModel(@Nullable Application application) {
       super(application);
       repository = CategoryRepository.getInstance(application);
       allCategory = repository.getAllCategory();
    }

    public LiveData<List<Category>> getAllCategory() {
        return repository.getAllCategory();
    }

    public void insert(Category category){
        repository.insert(category);
    }

    public void deleteAll(){
        repository.deleteAllData();
    }

}