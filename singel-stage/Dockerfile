FROM nexus-docker.pnw.loc/maven:3.8.5-openjdk-17

WORKDIR /opt/app
COPY /demo /opt/app
ENTRYPOINT ["mvn", "-Dhttps.protocols=TLSv1.2", "spring-boot:run"]