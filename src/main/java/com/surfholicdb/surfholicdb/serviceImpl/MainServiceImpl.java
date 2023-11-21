package com.surfholicdb.surfholicdb.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.stereotype.Service;

import com.surfholicdb.surfholicdb.service.MainService;

@Service("MainService")
public class MainServiceImpl implements MainService{

    // 인증키 
    String key = "tnJL6JvtpR6hTixEPtaE6w==";

    @Override
    public List<String> getSeaInfo(String obsCode) {
        List<String> seaResult = new ArrayList<String>();

        // 관측소 번호 obsCode
        //String obsCode = obsCode;

        //DateType
        //String dateType = "";

        //조위관측소 실측 수온, 염분, 기온, 파고, 바람(풍향), 조류예보(유향, 유속), 조석
        String[] dateType = {"tideObsTemp","tideObsSalt", "tideObsAirTemp", "obsWaveHight", "tideObsWind", "fcTidalCurrent", "tideObsPreTab"};

        LocalDate now = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDate = now.format(format);
        
        try {
            
            for (String dt : dateType) {
                URL url = new URL("http://www.khoa.go.kr/api/oceangrid/"+dt+"/search.do?ServiceKey="+key+"&ObsCode="+obsCode+"&Date="+currentDate+"&ResultType=json");
                BufferedReader bf;
                bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                String apiSource = bf.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(apiSource);
                JSONObject result = (JSONObject)jsonObject.get("result");
                JSONObject meta = (JSONObject)result.get("meta");

                //System.out.println(meta);

                JSONArray data = (JSONArray)result.get("data");
                int dataIndex= data.size() -1;
                JSONObject record_time = (JSONObject)data.get(dataIndex);
                JSONObject wave_height = (JSONObject)data.get(dataIndex);

                System.out.println(dt +"=="+ record_time + "와" + wave_height);
            }	

        } catch (Exception e) {
            e.printStackTrace();
        }
        return seaResult;
    }

    @Override
    public List<String> getDateApi() throws IOException{
        List<String> dateResult = new ArrayList<String>();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=E%2FnPdqd8cZACYHeE%2Bh%2FcNgHWvp4Ln%2BH%2BgB3974M%2BXnUn2F2t60lWQlydLUTILRc02kbF90caKUFq5NOnf1ncJg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("4", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20231113", "UTF-8")); /*‘21년 6월 28일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("2300", "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("55", "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

        // try {
        //     throw new IOException();

        // } catch (IOException e) {
		// 	e.printStackTrace();
		// }
        return dateResult;
    }

    @Override
    public List<String> getFutureDateApi() throws IOException{
        List<String> futureDateResult = new ArrayList<String>();//?serviceKey=9ED3DA27-EE19-4CCB-9877-8F76C187B0AB&resultType=json&mmaf=101&mmsi=994401578,994401597
        StringBuilder urlBuilder = new StringBuilder("http://marineweather.nmpnt.go.kr:8001/openWeatherNow.do"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=9ED3DA27-EE19-4CCB-9877-8F76C187B0AB"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("mmaf","UTF-8") + "=" + URLEncoder.encode("101", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("mmsi","UTF-8") + "=" + URLEncoder.encode("994401578", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),  "UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        return futureDateResult;
    }

    // @Override
    // public void getwindApi() throws IOException {
    //     String urlReq = "https://www.windguru.cz/widget-fcst-iframe.php?s=208542&m=100&mw=47&uid=wg_fwdg_208542_100_1700440455527&wj=ms&tj=c&waj=m&tij=cm&odh=0&doh=24&fhours=240&hrsm=2&vt=forecasts&lng=en&idbs=1&ts=2&p=WINDSPD,GUST,SMER,HTSGW,PERPW,DIRPW,SWELL1,SWDIR1,WVHGT,WVDIR,TMP,TMPE,CDC,APCP1s,SLP,RATING&hostname=127.0.0.1&url=http%3A%2F%2F127.0.0.1%3A8080%2F";
        
    //     URL url = new URL(urlReq);
    //     HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //     conn.setRequestMethod("GET");
    //     BufferedReader rd;
    //     if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
    //         rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    //     } else {
    //         rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),  "UTF-8"));
    //     }
    //     StringBuilder sb = new StringBuilder();
    //     String line;
    //     while ((line = rd.readLine()) != null) {
    //         sb.append(line);
    //     }
    //     rd.close();
    //     conn.disconnect();
    //     System.out.println(sb.toString());
    // }


    
}
