package com.example.eat_master;

public class recentfood {
    private String idorder,idfoodrecent,namefrecent,nameresrec,datefoodrecent,imgfoodrecent,idcusrecent,price;

    public recentfood() {
    }

    public recentfood(String idorder,String idfoodrecent, String namefrecent,String nameresrec ,String datefoodrecent, String imgfoodrecent, String idcusrecent,String price) {
        this.idorder = idorder;
        this.idfoodrecent = idfoodrecent;
        this.namefrecent = namefrecent;
        this.nameresrec = nameresrec;
        this.datefoodrecent = datefoodrecent;
        this.imgfoodrecent = imgfoodrecent;
        this.idcusrecent = idcusrecent;
        this.price = price;
    }

    public String getIdorder() {
        return idorder;
    }

    public void setIdorder(String idorder) {
        this.idorder = idorder;
    }

    public String getIdfoodrecent() {
        return idfoodrecent;
    }

    public void setIdfoodrecent(String idfoodrecent) {
        this.idfoodrecent = idfoodrecent;
    }

    public String getNamefrecent() {
        return namefrecent;
    }

    public void setNamefrecent(String namefrecent) {
        this.namefrecent = namefrecent;
    }

    public String getNameresrec() {
        return nameresrec;
    }

    public void setNameresrec(String nameresrec) {
        this.nameresrec = nameresrec;
    }

    public String getDatefoodrecent() {
        return datefoodrecent;
    }

    public void setDatefoodrecent(String datefoodrecent) {
        this.datefoodrecent = datefoodrecent;
    }

    public String getImgfoodrecent() {
        return imgfoodrecent;
    }

    public void setImgfoodrecent(String imgfoodrecent) {
        this.imgfoodrecent = imgfoodrecent;
    }

    public String getIdcusrecent() {
        return idcusrecent;
    }

    public void setIdcusrecent(String idcusrecent) {
        this.idcusrecent = idcusrecent;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
