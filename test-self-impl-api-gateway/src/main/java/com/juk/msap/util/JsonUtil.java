package com.juk.msap.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class JsonUtil {
    private static final JSONParser JSON_PARSER = new JSONParser();

    public JSONObject extractReqJson(HttpServletRequest request){
        JSONObject jsonObj = null;
        try {
            // 1. JSON data parse
            String requestText = readRequestData(request.getInputStream());
            jsonObj = parseJSONObject(requestText);
            if(jsonObj!=null){
                System.out.println(">>> " +jsonObj.toJSONString());
            }
        } catch (Exception e) {
            System.out.println("1>>> " +e.getMessage());
        }

        if(jsonObj!=null) {
            System.out.println("1, extractReqJson : " + jsonObj.toJSONString());
        }else{
            System.out.println("1, extractReqJson : jsonObj is null" );
        }

        return jsonObj;
    }

    /*
    private void sendResultToUser(HttpServletResponse response, String msRespData){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        OutputStreamWriter userWR = null;
        try {
            userWR = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
            userWR.write(msRespData);
            userWR.flush();
        }catch(Exception e){
            System.out.println("ee:"+e.getMessage());
        }finally {
            if (userWR != null) {
                try { userWR.close();}
                catch (IOException e) {e.printStackTrace();  }
            }
        }
    }*/

    public String readRequestData(InputStream is) throws Exception {
        String requestData = "";

        try {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    requestData += line;
                }
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
            //logger.debug("contents : " + requestData);
            System.out.println("contents : " + requestData);
        } catch (IOException e) {
            String errMsg = "API CONTENT 수신 오류 : " + e.getMessage();
            //logger.info(errMsg);
            System.out.println(errMsg);
            throw new Exception(errMsg);
        }

        return requestData;
    }

    public JSONObject parseJSONObject(String text) throws Exception {
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) JSON_PARSER.parse(text);
        } catch (ParseException pe) {
            String errMsg = "API CONTENT 파싱 오류 : " + pe.getMessage();
            //logger.info(errMsg, pe);
            System.out.println(errMsg);
            throw new Exception(errMsg);
        } catch (Exception e) {
            String errMsg = "API CONTENT 처리 오류 : " + e.getMessage();
            //logger.info(errMsg, e);
            System.out.println(errMsg);
        }

        return jsonObject;
    }
}
