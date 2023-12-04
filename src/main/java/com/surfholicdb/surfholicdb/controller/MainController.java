package com.surfholicdb.surfholicdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String main() {
        return "main.html";
    }

    @RequestMapping("/wave2")
    public String wave2(){
        return "wave2";
    }

}
