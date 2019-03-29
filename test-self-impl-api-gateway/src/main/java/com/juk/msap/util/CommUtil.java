package com.juk.msap.util;

import com.juk.msap.test.TestVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Service
public class CommUtil {
     public HttpURLConnection deliveryMicroService(String msUrlStr, JSONObject jsonObj, String USER_ID){
        URL msURL = null;
        HttpURLConnection httpConn = null;
        DataOutputStream wr = null;
        StringBuffer jsonBF = new StringBuffer();

        try {
            msURL = new URL(msUrlStr);
            httpConn = (HttpURLConnection)msURL.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");

            httpConn.setDoOutput(true);
            wr = new DataOutputStream(httpConn.getOutputStream());

            //테스트화면에서 입력한 값을 받아옴
            //if(testVO!=null && testVO.getUSER_ID()!=null && testVO.getUSER_ID().length()>0){
            if(USER_ID!=null && USER_ID.length()>0){
                JSONObject resData = new JSONObject();
                resData.put("UID", USER_ID);
                jsonBF.append(resData.toJSONString());
                System.out.println("test web wr wright: " + resData.toJSONString());
            }else {
                //실제 request stream에 data가 있는경우,
                //jsonBF.append(jsonObj.toJSONString());
                //테스트 data
                JSONObject resData = new JSONObject();
                resData.put("UID", "JUK");
                jsonBF.append(resData.toJSONString());
                System.out.println("wr wright: " + jsonBF.toString());
            }

            wr.write(jsonBF.toString().getBytes());
            wr.flush();
        }catch (MalformedURLException e) {
            System.out.println("2>>"+e.getMessage()); }
        catch (ProtocolException e) {
            System.out.println("3>>"+e.getMessage()); }
        catch (IOException e) {
            System.out.println("4>"+e.getMessage()); }
        finally{
            try { if(wr!=null) { wr.close(); } }
            catch (IOException e) {
                System.out.println("5>>"+e.getMessage());
            }
        }

        System.out.println("2, deliveryMicroService : " + msUrlStr);

        return httpConn;
    }

    public void recvMSRespData(HttpURLConnection httpConn, StringBuffer msRespData){
        BufferedReader br = null;

        try {
            int responseCode = httpConn.getResponseCode();
            if(responseCode==200) {
                br = new BufferedReader(new InputStreamReader(httpConn.getInputStream())); // 정상 호출
            } else {
                br = new BufferedReader(new InputStreamReader(httpConn.getErrorStream()));  // 에러 발생
            }
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                msRespData.append(inputLine);
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("6>>"+ msRespData.toString()+ ","+e.getMessage());
            JSONObject errObj = new JSONObject();
            errObj.put("ERROR",e.getMessage()); //400~ 500~
            msRespData.append(errObj.toJSONString());
        }finally{
            try{ if(br!=null){ br.close(); }}
            catch(Exception e){e.printStackTrace();}
        }

        System.out.println("3, recvMSRespData : " + msRespData.toString());
    }

    public void sendResultToUser(HttpServletResponse response, StringBuffer msRespData){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        OutputStreamWriter userWR = null;
        try {
            userWR = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
            userWR.write(msRespData.toString());
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

}
