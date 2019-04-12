package com.example.mytodo.repo;

import android.arch.lifecycle.LiveData;

import com.example.mytodo.db.GroceryDAO;
import com.example.mytodo.db.TodoDAO;
import com.example.mytodo.model.GroceryInfo;
import com.example.mytodo.model.Todo;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

public class TodoRepository {


    final Executor executor;
    final TodoDAO todoDAO;
    final GroceryDAO groceryDAO;
    private LiveData<List<Todo>> todoss;

    @Inject
    public TodoRepository(Executor executor, TodoDAO todoDAO,GroceryDAO groceryDAO){
        this.executor = executor;
        this.todoDAO = todoDAO;
        this.groceryDAO = groceryDAO;
    }

    public LiveData<List<Todo>> getTodos(){
        if (todoss == null){
            todoss = todoDAO.getTodos();
        }
        return todoss;
    }
    public void insertGrocery(GroceryInfo groceryInfo){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                groceryDAO.insert(groceryInfo);
            }
        });
    }
    public LiveData<GroceryInfo> getGrocerys(String barcode){
        return groceryDAO.getGrocery(barcode);
    }

    public void insert(final Todo todo){
        executor.execute(() -> todoDAO.insert(todo));
    }

    public void delete(int id){
        executor.execute(() -> todoDAO.deleteByTodoId(id));
    }

    public void update(Todo todo){
            executor.execute(() -> todoDAO.update(todo));
    }

}
