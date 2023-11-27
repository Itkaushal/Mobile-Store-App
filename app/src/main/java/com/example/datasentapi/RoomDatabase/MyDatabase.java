package com.example.datasentapi.RoomDatabase;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract ProductDao productDao();
}
