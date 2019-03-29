package com.juk.msap.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ApiGWDao {
    @Autowired
    @Resource(name="sqlSessionTemplate")
    private SqlSessionTemplate sqlSnTplt;

    public String getApiPort(String apiId){
        //Map map = new HashMap();
        //map.put("API_ID",apiId);
        String API_WEB_PORT = sqlSnTplt.selectOne("API_GW.selectApiPortById", apiId);
        System.out.println("call getApiPort!!!:"+ API_WEB_PORT);
        return API_WEB_PORT;
    }
}
