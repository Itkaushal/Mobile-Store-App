package com.example.datasentapi.Modal;

public class modal_home {

    String pname,pimgurl;

    public modal_home()
    {

    }

    public modal_home(String pname, String pimgurl) {
        this.pname = pname;
        this.pimgurl = pimgurl;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPimgurl() {
        return pimgurl;
    }

    public void setPimgurl(String pimgurl) {
        this.pimgurl = pimgurl;
    }
}
