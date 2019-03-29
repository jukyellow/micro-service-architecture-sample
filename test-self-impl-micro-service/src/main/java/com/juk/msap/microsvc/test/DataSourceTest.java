package com.juk.msap.microsvc.test;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:/main/web/WEB-INF/applicationContext.xml"})
public class DataSourceTest {

    //@Inject
    @Autowired
    private MysqlDataSource ds;

    @Test
    public void testConection() throws Exception{
        try(Connection con = ds.getConnection()){
            System.out.println(con);
        }catch(Exception e) {
            e.printStackTrace();
        }//try_
    }//test_

}