package com.zippyttech.crearpdf;

public class Anime {
   private int id, capitule;
   private int date_int_c,date_int_u,type_int;
   private String name, status,date_c,date_u,color;
   private boolean status_bol;
   private String type,dirImage, image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapitule() {
        return capitule;
    }

    public void setCapitule(int capitule) {
        this.capitule = capitule;
    }

    public int getDate_int_c() {
        return date_int_c;
    }

    public void setDate_int_c(int date_int_c) {
        this.date_int_c = date_int_c;
    }

    public int getDate_int_u() {
        return date_int_u;
    }

    public void setDate_int_u(int date_int_u) {
        this.date_int_u = date_int_u;
    }

    public int getType_int() {
        return type_int;
    }

    public void setType_int(int type_int) {
        this.type_int = type_int;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate_c() {
        return date_c;
    }

    public void setDate_c(String date_c) {
        this.date_c = date_c;
    }

    public String getDate_u() {
        return date_u;
    }

    public void setDate_u(String date_u) {
        this.date_u = date_u;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isStatus_bol() {
        return status_bol;
    }

    public void setStatus_bol(boolean status_bol) {
        this.status_bol = status_bol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirImage() {
        return dirImage;
    }

    public void setDirImage(String dirImage) {
        this.dirImage = dirImage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
