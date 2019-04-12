package com.example.mytodo.di;

import android.app.Application;

import com.example.mytodo.MyTodoApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        Appmodule.class,
        MainActivityModule.class
}
)
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(MyTodoApplication myTodoApplication);
}
