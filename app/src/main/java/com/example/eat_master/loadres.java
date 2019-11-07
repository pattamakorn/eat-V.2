package com.example.eat_master;

public class loadres {
    private String nameresre,deadline,idresre,imgpromote;

    public loadres() {
    }

    public loadres(String nameresre, String deadline, String idresre, String imgpromote) {
        this.nameresre = nameresre;
        this.deadline = deadline;
        this.idresre = idresre;
        this.imgpromote = imgpromote;
    }

    public String getNameresre() {
        return nameresre;
    }

    public void setNameresre(String nameresre) {
        this.nameresre = nameresre;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getIdresre() {
        return idresre;
    }

    public void setIdresre(String idresre) {
        this.idresre = idresre;
    }

    public String getImgpromote() {
        return imgpromote;
    }

    public void setImgpromote(String imgpromote) {
        this.imgpromote = imgpromote;
    }
}
