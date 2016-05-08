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
mvn3 clean package 

#teste curl

##create a user: endpoint: cadastro POST
curl -i -X POST -H "Content-Type:application/json" -d '{ "name": "João da Silva", "email": "joao@silva.org", "password": "hunter2", "phones": [ { "number": 987654321, "ddd": "21" }, { "number": 77777, "ddd": "11" } ] }' http://localhost:8181/cadastro

##query all the users: endpoint: cadastro GET
curl http://localhost:8181/cadastro

##query for specific user: endpoint: /cadastro/{id} GET
curl http://localhost:8181/cadastro/1

##all the custom queries: /cadastro
curl http://localhost:8181/cadastro/search

##find a user by email
curl http://localhost:8181/cadastro/search/findByEmail?email=joao@silva.org

##replace user
curl -i -X PUT -H "Content-Type:application/json" -d '{ "name": "João da SilvaXX", "email": "joao@silva.orgXXX", "password": "hunter2XX", "phones": [ { "number": 98765432177, "ddd": "21" } ] }' http://localhost:8181/cadastro/1

##update user
curl -i -X PATCH -H "Content-Type:application/json" -d '{ "name": "João da SilvaXX", "email": "joao@silva.orgXXX", "password": "hunter2XX", "phones": [ { "number": 98765432177, "ddd": "21" } ] }' http://localhost:8181/cadastro/2

##delete a user
curl -X DELETE http://localhost:8181/cadastro/1