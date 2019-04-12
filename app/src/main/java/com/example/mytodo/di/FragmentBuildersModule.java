package com.example.mytodo.di;


import com.example.mytodo.ui.AddFragment;
import com.example.mytodo.ui.ListFragment;
import com.example.mytodo.ui.ScannerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ListFragment contributeListFragment();

    @ContributesAndroidInjector
    abstract AddFragment contributeAddFragment();

    @ContributesAndroidInjector
    abstract ScannerFragment contributeScannerFragment();

}
