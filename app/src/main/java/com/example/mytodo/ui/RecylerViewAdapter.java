package com.example.mytodo.ui;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytodo.R;
import com.example.mytodo.model.Todo;

import java.text.DateFormat;
import java.util.List;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.MyViewHolder> {

    public interface Listener{
        void OnDelete(int id);
        void OnItemClick(Todo todo);
    }


    public List<Todo> todoList;
    public Listener listener;
    public RecylerViewAdapter(List<Todo> todoList, Listener listener){
            this.todoList = todoList;
            this.listener = listener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.todoitems, viewGroup, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.todo = todoList.get(i);
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
        myViewHolder.textView.setText(myViewHolder.todo.todo);
        myViewHolder.barcodeText.setText("(" +myViewHolder.todo.barcode + ")");
        myViewHolder.dateText.setText(dateFormat.format(myViewHolder.todo.startdate) + " - " + dateFormat.format(myViewHolder.todo.endDate));
        myViewHolder.itemView.setOnClickListener(view -> listener.OnItemClick(myViewHolder.todo));
        Log.d("TESTAPA" , "TESTA " + myViewHolder.todo.id);
        myViewHolder.imageView.setOnClickListener(view -> {
            listener.OnDelete(myViewHolder.todo.id);
        });


    }

    @Override
    public int getItemCount() {
        return todoList != null ? todoList.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public TextView dateText;
        public TextView barcodeText;
        public Todo todo;
        public ImageView imageView;
        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textView);
            dateText = v.findViewById(R.id.dateText);
            imageView = v.findViewById(R.id.imageView);
            barcodeText = v.findViewById(R.id.textBarcode);
        }
    }
}
