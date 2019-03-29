package com.example.demo.db;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootApplication
@RunWith(SpringRunner.class)
@SpringBootTest 
//@ContextConfiguration(locations= {"classpath:/sqlmap/mapper.xml"})
public class DbConnTest 	{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired 
	XmlMapper mapper; //스프링에서 인터페이스 클래스를 DI를 통해 bean을 생성하여 등록함
	@Autowired 
	private SqlSessionTemplate sqlSession;
	
	@Test
	public void testJdbcConn() {
		String apiId = "acps_v1_user_name";
		String API_WEB_PORT = jdbcTemplate.queryForObject("select count(*) cnt from dual", String.class);
        System.out.println("call getApiPort!!!:"+ API_WEB_PORT);
	}
	
	@Test
	public void testJdbcConnByParam() {
		String apiId = "acps_v1_user_name";
		String API_WEB_PORT = jdbcTemplate.queryForObject("select API_WEB_PORT from MS_API_INFO where API_ID = ?", String.class, apiId);
		System.out.println("call getApiPort2:"+ API_WEB_PORT);
	}
	
	@Test
	public void testMapper() {
		String apiId = "acps_v1_user_name";		
		String API_WEB_PORT = mapper.selectApiPortById(apiId);
		System.out.println("call getApiPort3:"+ API_WEB_PORT);
	}
	
	@Test
	public void testSqlSession() {
		String apiId = "acps_v1_user_name";
		String API_WEB_PORT = sqlSession.selectOne("com.example.demo.db.XmlMapper.selectApiPortById", apiId);
		System.out.println("call getApiPort4d:"+ API_WEB_PORT);
	}

	
	//commit and push test..
}
