package com.juk.msap.microsvc.util;

import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class CommUtil {

    public void sendResultToUser(HttpServletResponse response, String msRespData){
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
    }

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

}
