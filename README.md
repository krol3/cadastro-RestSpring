# cadastro-RestSpring
Spring-boot 

#Gradle
--- withoud installed Gradle
BEFORE Generate wrapper with specific version: gradle wrapper --gradle-version 2.3
to RUN :
./gradlew build && java -jar build/libs/cadastro-spring-rest-service-0.1.0.jar  

--- installed Gradle
gradle build
java -jar build/libs/cadastro-spring-rest-service-0.1.0.jar --server.port=8181

#Maven
--- installed Maven3
mvn3 clean package && target/cadastro-spring-rest-service-0.1.0.jar --server.port=8181

# Docker
docker build -t krol/cadastro-rest-spring-app:latest .
docker run -p 8080:8080 krol/cadastro-rest-spring-app:latest

# Docker cloud
http://cadastro-rest-spring-app-1206-1.3bdd4ee8.cont.dockerapp.io:8080/

#teste endpoint : CADASTRO

## cadastrar um usuario: cadastro POST
curl -i -X POST -H "Content-Type:application/json" -d '{ "name": "João da Silva", "email": "joao@silva.org", "password": "hunter2", "phones": [ { "number": 987654321, "ddd": "21" }, { "number": 77777, "ddd": "11" } ] }' http://localhost:8080/cadastro

http://localhost:8181/login?email=joao@silva.org&senha=hunter2

##query for specific user: endpoint: /cadastro/{id} GET
curl http://localhost:8080/cadastro/1

#teste endpoint : LOGIN
curl http://localhost:8080/login?email=joao@silva.org&senha=hunter2
curl http://cadastro-rest-spring-app-1206-1.3bdd4ee8.cont.dockerapp.io:8080/login?email=joao@silva.org&senha=hunter2

#teste endpoint : PERFIL
curl --header "token: 361a4e78-724b-4dc0-82ef-b0504b6170cf" http://localhost:8080/perfil/1
curl --header "token: 361a4e78-724b-4dc0-82ef-b0504b6170cf" http://cadastro-rest-spring-app-1206-1.3bdd4ee8.cont.dockerapp.io:8080/perfil/1
curl --header "token: 5a786f63-1b5f-4494-ac2f-3eca84b7a592" http://cadastro-rest-spring-app-1206-1.3bdd4ee8.cont.dockerapp.io:8080/perfil/1

#teste endpoint : USER

## selecionar todos os usuarios cadastrados
curl http://localhost:8080/user/

##all the custom queries: /user
curl http://cadastro-rest-spring-app-1206-1.3bdd4ee8.cont.dockerapp.io:8080/user/search

##find a user by email
curl http://localhost:8181/cadastro/search/findByEmail?email=joao@silva.org
curl http://cadastro-rest-spring-app-1206-1.3bdd4ee8.cont.dockerapp.io:8080/user/search/findByEmail\?email\=joao@silva.org

##replace user
curl -i -X PUT -H "Content-Type:application/json" -d '{ "name": "João da SilvaXX", "email": "joao@silva.orgXXX", "password": "hunter2XX", "phones": [ { "number": 98765432177, "ddd": "21" } ] }' http://localhost:8181/cadastro/1

##update user
curl -i -X PATCH -H "Content-Type:application/json" -d '{ "name": "João da SilvaXX", "email": "joao@silva.orgXXX", "password": "hunter2XX", "phones": [ { "number": 98765432177, "ddd": "21" } ] }' http://localhost:8181/cadastro/2

##delete a user
curl -X DELETE http://localhost:8181/cadastro/1