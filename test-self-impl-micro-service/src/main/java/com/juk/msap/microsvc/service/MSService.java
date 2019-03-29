package com.juk.msap.microsvc.service;


import com.juk.msap.microsvc.dao.MSDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Repository //(name = "microService")
public class MSService {
    @Autowired
    private JdbcTemplate jdbcTple;
    @Autowired
    private MSDao apiDao;

    public String getUserName(String userId){
        System.out.println("call getUserName:"+userId);
        String name = null;

        try {
            //1.db조회
            //String query = "select name from user where uid = ?";
            //name = jdbcTple.queryForObject(query, new Object[]{userId}, String.class);
            name = apiDao.selectUserName(userId);

            //2.결과응답
            System.out.println("name:" + name);
        }catch(Exception e){
            System.out.println("getUserName Exception:" + e.getMessage());
        }

        return name;
    }

    public String getUserNamInsertDate(String userId){
        System.out.println("call getUserNamInsertDate:"+userId);
        String dateStr = null;

        try {
            Date date = apiDao.selectUserInsertDate(userId);

            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateStr = transFormat.format(date);

            //2.결과응답
            System.out.println("in-date:" + dateStr);
        }catch(Exception e){
            System.out.println("getUserNamInsertDate Exception:" + e.getMessage());
        }

        return dateStr;
    }
}
