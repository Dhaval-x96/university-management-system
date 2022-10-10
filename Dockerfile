#Using java 11
FROM openjdk:11-jdk

FROM maven:3.8.6 AS maven

#Create a workdir for our app
WORKDIR /Users/dhaval/Work/personal-projects/university-management-system
COPY . /Users/dhaval/Work/personal-projects/university-management-system

# Compile and package the application to an executable JAR
RUN mvn clean package -DskipTests

#ARG JAR_FILE=./target/*.jar

RUN #echo "Hello World >> $JAR_FILE"

## Copying JAR file
#COPY --from=maven ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","./target/university-management-system-1.0.0.jar"]