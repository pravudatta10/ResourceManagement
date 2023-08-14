FROM openjdk:17
EXPOSE 8081
ADD target/resource-management-docker.jar resource-management-docker.jar 
ENTRYPOINT ["java","-jar","/resource-management-docker.jar"]