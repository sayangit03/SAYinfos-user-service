FROM tomcat:latest

ADD target/user-service.war /usr/local/tomcat/webapps/

EXPOSE 5050

CMD ["catalina.sh", "run"]