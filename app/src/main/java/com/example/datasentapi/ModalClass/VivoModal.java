package com.example.datasentapi.ModalClass;

import java.io.Serializable;

public class VivoModal implements Serializable {

    private String pname;
    private int pprice;
    private String pdesc;
    private String pimage;
    private int quantity;

    public VivoModal()
    {

    }

    public VivoModal(String pname, int pprice, String pdesc, String pimage, int quantity) {
        this.pname = pname;
        this.pprice = pprice;
        this.pdesc = pdesc;
        this.pimage = pimage;
        this.quantity = quantity;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPprice() {
        return pprice;
    }

    public void setPprice(int pprice) {
        this.pprice = pprice;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
