FROM java:openjdk-8-jdk
MAINTAINER Carol Guival <guillen.carolina@gmail.com>
VOLUME /tmp

EXPOSE 8080

## Copy the project and execute
#ADD cadastro/build/libs/cadastro-spring-rest-service-0.1.0.jar app.jar
# Install requirements installation
#RUN apk add --update git && rm -rf /var/cache/apk/*

RUN git clone https://github.com/krol3/cadastro-RestSpring && cd cadastro-RestSpring && git checkout rest

WORKDIR /cadastro-RestSpring/cadastro/

#RUN sh -c './gradlew build && java -jar build/libs/cadastro-spring-rest-service-0.1.0.jar'
#RUN sh -c './gradlew build'
#RUN sh -c 'touch /app.jar'
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","build/libs/cadastro-spring-rest-service-0.1.0.jar"]

ENTRYPOINT ["./gradlew", "build"] : ["java","-Djava.security.egd=file:/dev/./urandom","-jar","build/libs/cadastro-spring-rest-service-0.1.0.jar"]