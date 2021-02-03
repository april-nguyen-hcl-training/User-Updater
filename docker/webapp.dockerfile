FROM maven:3.6.3-openjdk-11 as maven_build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM tomcat:jdk11-openjdk
WORKDIR /usr/local/tomcat/webapps/
COPY --from=maven_build /app/target/ROOT.war .