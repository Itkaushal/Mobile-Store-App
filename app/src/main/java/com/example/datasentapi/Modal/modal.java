package com.example.datasentapi.Modal;

public class modal {
    String pname,pdesc,pprice,pimgurl;

    public modal()
    {

    }

    public modal(String pname, String pdesc, String pprice, String pimgurl) {

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

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getPimgurl() {
        return pimgurl;
    }

    public void setPimgurl(String pimgurl) {
        this.pimgurl = pimgurl;
    }
}
