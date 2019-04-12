package com.example.mytodo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.mytodo.model.GroceryInfo;
import com.example.mytodo.model.Todo;

@Database(entities = {Todo.class, GroceryInfo.class}, version = 1, exportSchema = false)
@TypeConverters({DateTypeConverter.class})
public abstract class MyTodoDB extends RoomDatabase {

    abstract public TodoDAO todoDAO();

    abstract public GroceryDAO groceryDAO();
}
