FROM openjdk:11
ADD target/*.jar app.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","/app.jar"]