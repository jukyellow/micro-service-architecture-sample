package com.juk.msap.microsvc.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class JsonUtil {
    @Autowired
    CommUtil commUtil;

    private static final JSONParser JSON_PARSER = new JSONParser();

    public JSONObject extractReqJson(HttpServletRequest request){
        JSONObject jsonObj = null;
        try {
            // 1. JSON data parse
            String requestText = commUtil.readRequestData(request.getInputStream());
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

    private JSONObject parseJSONObject(String text) throws Exception {
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
            throw new Exception(errMsg);
        }

        return jsonObject;
    }
}
