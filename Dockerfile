# our base build image
FROM maven:3.5-jdk-8

# copy the project files
COPY ./pom.xml ./pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src

# build for release
RUN mvn package

# set the startup command to run your binary
CMD ["java", "-jar", "target/dynamo-test-0.0.1-SNAPSHOT.jar"]