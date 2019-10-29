package com.example.iictbeta2.JavaClasses;

public class OrderDetails {
    String rice, chicken, pudding, lentil, eggcurry, khichuri, tableno;
    String display_name, uid, oid;

    public void setRice(String rice) {
        this.rice = rice;
    }

    public void setChicken(String chicken) {
        this.chicken = chicken;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public void setPudding(String pudding) {
        this.pudding = pudding;
    }

    public void setLentil(String lentil) {
        this.lentil = lentil;
    }

    public void setEggcurry(String eggcurry) {
        this.eggcurry = eggcurry;
    }

    public void setKhichuri(String khichuri) {
        this.khichuri = khichuri;
    }

    public void setTableno(String tableno) {
        this.tableno = tableno;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRice() {
        return rice;
    }

    public String getChicken() {
        return chicken;
    }

    public String getPudding() {
        return pudding;
    }

    public String getLentil() {
        return lentil;
    }

    public String getEggcurry() {
        return eggcurry;
    }

    public String getKhichuri() {
        return khichuri;
    }

    public String getTableno() {
        return tableno;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getUid() {
        return uid;
    }

    public String getOrder(){
        String msg = "";
        if(Integer.parseInt(rice) > 0) {
            msg += "Rice : " + rice + "\n";
        }
        if(Integer.parseInt(chicken) > 0){
            msg += "Chicken : " + chicken + "\n";
        }
        if(Integer.parseInt(pudding) > 0){
            msg += "Pudding : " + pudding + "\n";
        }
        if(Integer.parseInt(eggcurry) > 0){
            msg += "Egg Curry : " + eggcurry + "\n";
        }
        if(Integer.parseInt(lentil) > 0){
            msg += "Lentil : " + lentil + "\n";
        }
        if(Integer.parseInt(khichuri) > 0){
            msg += "Khichuri : " + khichuri + "\n";
        }

        return msg;
    }
}
