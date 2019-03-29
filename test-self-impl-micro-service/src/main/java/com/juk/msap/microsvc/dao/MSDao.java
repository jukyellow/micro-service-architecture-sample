package com.juk.msap.microsvc.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MSDao {
    @Autowired
    @Resource(name="sqlSessionTemplate")
    private SqlSessionTemplate sqlSnTplt;

    public String selectUserName(String userId){
        Map map = new HashMap();
        map.put("UID",userId);
        String name = sqlSnTplt.selectOne("TEST_SERVICE_USER.selectUserName", map);
        System.out.println("call ApiDao!!!:"+ name);
        return name;
    }

    public Date selectUserInsertDate(String userId){
        Map map = new HashMap();
        map.put("UID",userId);
        Date date = sqlSnTplt.selectOne("TEST_SERVICE_USER.selectUserInsertDate", map);
        System.out.println("call ApiDao!!!:"+ date);
        return date;
    }

}
