package com.example.successful_balanced_life.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


import com.example.successful_balanced_life.percistince.DateConverter;

import java.time.LocalDateTime;
import java.util.Date;


@Entity(tableName = "goal_table")
@TypeConverters(DateConverter.class)
public  class Goal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String categoryName;
    private int period;
    private int progress;
    private LocalDateTime startDate;
    private LocalDateTime deadline;


    public Goal(String name, String categoryName, int period, int progress,LocalDateTime startDate, LocalDateTime deadline) {
        this.name = name;
        this.categoryName = categoryName;
        this.period = period;
        this.progress = progress;
        this.startDate = startDate;
        this.deadline = deadline;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }



}
