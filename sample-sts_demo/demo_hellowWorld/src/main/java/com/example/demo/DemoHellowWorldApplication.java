package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //= @Configuration + @EnableAutoConfiguration + @ComponentScan
//*주의 @SpringBootApplication는 최상의 패키지에 선언해야, 하위 패키지의 class들을 ComponentScan 가능하다.
public class DemoHellowWorldApplication { //extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoHellowWorldApplication.class, args);
	}
	
	//war파일로 생성시
	//@Override
	//protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	//	return application.sources(DemoHellowWorldApplication.class);
	//}	
}

