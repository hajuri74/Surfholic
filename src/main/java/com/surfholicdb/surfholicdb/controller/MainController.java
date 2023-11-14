package com.surfholicdb.surfholicdb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.surfholicdb.surfholicdb.service.MainService;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping("/")
    public ModelAndView main() throws IOException {
        List<String> sea = new ArrayList<>();
        List<String> date = new ArrayList<>();
        List<String> future = new ArrayList<>();

        //sea = mainService.getSeaInfo();
        //date = mainService.getDateApi();
        //future = mainService.getFutureDateApi();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        return mv;
    }

    @RequestMapping("/seadata")
    public ModelAndView obsCode(@RequestParam String obsCode) throws IOException {
        List<String> sea = new ArrayList<>();
        List<String> date = new ArrayList<>();
        List<String> future = new ArrayList<>();

        //System.out.println(obsCode);
        sea = mainService.getSeaInfo(obsCode);
        //date = mainService.getDateApi();
        //future = mainService.getFutureDateApi();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        return mv;
    }
}
