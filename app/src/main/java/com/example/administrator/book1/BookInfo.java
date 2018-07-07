package com.example.administrator.book1;

/**
 * Created by loveb on 2018/6/30.
 */

public class BookInfo {

    public Integer Id;
    public String auter;
    public Double price;
    public Integer pages;
    public String Name;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getAuter() {
        return auter;
    }

    public void setAuter(String auter) {
        this.auter = auter;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
