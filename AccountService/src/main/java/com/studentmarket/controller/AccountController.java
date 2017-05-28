package com.studentmarket.controller;

import com.google.gson.Gson;
import com.studentmarket.models.Account;
import com.studentmarket.models.AccountDao;
import com.studentmarket.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
public class AccountController {

     protected Logger logger = Logger.getLogger(AccountController.class
             .getName());

     @Autowired
     private AccountDao accountDao;

     @RequestMapping(value="/account/create/{fname}/{lname}/{phone}/{email}/{address}/{postal}/{city}/{country}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public boolean registerAccount(@PathVariable String fname,
                                    @PathVariable String lname,
                                    @PathVariable Long phone,
                                    @PathVariable String email,
                                    @PathVariable String address,
                                    @PathVariable Integer postal,
                                    @PathVariable String city,
                                    @PathVariable String country,
                                    @PathVariable String password) {
          logger.info("AccountController registerAccount() invoked: ");

          Account account = new Account(fname, lname, phone, email, address,
               postal, city, country, password);
          if(accountDao.findByEmail(account.getEmail()) == null) {
               accountDao.save(account);
               return true;
               //return "Account successfully created";
          }
          return false;
          //return "Account email already registered. Please try a different email";
     }

     @RequestMapping(value="/account/login/{username}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public String login(@PathVariable String username, @PathVariable String password)
     {
          logger.info("AccountController login() invoked: ");
          Account foundAccount = accountDao.findByEmail(username);
          Gson gson = new Gson();
          if(foundAccount != null && password.equals(foundAccount.getPassword())) {
               logger.info("AccountController login() account found: " + foundAccount.toString());
               return gson.toJson(foundAccount);
          }
          return gson.toJson("invalid");
     }

     @RequestMapping(value="/test/{json}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     @ResponseBody
     public String testJSON(@PathVariable String json) {
          logger.info("AccountController testJSON() invoked: ");

          Gson gson = new Gson();
          User temp = gson.fromJson(json, User.class);
          logger.info("AccountController testJSON() json tested: " + temp.toString());

          System.out.println(temp.toString());
          return gson.toJson(temp);
     }


     @RequestMapping(value="/account/delete")
     @ResponseBody
     public String delete(long id) {
          logger.info("AccountController delete() invoked: ");

          Account account;
          try {
               account = new Account(id);
               accountDao.delete(account);
          }
          catch (Exception ex) {
               logger.info("AccountController delete() delete error: " + ex.toString());
               return "Error deleting the user: " + ex.toString();
          }
          logger.info("AccountController delete() delete successful: " + account.toString());
          return "User succesfully deleted!";
     }

     @RequestMapping("/account/get-by-email")
     @ResponseBody
     public String getByEmail(String email) {
          logger.info("AccountController getByEmail() invoked: ");
          String userId;
          try {
               Account account = accountDao.findByEmail(email);
               userId = String.valueOf(account.getId());
          }
          catch (Exception ex) {
               logger.info("AccountController getByEmail() user not found: " + ex.toString());
               return "User not found";
          }
          logger.info("AccountController getByEmail() user found: " + userId);
          return "The user id is: " + userId;
     }

     @RequestMapping("/update")
     @ResponseBody
     public String updateUser(long id, String email, String name) {
          logger.info("AccountController updateUser() invoked: ");
          Account account;
          try {
               account = accountDao.findOne(id);
               account.setEmail(email);
               account.setFname(name);
               accountDao.save(account);
          }
          catch (Exception ex) {
               logger.info("AccountController updateUser() update error: " + ex.toString());
               return "Error updating the user: " + ex.toString();
          }
          logger.info("AccountController updateUser() User succesfully updated: " + account.toString());
          return "User succesfully updated!";
     }
}
