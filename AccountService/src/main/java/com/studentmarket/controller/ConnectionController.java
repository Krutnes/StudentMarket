package com.studentmarket.controller;

import com.studentmarket.exceptions.AccountNotFoundException;
import com.studentmarket.models.Account;
import com.studentmarket.models.AccountDao;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;


public class ConnectionController {

     protected Logger logger = Logger.getLogger(ConnectionController.class
             .getName());

     protected AccountDao accountDao;
     @RequestMapping("/login/{email}/{password}")
     public Account login(@PathVariable("email") String email, @PathVariable("password") String password) {

          logger.info("ConnectionController login() invoked: ");

          Account account = accountDao.findByEmail(email);
          if(account == null)
               throw new AccountNotFoundException(email);
          else {
               logger.info("ConnectionController login() invoked: " + account.toString());
               return account;
          }
     }
}
