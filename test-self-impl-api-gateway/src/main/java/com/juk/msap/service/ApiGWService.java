package com.juk.msap.service;

import com.juk.msap.dao.ApiGWDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiGWService {
    @Autowired
    ApiGWDao apiDao;

    public String getApiPort(String apiUri){
        //apiUri : /acps/v1/user/name
        System.out.println("call getApiPort:"+apiUri);
        String apiId = "";
        String apiPort = null;

        try {
            //if(apiUri.startsWith("/")){apiUri = apiUri.substring(1, apiUri.length()); }
            String[] arr = apiUri.split("/");
            for (int i=0; i < arr.length; ++i) {
                String token = arr[i];
                if(token!=null && token.length()>0){
                    apiId += token + "_";
                }
            }
            if(apiId.endsWith("_")){ apiId = apiId.substring(0, apiId.length()-1); }
            System.out.println("call getApiPort apiId:"+apiId);

            //1.db조회
            apiPort = apiDao.getApiPort(apiId); //acps_v1_user_name

            //2.결과응답
            System.out.println("apiPort:" + apiPort);
        }catch(Exception e){
            System.out.println("getApiPort Exception:" + e.getMessage());
        }

        return apiPort;
    }
}
