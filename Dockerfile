FROM java:openjdk-8-jdk
MAINTAINER Carol Guival <guillen.carolina@gmail.com>
VOLUME /tmp

EXPOSE 8080

RUN git clone https://github.com/krol3/cadastro-RestSpring && cd cadastro-RestSpring && git checkout rest

WORKDIR /cadastro-RestSpring/cadastro/

#RUN sh -c './gradlew build && java -jar build/libs/cadastro-spring-rest-service-0.1.0.jar'

#ENTRYPOINT ["./gradlew", "build"] : ["java","-Djava.security.egd=file:/dev/./urandom","-jar","build/libs/cadastro-spring-rest-service-0.1.0.jar"]

ENTRYPOINT ["./service.sh"]