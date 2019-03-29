#1. 기반으로 사용할 이미지를 설정
FROM nginx:latest
MAINTAINER jUK "jukyellow@ktnet.com" 

#2. RUN을 이용해 쉘 스크립트를 실행
#RUN apt-get update
#RUN apt-get install -y nginx
#RUN echo "\ndaemon off;" >> /etc/nginx/nginx.conf
#VOLUME ["/data", "/etc/nginx/site-enabled", "/var/log/nginx"]

#소유권한 수정(root->www-data)
RUN chown -R www-data:www-data /var/lib/nginx

#3. 이후의 CMD 명령어가 실행될 경로설정
WORKDIR /etc/nginx

#4. 변경할 소스코드 및 설정을 컨테이너 내부 경로로 복사
#URI path를 지정하기 위해 server.xml을 설정하여 copy->ROOT.war 배포파일명 부여시 생략가능?
RUN rm -rf /etc/nginx/conf.d/*
COPY default.conf /etc/nginx/conf.d

#5. 컨테이너가 수행되었을때 수행할 명령을 지정
# 10001번 웹 서버 포트를 호스트PC로 열어 포트를 리슨
EXPOSE 9001
#CMD ["nginx"]
CMD ["nginx", "-g", "daemon off;"]
