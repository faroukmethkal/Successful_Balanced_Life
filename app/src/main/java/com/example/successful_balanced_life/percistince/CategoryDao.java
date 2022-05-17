package com.example.successful_balanced_life.percistince;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.successful_balanced_life.model.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);

    @Query("select * from category_table order by name")
    LiveData<List<Category>> getAllCategory();


    @Query("delete from category_table")
    void deleteAllCategory();

}
