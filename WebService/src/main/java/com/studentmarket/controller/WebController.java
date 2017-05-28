package com.studentmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

/**
 * Created by Andreas on 5/25/2017.
 */

@Controller
public class WebController {

    protected Logger logger = Logger.getLogger(WebController.class
            .getName());

    private int userId;

    protected ProductController productController;

    @RequestMapping("/")
    public String index(){

        //productController.getLatestProducts();

        logger.info("WebController index() invoked: ");

        return "index";
    }

    @RequestMapping("/myaccount")
    public String myAccount() {
        logger.info("WebController myAccount() invoked: ");
        return "myaccount";
    }

    @RequestMapping("/product/create")
    public String createProduct() {
        logger.info("WebController createProduct() invoked: ");
        return "createProduct";
    }

    @RequestMapping("/account/signup")
    public String createAccount() {
        logger.info("WebController createAccount() invoked: ");
        return "createAccount";
    }

}
