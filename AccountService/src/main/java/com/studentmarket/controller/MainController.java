package com.studentmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

     @RequestMapping("/")
     @ResponseBody
     public String index() {
          return "Made by Andreas & Andreas";
     }

}
