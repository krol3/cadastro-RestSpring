FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp

## Copy the project and execute
ADD cadastro/build/libs/cadastro-spring-rest-service-0.1.0.jar app.jar 
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]