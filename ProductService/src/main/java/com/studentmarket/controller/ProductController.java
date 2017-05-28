package com.studentmarket.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.studentmarket.models.Product;
import com.studentmarket.models.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * Created by Andreas on 5/26/2017.
 */

@RestController
public class ProductController {

    protected Logger logger = Logger.getLogger(ProductController.class
            .getName());

    /*@RequestMapping(value="/product/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() {
        Product dummy = new Product("Webprogrammering", "Book", "", "andreas.krutnes@gmail.com", "Kulsrudgutua 2c",
                new Integer(2022), "Gjerdrum", "Norway");
        Gson gson = new Gson();
        return gson.toJson(dummy);
    }*/



    @RequestMapping(value = "/product/search/{search}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String productSearch(@PathVariable String search){

        logger.info("ProductController productSearch() invoked: " + search);

        return search;
    }

    @RequestMapping("/products/create/{title}/{category}/{type}/{state}/{price}/" +
            "{description}/{phone}/{address}/{postal}/{city}/{country}")
    public Boolean create(@PathVariable String title, @PathVariable String category, @PathVariable String type,
                         @PathVariable String state, @PathVariable String price, @PathVariable String description,
                         @PathVariable String phone, @PathVariable String address, @PathVariable String postal,
                         @PathVariable String city, @PathVariable String country) {

        logger.info("ProductController create() : " + title);

        Product product = new Product(title, category, type, state, price, description,
                phone, address, postal, city, country);

        if(productDao.findByTitle(title) == null){
            logger.info("ProductController create() Created: " + title);
            productDao.save(product);
            return true;
        }

        return false;
    }

    @RequestMapping("/products/create/new")
    @ResponseBody
    public String create(String title) {
        Product product = null;
        try {
            product = new Product(title);
            productDao.save(product);
        }
        catch (Exception ex) {
            return "Error creating the product: " + ex.toString();
        }
        return "Product succesfully created! (id = " + product.getId() + ")";
    }

    /**
     * GET /delete  --> Delete the product having the passed id.
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            Product product = new Product(id);
            productDao.delete(product);
        }
        catch (Exception ex) {
            return "Error deleting the product:" + ex.toString();
        }
        return "Product succesfully deleted!";
    }

    /**
     * GET /get-by-email  --> Return the id for the product having the passed
     * email.
     */
    @RequestMapping("/get-by-title")
    @ResponseBody
    public String getByTitle(String title) {
        String productId = "";
        try {
            Product product = productDao.findByTitle(title);
            productId = String.valueOf(product.getId());
        }
        catch (Exception ex) {
            return "Product not found";
        }
        return "The product id is: " + productId;
    }

    /**
     * GET /update  --> Update the email and the name for the product in the
     * database having the passed id.
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateProduct(long id, String title, String category, String type, String state, String price, String description,
                                String phone, String address, String postal, String city, String country) {
        try {
            Product product = productDao.findOne(id);
            product.setTitle(title);
            product.setCategory(category);
            product.setType(type);
            product.setState(state);
            product.setPrice(price);
            product.setDescription(description);
            product.setPhone(phone);
            product.setAddress(address);
            product.setPostal(postal);
            product.setCity(city);
            product.setCountry(country);
            productDao.save(product);
        }
        catch (Exception ex) {
            return "Error updating the product: " + ex.toString();
        }
        return "User succesfully updated!";
    }

    // Private fields

    @Autowired
    private ProductDao productDao;

    /*@RequestMapping("/products/{productId}")
    @ResponseBody
    public Product byID(@PathVariable("productId") int productId) {
        Product product = productDao.findbyTitle(productId);

        if(product == null)
            throw new ProductNotFoundException(productId);
        else {
            return product;
        }
    }

    /*@RequestMapping("/products/all")
    public Product byID() {
        Product product = productDao.findAll();

        if(product == null)
            throw new ProductNotFoundException("All");
        else {
            return product;
        }
    }*/
}
