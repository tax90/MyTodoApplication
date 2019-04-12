package com.example.mytodo.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.mytodo.model.GroceryInfo;

@Dao
public interface GroceryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GroceryInfo groceryInfo);

    @Query("SELECT * FROM groceryinfo where barcode = :barCode LIMIT 1")
    LiveData<GroceryInfo> getGrocery(String barCode);
}
