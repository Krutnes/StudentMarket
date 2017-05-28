package com.studentmarket.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

/**
 * Created by Andreas on 5/26/2017.
 */

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Product info
    @NotNull
    private String title; //product title
    @NotNull
    private String category; //product category
    @NotNull
    private String type; //is it for sale, rent or give away
    @NotNull
    private String state; //used og new
    @NotNull
    private String price; //product price
    @NotNull
    private String description; //product description
    //@NotNull
    //private String image; //product image


    //Contact info
    @NotNull
    private String phone;
    @NotNull
    private String address;
    @NotNull
    private String postal;
    @NotNull
    private String city;
    @NotNull
    private String country;
    //@NotNull
    //private String userId;

    public Product(){}

    public Product(long id) {
        this.id = id;
    }

    //Kun for testing
    public Product(String title){
        this.title = title;
        this.category = "";
        this.type = "";
        this.state = "";
        this.price = "";
        this.description = "";
        //this.image = image;
        this.phone = "";
        this.address = "";
        this.postal = "";
        this.city = "";
        this.country = "";
        //this.userId = userId;
    }

    public Product(String title, String category, String type, String state, String price, String description,
                   String phone, String address, String postal, String city, String country) {
        this.title = title;
        this.category = category;
        this.type = type;
        this.state = state;
        this.price = price;
        this.description = description;
        //this.image = image;
        this.phone = phone;
        this.address = address;
        this.postal = postal;
        this.city = city;
        this.country = country;
        //this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public String getImage() {
        return image;
    }*/

    /*public void setImage(String image) {
        this.image = image;
    }*/

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /*public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }*/
}
