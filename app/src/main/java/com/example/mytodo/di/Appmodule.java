package com.example.mytodo.di;

import android.app.Activity;
import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.mytodo.db.GroceryDAO;
import com.example.mytodo.db.MyTodoDB;
import com.example.mytodo.db.TodoDAO;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

@Module(includes = ViewModelModule.class)
class Appmodule {

    @Singleton
    @Provides
    Executor executor(){
        return Executors.newSingleThreadExecutor();
    }
    @Singleton
    @Provides
    MyTodoDB providesMyTodoDatabase(Application application) {
            return Room.databaseBuilder(application,MyTodoDB.class,"mytodo.db").build();
    }

    @Singleton
    @Provides
    TodoDAO provideTodoDao(MyTodoDB myTodoDB){
        return myTodoDB.todoDAO();
    }

    @Singleton
    @Provides
    GroceryDAO provideGroceryDao(MyTodoDB myTodoDB){return myTodoDB.groceryDAO();}

    @Provides
    ZXingScannerView provideZxingScannerview(Application activity){
        return new ZXingScannerView(activity);
    }
}
