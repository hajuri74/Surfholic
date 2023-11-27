package com.surfholicdb.surfholicdb.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.surfholicdb.surfholicdb.service.MainService;

@Service("MainService")
public class MainServiceImpl implements MainService{

    @Override
    public HashMap<String, Object> getWaveApi(HashMap<String, Object> region) throws IOException, ParseException {
        String api_key = "tnJL6JvtpR6hTixEPtaE6w=="; //바다누리 api
        String api_key2 = "E%2FnPdqd8cZACYHeE%2Bh%2FcNgHWvp4Ln%2BH%2BgB3974M%2BXnUn2F2t60lWQlydLUTILRc02kbF90caKUFq5NOnf1ncJg%3D%3D"; //공공데이터 기상청_전국 해수욕장 날씨 조회서비스
        LocalDate now = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        String today = now.format(format);
        String todayWh = now.format(format);

        // 현재 시간        
        LocalTime nowTime = LocalTime.now();
        DateTimeFormatter formatHours = DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter formatMinute = DateTimeFormatter.ofPattern("mm");
        Integer time = Integer.parseInt(nowTime.format(formatHours));
        Integer timeMin = Integer.parseInt(nowTime.format(formatMinute));
        String hours = time < 10 ? 0+String.valueOf(time-1) : String.valueOf(time-1);
        String minute = timeMin < 10 ? 0+String.valueOf(timeMin-1) : String.valueOf(timeMin-1);

        String base_time = "0000";
        if(time>=2 && time<5){
            base_time = "0200";
        }else if(time>=5 && time<8){
            base_time = "0500";
        }else if(time>=8 && time<11){
            base_time = "0800";
        }else if(time>=11 && time<14){
            base_time = "1100";
        }else if(time>=14 && time<17){
            base_time = "1400";
        }else if(time>=17 && time<20){
            base_time = "1700";
        }else if(time>=20 && time<23){
            base_time = "2000";
        }else{
            base_time = "2300";
            LocalDate yesterday= now.minusDays(1);
            todayWh = yesterday.format(format);
        }


        //api: 바다누리(2년마다 키 갱신해야함) 
        String waveHightWeather = null; //유의파고&날씨
        String tidalBuWind = null; //풍향/풍속
        String tidalBu = null; //유속/유향
        String airTemp = null; //기온
        String waveTemp = null; //수온
        String tideObsPreTab = null; //조석(저조,고조)
        String [] urlnone = {"없음"};
        String regionResult = (String) region.get("region");

        if(region.get("region").equals("부산(송정)")){
            waveHightWeather = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?ServiceKey=" + api_key2 + "&dataType=JSON&base_date="+todayWh+"&base_time="+base_time+"&nx=100&ny=76";
            tidalBuWind = "http://www.khoa.go.kr/api/oceangrid/tidalBuWind/search.do?ServiceKey=" + api_key + "&ObsCode=TW_0090&Date=" + today + "&ResultType=json";
            tidalBu = "http://www.khoa.go.kr/api/oceangrid/tidalBu/search.do?ServiceKey=" + api_key + "&ObsCode=TW_0090&Date=" + today + "&ResultType=json";
            airTemp = "http://www.khoa.go.kr/api/oceangrid/tidalBuAirTemp/search.do?ServiceKey=" + api_key + "&ObsCode=TW_0090&Date=" + today + "&ResultType=json";
            waveTemp = "http://www.khoa.go.kr/api/oceangrid/tidalBuTemp/search.do?ServiceKey=" + api_key + "&ObsCode=TW_0090&Date=" + today + "&ResultType=json";
            tideObsPreTab = "http://www.khoa.go.kr/api/oceangrid/tideObsPreTab/search.do?ServiceKey=" + api_key + "&ObsCode=DT_0005&Date=" + today + "&ResultType=json";
            String [] url = {waveHightWeather,tidalBuWind,tidalBu,airTemp,waveTemp,tideObsPreTab,regionResult};
            return resultApi(url);
        }else if(region.get("region").equals("강릉경포")){
            waveHightWeather = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?ServiceKey=" + api_key2 + "&dataType=JSON&base_date="+todayWh+"&base_time="+base_time+"&nx=94&ny=131";
            tidalBuWind = "http://www.khoa.go.kr/api/oceangrid/tidalBuWind/search.do?ServiceKey=" + api_key + "&ObsCode=TW_0089&Date=" + today + "&ResultType=json";
            tidalBu = "http://www.khoa.go.kr/api/oceangrid/tidalBu/search.do?ServiceKey=" + api_key + "&ObsCode=TW_0089&Date=" + today + "&ResultType=json";
            airTemp = "http://www.khoa.go.kr/api/oceangrid/tidalBuAirTemp/search.do?ServiceKey=" + api_key + "&ObsCode=TW_0089&Date=" + today + "&ResultType=json";
            waveTemp = "http://www.khoa.go.kr/api/oceangrid/tidalBuTemp/search.do?ServiceKey=" + api_key + "&ObsCode=TW_0089&Date=" + today + "&ResultType=json";
            tideObsPreTab = "http://www.khoa.go.kr/api/oceangrid/tideObsPreTab/search.do?ServiceKey=" + api_key + "&ObsCode=SO_0733&Date=" + today + "&ResultType=json";
            String [] url = {waveHightWeather,tidalBuWind,tidalBu,airTemp,waveTemp,tideObsPreTab,regionResult};
            return resultApi(url);
        }else if(region.get("region").equals("강릉망상")){
            waveHightWeather = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?ServiceKey=" + api_key2 + "&dataType=JSON&base_date="+todayWh+"&base_time="+base_time+"&nx=95&ny=129";
            tidalBuWind = "http://www.khoa.go.kr/api/oceangrid/tidalBuWind/search.do?ServiceKey=" + api_key + "&ObsCode=TW_0094&Date=" + today + "&ResultType=json";
            tidalBu = "http://www.khoa.go.kr/api/oceangrid/tidalBu/search.do?ServiceKey=" + api_key + "&ObsCode=TW_0094&Date=" + today + "&ResultType=json";
            airTemp = "http://www.khoa.go.kr/api/oceangrid/tidalBuAirTemp/search.do?ServiceKey=" + api_key + "&ObsCode=TW_0094&Date=" + today + "&ResultType=json";
            waveTemp = "http://www.khoa.go.kr/api/oceangrid/tidalBuTemp/search.do?ServiceKey=" + api_key + "&ObsCode=TW_0094&Date=" + today + "&ResultType=json";
            tideObsPreTab = "http://www.khoa.go.kr/api/oceangrid/tideObsPreTab/search.do?ServiceKey=" + api_key + "&ObsCode=SO_0733&Date=" + today + "&ResultType=json";
            String [] url = {waveHightWeather,tidalBuWind,tidalBu,airTemp,waveTemp,tideObsPreTab,regionResult};
            return resultApi(url);
        }else if(region.get("region").equals("제주월정")){
            waveHightWeather = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?ServiceKey=" + api_key2 + "&dataType=JSON&base_date="+todayWh+"&base_time="+base_time+"&nx=59&ny=38";
            tidalBuWind = "http://www.khoa.go.kr/api/oceangrid/tidalBuWind/search.do?ServiceKey=" + api_key + "&ObsCode=KG_0028&Date=" + today + "&ResultType=json";
            tidalBu = "http://www.khoa.go.kr/api/oceangrid/tidalBu/search.do?ServiceKey=" + api_key + "&ObsCode=KG_0028&Date=" + today + "&ResultType=json";
            airTemp = "http://www.khoa.go.kr/api/oceangrid/tidalBuAirTemp/search.do?ServiceKey=" + api_key + "&ObsCode=KG_0028&Date=" + today + "&ResultType=json";
            waveTemp = "http://www.khoa.go.kr/api/oceangrid/tidalBuTemp/search.do?ServiceKey=" + api_key + "&ObsCode=KG_0028&Date=" + today + "&ResultType=json";
            tideObsPreTab = "http://www.khoa.go.kr/api/oceangrid/tideObsPreTab/search.do?ServiceKey=" + api_key + "&ObsCode=DT_0004&Date=" + today + "&ResultType=json";
            String [] url = {waveHightWeather,tidalBuWind,tidalBu,airTemp,waveTemp,tideObsPreTab,regionResult};
            return resultApi(url);
        }else if(region.get("region").equals("포항")){
            waveHightWeather = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?ServiceKey=" + api_key2 + "&dataType=JSON&base_date="+todayWh+"&base_time="+base_time+"&nx=102&ny=96";
            tidalBuWind = "http://www.khoa.go.kr/api/oceangrid/tideObsWind/search.do?ServiceKey=" + api_key + "&ObsCode=DT_0091&Date=" + today + "&ResultType=json";
            tidalBu = "http://www.khoa.go.kr/api/oceangrid/tidalHfRadar/search.do?ServiceKey=" + api_key + "&ObsCode=HF_0071&Date=" + today+hours+ "&ResultType=json";
            airTemp = "http://www.khoa.go.kr/api/oceangrid/tideObsAirTemp/search.do?ServiceKey=" + api_key + "&ObsCode=DT_0091&Date=" + today + "&ResultType=json";
            waveTemp = "http://www.khoa.go.kr/api/oceangrid/tideObsTemp/search.do?ServiceKey=" + api_key + "&ObsCode=DT_0091&Date=" + today + "&ResultType=json";
            tideObsPreTab = "http://www.khoa.go.kr/api/oceangrid/tideObsPreTab/search.do?ServiceKey=" + api_key + "&ObsCode=DT_0901&Date=" + today + "&ResultType=json";
            String [] url = {waveHightWeather,tidalBuWind,tidalBu,airTemp,waveTemp,tideObsPreTab,regionResult};
            return resultApi(url);
        }else if(region.get("region").equals("울산")){
            waveHightWeather = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?ServiceKey=" + api_key2 + "&dataType=JSON&base_date="+todayWh+"&base_time="+base_time+"&nx=102&ny=80";
            tidalBuWind = "http://www.khoa.go.kr/api/oceangrid/tideObsWind/search.do?ServiceKey=" + api_key + "&ObsCode=DT_0020&Date=" + today + "&ResultType=json";
            tidalBu = "http://www.khoa.go.kr/api/oceangrid/tidalHfRadar/search.do?ServiceKey=" + api_key + "&ObsCode=HF_0063&Date=" + today+hours + "&ResultType=json";
            airTemp = "http://www.khoa.go.kr/api/oceangrid/tideObsAirTemp/search.do?ServiceKey=" + api_key + "&ObsCode=DT_0020&Date=" + today + "&ResultType=json";
            waveTemp = "http://www.khoa.go.kr/api/oceangrid/tideObsTemp/search.do?ServiceKey=" + api_key + "&ObsCode=DT_0020&Date=" + today + "&ResultType=json";
            tideObsPreTab = "http://www.khoa.go.kr/api/oceangrid/tideObsPreTab/search.do?ServiceKey=" + api_key + "&ObsCode=DT_0020&Date=" + today + "&ResultType=json";
            String [] url = {waveHightWeather,tidalBuWind,tidalBu,airTemp,waveTemp,tideObsPreTab,regionResult};
            return resultApi(url);
        }else if(region.get("region").equals("다대포")){
            waveHightWeather = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?ServiceKey=" + api_key2 + "&dataType=JSON&base_date="+todayWh+"&base_time="+base_time+"&nx=96&ny=73";
            tidalBuWind = "http://www.khoa.go.kr/api/oceangrid/tideObsWind/search.do?ServiceKey=" + api_key + "&ObsCode=DT_0056&Date=" + today + "&ResultType=json";
            tidalBu = "http://www.khoa.go.kr/api/oceangrid/tidalCurrentArea/search.do?ServiceKey=" + api_key + "&Date=" + today+ "&Hour=" + hours + "&Minute=" + minute + "&MaxX=129&MinX=128&MaxY=36&MinY=35&ResultType=json";
            airTemp = "http://www.khoa.go.kr/api/oceangrid/tideObsAirTemp/search.do?ServiceKey=" + api_key + "&ObsCode=DT_0056&Date=" + today + "&ResultType=json";
            waveTemp = "http://www.khoa.go.kr/api/oceangrid/tideObsTemp/search.do?ServiceKey=" + api_key + "&ObsCode=DT_0005&Date=" + today + "&ResultType=json";
            tideObsPreTab = "http://www.khoa.go.kr/api/oceangrid/tideObsPreTab/search.do?ServiceKey=" + api_key + "&ObsCode=DT_0056&Date=" + today + "&ResultType=json";
            String [] url = {waveHightWeather,tidalBuWind,tidalBu,airTemp,waveTemp,tideObsPreTab,regionResult};
            return resultApi(url);
        }

        return resultApi(urlnone);
    }

    private HashMap<String, Object> resultApi(String [] url) throws IOException, ParseException {
        HashMap<String, Object> waveApi = new HashMap<String, Object>();

            if(url[0] != null){
                HttpURLConnection con = (HttpURLConnection) new URL(url[0]).openConnection();
                con.setRequestMethod("GET");
                //con.setReadTimeout(1000);
                con.connect();
        
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
                String apiSource = br.readLine();
    
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(apiSource);
                
                JSONObject result = (JSONObject)jsonObject.get("response");
                JSONObject body = (JSONObject)result.get("body");

                if(body != null){
                    JSONObject items = (JSONObject)body.get("items");
                    JSONArray item = (JSONArray)items.get("item");
        
                    JSONObject skydata = (JSONObject)item.get(5);
                    JSONObject sky2data = (JSONObject)item.get(6);
                    JSONObject waveHe = (JSONObject)item.get(8);
                    Object sky1 = skydata.get("fcstValue");
                    Object sky2 = sky2data.get("fcstValue");
                    Object wave_height = waveHe.get("fcstValue");
                    Object sky = "";
                    if(sky1.equals("1") && sky2.equals("0")){
                        sky = "맑음";
                    }else if(sky1.equals("3") && sky2.equals("0")){
                        sky = "구름많음";
                    }else if(sky1.equals("4") && sky2.equals("0")){
                        sky = "흐림";
                    }else if(sky2.equals("1")){
                        sky = "비";
                    }else if(sky2.equals("2")){
                        sky = "비/눈";
                    }else if(sky2.equals("3")){
                        sky = "눈";
                    }else if(sky2.equals("4")){
                        sky = "소나기";
                    }
    
                    waveApi.put("sky", sky);
                    waveApi.put("wave_height", wave_height);
                }else{
                    waveApi.put("sky", "없음");
                    waveApi.put("wave_height", "없음");
                }

                br.close();
                con.disconnect();
            }
            
            if(url[1] != null){
                HttpURLConnection con = (HttpURLConnection) new URL(url[1]).openConnection();
                con.setRequestMethod("GET");
                //con.setReadTimeout(1000);
                con.connect();
        
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
                String apiSource = br.readLine();
    
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(apiSource);

                JSONObject result = (JSONObject)jsonObject.get("result");
                JSONArray data = (JSONArray)result.get("data");

                if(data != null){
                    int dataIndex= data.size() -1;
                    JSONObject dataLast = (JSONObject)data.get(dataIndex);
                    Object wind_speed = dataLast.get("wind_speed");
                    Object wind_dir = dataLast.get("wind_dir");
                    waveApi.put("wind_speed", wind_speed);
                    waveApi.put("wind_dir", wind_dir);
                }else{
                    waveApi.put("wind_speed", "없음");
                    waveApi.put("wind_dir", "없음");
                }
    
                br.close();
                con.disconnect();
            }
            if(url[2] != null){
                HttpURLConnection con = (HttpURLConnection) new URL(url[2]).openConnection();
                con.setRequestMethod("GET");
                //con.setReadTimeout(1000);
                con.connect();
        
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
                String apiSource = br.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(apiSource);

                JSONObject result = (JSONObject)jsonObject.get("result");
                JSONArray data = (JSONArray)result.get("data");
                System.out.println(url[6]);
                if(data != null){
                    if(!url[6].equals("다대포")){
                        int dataIndex= data.size() -1;
                        JSONObject dataLast = (JSONObject)data.get(dataIndex);
                        Object current_speed = dataLast.get("current_speed");
                        Object current_direct = dataLast.get("current_direct");
                        waveApi.put("current_speed", current_speed);
                        waveApi.put("current_direct", current_direct);
                    }else{
                        int dataIndex= data.size() -1;
                        JSONObject dataLast = (JSONObject)data.get(dataIndex);
                        Object current_speed = dataLast.get("current_speed");
                        Object current_direct = dataLast.get("current_dir");
                        waveApi.put("current_speed", current_speed);
                        waveApi.put("current_direct", current_direct);
                    }
                }else{
                    waveApi.put("current_speed", "없음");
                    waveApi.put("current_direct", "없음");
                }
                
                br.close();
                con.disconnect();   
            }
            
            if(url[3] != null){
                HttpURLConnection con = (HttpURLConnection) new URL(url[3]).openConnection();
                con.setRequestMethod("GET");
                //con.setReadTimeout(1000);
                con.connect();
        
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
                String apiSource = br.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(apiSource);

                JSONObject result = (JSONObject)jsonObject.get("result");
                JSONArray data = (JSONArray)result.get("data");
                
                if(data != null){
                    int dataIndex= data.size() -1;
                    JSONObject dataLast = (JSONObject)data.get(dataIndex);
                    Object air_temp = dataLast.get("air_temp");
                    waveApi.put("air_temp", air_temp);
                }else{
                    waveApi.put("air_temp", "없음");
                }

                br.close();
                con.disconnect();
            }
            
            if(url[4] != null){
                HttpURLConnection con = (HttpURLConnection) new URL(url[4]).openConnection();
                con.setRequestMethod("GET");
                //con.setReadTimeout(1000);
                con.connect();
        
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
                String apiSource = br.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(apiSource);

                JSONObject result = (JSONObject)jsonObject.get("result");
                JSONArray data = (JSONArray)result.get("data");

                if(data != null){
                    int dataIndex= data.size() -1;
                    JSONObject dataLast = (JSONObject)data.get(dataIndex);
                    Object water_temp = dataLast.get("water_temp");
                    waveApi.put("water_temp", water_temp);
                }else{
                    waveApi.put("water_temp", "없음");
                }

                br.close();
                con.disconnect();
            }
            
            if(url[5] != null){
                HttpURLConnection con = (HttpURLConnection) new URL(url[5]).openConnection();
                con.setRequestMethod("GET");
                //con.setReadTimeout(1000);
                con.connect();
        
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
                String apiSource = br.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(apiSource);

                JSONObject result = (JSONObject)jsonObject.get("result");
                JSONArray data = (JSONArray)result.get("data");
                if(data != null){
                    JSONObject dataLast1 = (JSONObject)data.get(0);
                    JSONObject dataLast2 = (JSONObject)data.get(1);
                    JSONObject dataLast3 = (JSONObject)data.get(2);
                    JSONObject dataLast4 = (JSONObject)data.get(3);
                    Object low_code1 = dataLast1.get("tph_level");
                    Object low_time1 = dataLast1.get("tph_time");
                    Object hl_code1 = dataLast2.get("tph_level");
                    Object hl_time1 = dataLast2.get("tph_time");
                    Object low_code2 = dataLast3.get("tph_level");
                    Object low_time2 = dataLast3.get("tph_time");
                    Object hl_code2 = dataLast4.get("tph_level");
                    Object hl_time2 = dataLast4.get("tph_time");
                    waveApi.put("low_code1", low_code1);
                    waveApi.put("low_time1", low_time1);
                    waveApi.put("hl_code1", hl_code1);
                    waveApi.put("hl_time1", hl_time1);
                    waveApi.put("low_code2", low_code2);
                    waveApi.put("low_time2", low_time2);
                    waveApi.put("hl_code2", hl_code2);
                    waveApi.put("hl_time2", hl_time2);
                }else{
                    waveApi.put("low_code1", "없음");
                    waveApi.put("low_time1", "없음");
                    waveApi.put("hl_code1", "없음");
                    waveApi.put("hl_time1", "없음");
                    waveApi.put("low_code2", "없음");
                    waveApi.put("low_time2", "없음");
                    waveApi.put("hl_code2", "없음");
                    waveApi.put("hl_time2", "없음");
                }
                
                br.close();
                con.disconnect();
            }

        return waveApi;
    }



    
}
