package com.example.datasentapi.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insertrecord(Product product);

    @Query("SELECT EXISTS(SELECT * FROM product WHERE pid = :productid)")
    Boolean is_exist(int productid);

    @Query("SELECT * FROM product")
    List<Product> getAllproduct();

    @Query("DELETE FROM product WHERE pid=:id")
    void deleteById(int id);
}
