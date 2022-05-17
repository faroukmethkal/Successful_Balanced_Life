package com.example.successful_balanced_life.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

@Entity(tableName = "category_table")
public class Category {
    @PrimaryKey
    @NonNull
    private String name;

    public Category(@NonNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

}
