package com.surfholicdb.surfholicdb.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.ui.ModelMap;

public interface MainService {

    HashMap<String, Object> getWaveApi(HashMap<String, Object> region) throws IOException, ParseException;
}
