# Java runtime.  TODO explore versioning also jdk vs jre for deploy;  jdk11
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="drbugsmn@gmail.com"

#this is a short term hack to allow me to get a running container which fails without mongo
#ENV spring.data.mongodb.host "host.docker.internal"

#Volume provides a place for docker to mount from the host to the container
VOLUME /tmp

#Expos port 8080 to the external world
EXPOSE 8080

#Stuff for building the image
# The location of the spring boot jar file to add to the image.  this can go away once I gradlefy this
ARG JAR_FILE=build/libs/kotlinmicroservice-0.0.1-SNAPSHOT.jar

# Add the above sb jar to the image.
ADD ${JAR_FILE} kotlinmicroservice.jar

# can also access all the stuff that goes into the jar rather thanjust a jar file, ie:
# ARG DEPENDENCY=target/dependency
# COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
# COPY ${DEPENDENCY}/META-INF /app/META-INF
# COPY ${DEPENDENCY}/BOOT-INF/classes /app

# Run the jar file
ENTRYPOINT ["java","-jar","/kotlinmicroservice.jar"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/kotlinmicroservice.jar"]