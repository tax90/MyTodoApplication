package com.example.mytodo.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mytodo.model.Todo;

import java.util.List;

@Dao
public interface TodoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Todo todo);

    @Query("SELECT * FROM todo")
    LiveData<List<Todo>> getTodos();

    @Query("DELETE FROM todo WHERE id = :todoId")
    void deleteByTodoId(int todoId);

    @Update
    void update(Todo todo);

}
