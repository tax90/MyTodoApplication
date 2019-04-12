package com.example.mytodo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class GroceryInfo {


    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String barcode;

    public GroceryInfo(int id, String name, String barcode){
        this.id = id;
        this.name = name;
        this.barcode = barcode;
    }


}
