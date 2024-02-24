FROM openjdk:17

ADD /target/TestForRCDigital-0.0.1-SNAPSHOT.jar backend.jar

ENTRYPOINT ["java", "-jar", "backend.jar"]

#COPY . /java
#
#WORKDIR /java/src
#
#EXPOSE 1488
#
#RUN javac /java/src/main/java/com/example/TestForRCDigital/TestForRcDigitalApplication.java
#
#CMD ["java", "Main"]