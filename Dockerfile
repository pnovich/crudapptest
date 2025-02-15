FROM openjdk:21
EXPOSE 8080
ADD target/crudapptest.jar crudapptest.jar
ENTRYPOINT ["java", "-jar","/crudapptest.jar"]
#LABEL authors="VA"
#
#ENTRYPOINT ["top", "-b"]