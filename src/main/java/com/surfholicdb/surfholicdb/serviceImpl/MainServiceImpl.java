package com.surfholicdb.surfholicdb.serviceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.stereotype.Service;

import com.surfholicdb.surfholicdb.service.MainService;

@Service("MainService")
public class MainServiceImpl implements MainService{

    // 인증키 
    String key = "";

    @Override
    public void getSeaInfo() {
        try {
            
    		URL url = new URL("http://www.khoa.go.kr/api/oceangrid/obsWaveHight/search.do?ServiceKey=tnJL6JvtpR6hTixEPtaE6w==&ObsCode=TW_0090&Date=20231110&ResultType=json");

    		BufferedReader bf;

    		bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

    		String apiSource = bf.readLine();

        	JSONParser jsonParser = new JSONParser();
        	JSONObject jsonObject = (JSONObject)jsonParser.parse(apiSource);
        	JSONObject result = (JSONObject)jsonObject.get("result");
        	JSONObject meta = (JSONObject)result.get("meta");

            System.out.println(meta);

            JSONArray data = (JSONArray)result.get("data");
        	JSONObject record_time = (JSONObject)data.get(0);
        	JSONObject wave_height = (JSONObject)data.get(1);

            System.out.println(record_time + "와ㄴㄴㄴㄴㄴㄴㄴㄴㄴ " + wave_height);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
