package com.studentmarket.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.studentmarket.models.Account;
import com.studentmarket.models.Product;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Logger;

/**
 * Created by Andreas on 5/27/2017.
 */

@Controller
public class ProductController {

    protected Logger logger = Logger.getLogger(ProductController.class
            .getName());

    //Start when the home page is running to load the latest 20 products NOT FINISHED
    public void getLatestProducts(){
        logger.info("ProductController getLatestProducts() invoked: ");

        String latestProducts = "http://localhost:3333/product/all";

        try{
            URL url = new URL(latestProducts);
            HttpURLConnection requestProducts = (HttpURLConnection) url.openConnection();
            requestProducts.connect();
            System.out.println("Koblet opp mot service: " + requestProducts.toString());

            JsonParser jp = new JsonParser();
            JsonElement productServiceResult = jp.parse(new InputStreamReader((InputStream) requestProducts.getContent()));
            System.out.println(productServiceResult);
            Gson gson = new Gson();
            Product product = gson.fromJson(productServiceResult, Product.class);
            System.out.println(product.toString());

            logger.info("ProductController getLatestProducts(): received latest products ");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Runs when user press search bar button from homepage
    @RequestMapping(value = "/product/search", method = RequestMethod.POST)
    public String productSearch(@ModelAttribute("search") String search,
                       Model model){

        logger.info("ProductController productSearch() invoked: " + search);

        String searchProduct = "http://localhost:3333/product/search/"+search;

        try{
            URL url = new URL(searchProduct);
            HttpURLConnection requestProduct = (HttpURLConnection) url.openConnection();
            requestProduct.connect();

            System.out.println("Koblet opp mot service: " + requestProduct.toString());

            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser();
            JsonElement productServiceResult = jp.parse(new InputStreamReader((InputStream) requestProduct.getContent()));

            System.out.println("Dette ble motatt fra service: " + productServiceResult);
            logger.info("ProductController productSearch() received from service: " + search);

            model.addAttribute("result", true);
            model.addAttribute("productService", productServiceResult);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "responseService";
    }

    //Create new product
    @RequestMapping(value ="/product/create/new", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("type") String type,
                                @ModelAttribute("title") String title,
                                @ModelAttribute("category") String category,
                                @ModelAttribute("state") String state,
                                @ModelAttribute("description") String description,
                                @ModelAttribute("price") String price,
                                @ModelAttribute("streetadr") String streetadr,
                                @ModelAttribute("postalcode") String postalcode,
                                @ModelAttribute("city") String city,
                                @ModelAttribute("country") String country,
                                @ModelAttribute("phone") String phone,
                                Model model) {

        logger.info("ProductController createProduct() invoked: " + title);

        String createProdcut = "";
        boolean doProductExist = false;

        try{
            String parameters = URLEncoder.encode(title, "UTF-8") +
                          "/" + URLEncoder.encode(category, "UTF-8") +
                          "/" + type + "/" + state + "/" + price +
                          "/" + URLEncoder.encode(description, "UTF-8") +
                          "/" + phone + "/" + URLEncoder.encode(streetadr, "UTF-8") +
                          "/" + postalcode + "/" + URLEncoder.encode(city, "UTF-8") +
                          "/" + URLEncoder.encode(country, "UTF-8");

            createProdcut = "http://localhost:3333/product/create/" + parameters;
            logger.info("ProductController createProduct() sending parameters: " + parameters);
        } catch (Exception e) {
            System.out.println("Encoding error: " + e);
        }

        try{
            URL url = new URL(createProdcut);
            HttpURLConnection requestProduct = (HttpURLConnection) url.openConnection();
            requestProduct.connect();

            System.out.println("Koblet opp mot service: " + requestProduct.toString());

            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser();
            JsonElement productServiceResult = jp.parse(new InputStreamReader((InputStream) requestProduct.getContent()));
            System.out.println(productServiceResult);
            doProductExist = Boolean.parseBoolean(productServiceResult.toString());

            String result = doProductExist ? "Product succesfully created" : "prodcut already exist";

            logger.info("ProductController createProduct() created: " + title);

            model.addAttribute("result", true);
            model.addAttribute("responseService", result);

        } catch (Exception e) {
            System.out.println("Encoding error: " + e);
        }

        return "createProduct";
    }


}
























