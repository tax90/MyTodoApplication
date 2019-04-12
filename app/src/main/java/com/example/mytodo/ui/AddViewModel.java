package com.example.mytodo.ui;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.mytodo.model.GroceryInfo;
import com.example.mytodo.model.Todo;
import com.example.mytodo.repo.TodoRepository;


import java.util.Date;

import javax.inject.Inject;

public class AddViewModel extends ViewModel {

    public TodoRepository todoRepository;



    private int priority;
    private String barcode;
    private Date startDate;
    private Date endDate;
    private String todos;


    @Inject
    public AddViewModel(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public void instert(){
        Todo todo = new Todo(0,todos,priority,startDate,endDate,barcode);
        todoRepository.insert(todo);
    }
    public void instertGrocery(){
        GroceryInfo groceryInfo = new GroceryInfo(0,todos,barcode);
        todoRepository.insertGrocery(groceryInfo);
    }
    public LiveData<GroceryInfo> getGrocerys(String barcode){
        return todoRepository.getGrocerys(barcode);
    }

    public void setPriorty(int priorty) {
        this.priority = priorty;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getStartDate() {
        return startDate;
    }


    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTodo(String todos) {
        this.todos = todos;
    }
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
