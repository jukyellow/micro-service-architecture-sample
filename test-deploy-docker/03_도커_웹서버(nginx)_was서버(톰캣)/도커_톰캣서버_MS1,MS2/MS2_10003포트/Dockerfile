#1. 기반으로 사용할 이미지를 설정
FROM tomcat:8
MAINTAINER jUK "jukyellow@ktnet.com" 

#2. RUN을 이용해 쉘 스크립트를 실행, 톰캣설치
#톰캣은 보통 도커에서 pull명령어로 image를 내려받아두고 사용하여 설치과정 불필요( docker pull tomcat:8 )

#3. 이후의 CMD 명령어가 실행될 경로설정
WORKDIR /usr/local/tomcat/bin

#4. 변경할 소스코드 및 설정을 컨테이너 내부 경로로 복사
#URI path를 지정하기 위해 server.xml을 설정하여 copy->ROOT.war 배포파일명 부여시 생략가능?
RUN rm -rf /usr/local/tomcat/webapps/*
RUN rm -rf /usr/local/tomcat/conf/server.xml
COPY TestMS.war /usr/local/tomcat/webapps
COPY server.xml /usr/local/tomcat/conf

#5. 컨테이너가 수행되었을때 수행할 명령을 지정
# 10001번 웹 서버 포트를 호스트PC로 열어 포트를 리슨
EXPOSE 10003
CMD ["catalina.sh", "run"]
