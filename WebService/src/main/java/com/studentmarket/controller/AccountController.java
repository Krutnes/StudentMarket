package com.studentmarket.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.studentmarket.models.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Logger;

@Controller
public class AccountController {

     protected Logger logger = Logger.getLogger(AccountController.class
             .getName());

     //IKKE FERDIG
     @RequestMapping(value = "/account/create", method = RequestMethod.POST)
     public String createAccount(@ModelAttribute("fname") String firstName,
                         @ModelAttribute("lname") String lastName,
                         @ModelAttribute("phonenumber") Long phonenumber,
                         @ModelAttribute("email") String email,
                         @ModelAttribute("address") String address,
                         @ModelAttribute("postalcode") Integer postalCode,
                         @ModelAttribute("city") String city,
                         @ModelAttribute("country") String country,
                         @ModelAttribute("password") String password,
                         Model model){
          logger.info("AccountController createAccount() invoked: ");
          boolean doAccountExist = false;
          String createAccountService = "";
          try{
               String parameters = URLEncoder.encode(firstName, "UTF-8")+
                       "/"+URLEncoder.encode(lastName, "UTF-8")+
                       "/"+phonenumber+"/"+email+
                       "/"+URLEncoder.encode(address, "UTF-8")+
                       "/"+postalCode+
                       "/"+URLEncoder.encode(city, "UTF-8")+
                       "/"+URLEncoder.encode(country, "UTF-8")+
                       "/"+URLEncoder.encode(password, "UTF-8");
               createAccountService = "http://localhost:4444/account/create/" + parameters;
               logger.info("AccountController createAccount() parameters: " + parameters);
          } catch(Exception e) {
               System.out.println("Encoding error: " + e);
          }
          try{
               //Connecting to AccountService microservice
               URL url = new URL(createAccountService);
               HttpURLConnection requestCreateAccount = (HttpURLConnection) url.openConnection();
               requestCreateAccount.connect();
               logger.info("AccountController createAccount() connection established: ");

               JsonParser jp = new JsonParser();

               JsonElement createAccountServiceResult = jp.parse(new InputStreamReader((InputStream) requestCreateAccount.getContent()));
               System.out.println(createAccountServiceResult);
               doAccountExist = Boolean.parseBoolean(createAccountServiceResult.toString());
               String result = doAccountExist ? "Account successfully created" : "Account already exist";
               logger.info("AccountController createAccount() : " + result);
               model.addAttribute("result", true);
               model.addAttribute("responseService", result);
               //Gson gson = new Gson();
               //Account account = gson.fromJson(loginServiceResult, Account.class);
               //System.out.println(account.toString());

          } catch (Exception e){
               System.out.println("Feil i response: " + e);
          }
          return "myaccount";

     }

     @RequestMapping(value = "/account/login", method = RequestMethod.POST)
     public String login(@ModelAttribute("username") String username, @ModelAttribute("password") String password,
                         Model model) {
          logger.info("AccountController login() invoked: ");
          String passwordEncoded = "";
          try {
               passwordEncoded = URLEncoder.encode(password, "UTF-8");
          } catch(Exception e) {
               System.out.println("Error in password encoding: " + e);
          }
          String loginAccountService = "http://localhost:4444/account/login/" +
                  username + "/" + passwordEncoded;
          try{
               //Connecting to AccountService microservice
               URL url = new URL(loginAccountService);
               HttpURLConnection requestLogin = (HttpURLConnection) url.openConnection();
               requestLogin.connect();
               logger.info("AccountController login() connection established: ");

               JsonParser jp = new JsonParser();

               JsonElement loginServiceResult = jp.parse(new InputStreamReader((InputStream) requestLogin.getContent()));
               // ----------------------------------------------
               // Her må det lages en slags "session"
               // ----------------------------------------------
               System.out.println("Test: " + loginServiceResult.toString());
               if(!loginServiceResult.toString().equals("invalid")) {
                    Gson gson = new Gson();
                    Account account = gson.fromJson(loginServiceResult, Account.class);
                    System.out.println(account.toString());
                    model.addAttribute("fname", account.getFname());
                    model.addAttribute("lname", account.getLname());
                    model.addAttribute("phone", account.getPhone());
                    model.addAttribute("address", account.getAddress());
                    model.addAttribute("postal", account.getPostal());
                    model.addAttribute("city", account.getCity());
                    model.addAttribute("country", account.getCountry());
                    return "accountOverview";
               }
               return "myaccount";


          } catch (Exception e){
               System.out.println("Feil i response: " + e);
          }

          return "myaccount";
     }
}
