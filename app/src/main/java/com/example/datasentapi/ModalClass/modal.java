package com.example.datasentapi.ModalClass;

import java.io.Serializable;

public  class modal implements Serializable{
      String pname;
      String pdesc;
      int pprice;
      String pimgurl;

    public modal()
    {

    }

    public modal(String pname, String pdesc, int pprice, String pimgurl) {
        this.pname = pname;
        this.pdesc = pdesc;
        this.pprice = pprice;
        this.pimgurl = pimgurl;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public int getPprice() {
        return pprice;
    }

    public void setPprice(int pprice) {
        this.pprice = pprice;
    }

    public String getPimgurl() {
        return pimgurl;
    }

    public void setPimgurl(String pimgurl) {
        this.pimgurl = pimgurl;
    }
}
