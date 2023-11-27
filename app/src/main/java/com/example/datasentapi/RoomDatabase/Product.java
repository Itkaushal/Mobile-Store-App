package com.example.datasentapi.RoomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {

    @PrimaryKey(autoGenerate = true)
    public int pid;

    @ColumnInfo(name = "pname")
    public String pname;

    @ColumnInfo(name = "pquat")
    public int pquat;

    @ColumnInfo(name = "pprice")
    public int pprice;

    public Product(int pid, String pname, int pquat, int pprice) {
        this.pid = pid;
        this.pname = pname;
        this.pquat = pquat;
        this.pprice = pprice;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPquat() {
        return pquat;
    }

    public void setPquat(int pquat) {
        this.pquat = pquat;
    }

    public int getPprice() {
        return pprice;
    }

    public void setPprice(int pprice) {
        this.pprice = pprice;
    }
}
