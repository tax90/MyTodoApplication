package com.example.mytodo.ui;


import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.mytodo.R;
import com.example.mytodo.di.Injectable;
import com.example.mytodo.model.Todo;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import androidx.navigation.Navigation;


public class AddFragment extends Fragment implements Injectable {


    private EditText editText;
    private Button button;
    private EditText barcodeText;
    private AddViewModel addViewModel;
    private SeekBar seekBar;
    private TextView dateText;
    private TextView endDateText;
    private TextView selectedEndDate;
    private int priorty = 0;
    private TextView selectedDate;
    private DateFormat dateFormat;
    private InputMethodManager imm;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add, container, false);
        editText = root.findViewById(R.id.editText);
        button = root.findViewById(R.id.button);
        seekBar = root.findViewById(R.id.seekBar2);
        dateText = root.findViewById(R.id.textDate);
        selectedDate = root.findViewById(R.id.textSelectedDate);
        endDateText = root.findViewById(R.id.selectendDate);
        selectedEndDate = root.findViewById(R.id.textEndDate);
        barcodeText = root.findViewById(R.id.barcodeText);



        button.setOnClickListener(view -> {
            addViewModel.setTodo(editText.getText().toString());
            addViewModel.setPriorty(priorty);
            addViewModel.instert();
            addViewModel.instertGrocery();
            editText.setText(null);
            Navigation.findNavController(view).popBackStack();
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                priorty = i;
                hideSoftKeyBoard();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        dateText.setOnClickListener(view -> {
          selectDate();
        });

        endDateText.setOnClickListener(view -> {
            selectEnddate();
        });

        dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
        Calendar calendar = Calendar.getInstance();
        selectedDate.setText(dateFormat.format(calendar.getTime()));
        selectedEndDate.setText(dateFormat.format(calendar.getTime()));

        selectedDate.setOnClickListener(view -> selectDate());
        selectedEndDate.setOnClickListener(view -> selectEnddate());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addViewModel = ViewModelProviders.of(this,viewModelFactory).get(AddViewModel.class);
        Calendar calendar = Calendar.getInstance();

        if (getArguments() != null){
            barcodeText.setText(getArguments().getString("barcode"));
            addViewModel.setBarcode(getArguments().getString("barcode"));
        }
        if (addViewModel.getBarcode() != null){
            addViewModel.getGrocerys(addViewModel.getBarcode()).observe(getActivity(), groceryInfo -> {
                if (groceryInfo != null){
                    editText.setText(groceryInfo.name);
                }
            });
        }


        addViewModel.setStartDate(calendar.getTime());
        addViewModel.setEndDate(calendar.getTime());
    }

    public void selectDate(){
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (datePicker, year, month, dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);
            selectedDate.setText(dateFormat.format(calendar.getTime()));
            addViewModel.setStartDate(calendar.getTime());

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(calendar.getTime().getTime());
        datePickerDialog.show();
    }
    public void selectEnddate(){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (datePicker, year, month, dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);
            selectedEndDate.setText(dateFormat.format(calendar.getTime()));
            addViewModel.setEndDate(calendar.getTime());

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(addViewModel.getStartDate().getTime());
        datePickerDialog.show();
    }

    private void hideSoftKeyBoard() {
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
}
