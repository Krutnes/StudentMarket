package com.studentmarket.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

/**
 * Represents a user account for this web application.
 */
@Entity
@Table(name = "accounts")
public class Account {

     // ------------------------
     // PRIVATE FIELDS
     // ------------------------

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private long id;

     @NotNull
     private String fname;

     @NotNull
     private String lname;

     @NotNull
     private Long phone;

     @NotNull
     private String email;

     @NotNull
     private String address;

     @NotNull
     private Integer postal;

     @NotNull
     private String city;

     @NotNull
     private String country;

     @NotNull
     private String password;

     // ------------------------
     // PUBLIC METHODS
     // ------------------------

     public Account() { }

     public Account(long id) {
          this.id = id;
     }

     public Account(String fname, String lname, Long phone, String email, String address, Integer postal,
                    String city, String country, String password) {
          this.fname = fname;
          this.lname = lname;
          this.phone = phone;
          this.email = email;
          this.address = address;
          this.postal = postal;
          this.city = city;
          this.country = country;
          this.password = password;
     }

     public long getId() {
          return id;
     }

     public void setId(long value) {
          this.id = value;
     }

     public String getFname() {
          return fname;
     }

     public void setFname(String value) {
          this.fname = value;
     }

     public String getLname() {
          return lname;
     }

     public void setLname(String value) {
          this.lname = value;
     }

     public Long getPhone() {
          return phone;
     }

     public void setPhone(Long value) {
          this.phone = value;
     }

     public String getEmail() { return email; }

     public void setEmail(String value) { this.email = value; }

     public String getAddress() {
          return address;
     }

     public void setAddress(String value) {
          this.address = value;
     }

     public Integer getPostal() {
          return postal;
     }

     public void setPostal(Integer value) {
          this.postal = value;
     }

     public String getCity() {
          return city;
     }

     public void setCity(String value) {
          this.city = value;
     }

     public String getCountry() {
          return country;
     }

     public void setCountry(String value) {
          this.city = value;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String value) {
          this.password = value;
     }

} // class Account
