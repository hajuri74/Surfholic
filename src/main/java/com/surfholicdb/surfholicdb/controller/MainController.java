package com.surfholicdb.surfholicdb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.surfholicdb.surfholicdb.service.MainService;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    //@CrossOrigin(origins = "http://localhost:8080", maxAge=3600)
    @RequestMapping("/")
    public String main() {
        return "main.html";
    }

    @RequestMapping("/wave")
    public String wave(@RequestParam HashMap<String,Object> region, ModelMap model) throws IOException, ParseException{
        HashMap<String,Object> waveApi = mainService.getWaveApi(region);
        System.out.println(waveApi.toString());
        model.addAttribute("waveApis",waveApi);
        model.addAttribute("region",region.get("region"));
        return "wave";
    }

    @RequestMapping("/wave2")
    public String wave2(){
        return "wave2";
    }

}
