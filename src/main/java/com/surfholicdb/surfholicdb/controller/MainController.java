package com.surfholicdb.surfholicdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.surfholicdb.surfholicdb.service.MainService;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping("/")
    public ModelAndView main() {
        
        mainService.getSeaInfo();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        return mv;
    } 
}
