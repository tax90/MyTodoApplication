package com.example.mytodo.di;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.mytodo.ui.AddViewModel;
import com.example.mytodo.ui.ListViewModel;
import com.example.mytodo.viewmodel.TodoViewmodelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel.class)
    abstract ViewModel bindListViewModel(ListViewModel listViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddViewModel.class)
    abstract ViewModel bindAddViewModel(AddViewModel addViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(TodoViewmodelFactory factory);
}
