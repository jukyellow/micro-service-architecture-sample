package com.example.demo.db;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XmlMapper {
	public String selectApiPortById(String apiId);
}
