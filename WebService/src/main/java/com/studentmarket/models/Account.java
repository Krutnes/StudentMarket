package com.studentmarket.models;

public class Account {

     private long id;

     private String fname;

     private String lname;

     private Long phone;

     private String email;

     private String address;

     private Integer postal;

     private String city;

     private String country;

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

     public void setPassword(String password) {
          this.password = password;
     }

     public String toString() {
          return "Account:\n" +
                  fname + "\n" +
                  lname + "\n" +
                  phone + "\n" +
                  email + "\n" +
                  address + "\n" +
                  postal + "\n" +
                  city + "\n" +
                  country + "\n";
     }
} // class Account
