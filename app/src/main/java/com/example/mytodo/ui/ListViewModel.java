package com.example.mytodo.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.mytodo.model.Todo;
import com.example.mytodo.repo.TodoRepository;

import java.util.List;

import javax.inject.Inject;

public class ListViewModel extends ViewModel {

    TodoRepository todoRepository;
    @Inject
    public ListViewModel(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public LiveData<List<Todo>> getTodos(){
        return todoRepository.getTodos();
    }

    public void delete(int id){
        todoRepository.delete(id);
    }

    public void Update(Todo todo){
        todoRepository.update(todo);
    }
}
