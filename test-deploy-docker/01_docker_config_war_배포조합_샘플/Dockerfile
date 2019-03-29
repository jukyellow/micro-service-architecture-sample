#1. 기반으로 사용할 이미지를 설정
FROM tomcat:8
MAINTAINER jUK "jukyellow@ktnet.com" 

#2. RUN을 이용해 쉘 스크립트를 실행, 톰캣설치
#

#3. 이후의 CMD 명령어가 실행될 경로설정
WORKDIR /usr/local/tomcat/bin

#4. 변경할 소스코드 및 설정을 컨테이너 내부 경로로 복사
RUN rm -rf /usr/local/tomcat/webapps/*
COPY testWebApp.war /usr/local/tomcat/webapps

#5. 컨테이너가 수행되었을때 수행할 명령을 지정
# 8081번 웹 서버 포트를 열어 포트를 리슨
EXPOSE 8081
CMD ["catalina.sh", "run"]
