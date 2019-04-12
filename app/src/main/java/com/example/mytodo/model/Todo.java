package com.example.mytodo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Todo {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String todo;
    public int priority;
    public Date startdate;
    public Date endDate;
    public String barcode;

    public Todo(int id, String todo,int priority,Date startdate, Date endDate, String barcode){
        this.id = id;
        this.todo = todo;
        this.priority = priority;
        this.startdate = startdate;
        this.endDate = endDate;
        this.barcode = barcode;
    }

}
