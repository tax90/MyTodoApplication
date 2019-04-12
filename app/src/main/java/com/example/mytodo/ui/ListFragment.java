package com.example.mytodo.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.mytodo.R;
import com.example.mytodo.di.Injectable;
import com.example.mytodo.model.Todo;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import androidx.navigation.Navigation;


public class ListFragment extends Fragment implements Injectable {




    private ListViewModel listViewModel;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private RecylerViewAdapter recylerViewAdapter;
    private FrameLayout frameLayout;
    private Button button;
    private EditText editText;
    private ImageView closeImage;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_list,container,false);
        listViewModel = ViewModelProviders.of(this,viewModelFactory).get(ListViewModel.class);

        floatingActionButton = root.findViewById(R.id.floatingActionButton);
        recyclerView = root.findViewById(R.id.recyclerview);
        frameLayout = root.findViewById(R.id.frameLayout);
        button = root.findViewById(R.id.button2);
        editText = root.findViewById(R.id.editText2);
        closeImage = root.findViewById(R.id.imageView2);
        frameLayout.setVisibility(View.INVISIBLE);
        floatingActionButton.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_listFragment_to_scannerFragment2));


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listViewModel.getTodos().observe(this, todos -> {
            if (todos != null){

                Collections.sort(todos, (todo, t1) -> t1.priority - todo.priority);
                recylerViewAdapter = new RecylerViewAdapter(todos, new RecylerViewAdapter.Listener() {
                    @Override
                    public void OnDelete(int id) {
                        listViewModel.delete(id);
                    }

                    @Override
                    public void OnItemClick(Todo todo) {
                       frameLayout.setVisibility(View.VISIBLE);
                       floatingActionButton.hide();
                       editText.setText(todo.todo);
                       closeImage.setOnClickListener(view -> {
                           frameLayout.setVisibility(View.GONE);
                           floatingActionButton.show();
                       });
                       button.setOnClickListener(view -> {
                           Todo todo1 = new Todo(todo.id,editText.getText().toString(),todo.priority,todo.startdate,todo.endDate,todo.barcode);
                           listViewModel.Update(todo1);
                           floatingActionButton.show();
                           frameLayout.setVisibility(View.INVISIBLE);
                       });
                    }
                });
                recyclerView.setHasFixedSize(true);

                // use a linear layout manager
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(recylerViewAdapter);
            }

        });
    }
}
