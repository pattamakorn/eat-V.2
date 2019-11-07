package com.example.eat_master;

public class footHit {

    private String foodname,foodres,price,ima,idfood;

    public footHit() {
    }

    public footHit(String foodname, String foodres, String price,String ima, String idfood) {
        this.foodname = foodname;
        this.foodres = foodres;
        this.price = price;
        this.ima = ima;
        this.idfood = idfood;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodres() {
        return foodres;
    }

    public void setFoodres(String foodres) {
        this.foodres = foodres;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIma() {
        return ima;
    }

    public void setIma(String ima) {
        this.ima = ima;
    }

    public String getIdfood() {
        return idfood;
    }

    public void setIdfood(String idfood) {
        this.idfood = idfood;
    }
}
