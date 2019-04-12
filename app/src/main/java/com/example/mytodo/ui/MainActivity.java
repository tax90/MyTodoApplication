package com.example.mytodo.ui;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mytodo.R;

import javax.inject.Inject;


import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;


import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> dispatchingAndroidInjector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
