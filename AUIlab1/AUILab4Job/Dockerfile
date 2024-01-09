FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="aui-job"
LABEL org.opencontainers.image.authors="Kuba Lisowski"
LABEL org.opencontainers.image.version="1.0"
LABEL org.opencontainers.image.description="AUI, job module."
LABEL org.opencontainers.image.licenses="MIT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="1.0"

ENV SERVER_PORT=8080

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:simple-rpg-profession
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=password

ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

ENV SPRING_H2_CONSOLE_enabled=true

ENV AUI_CHARACTER_URL=aui-person:8080

EXPOSE 8080

COPY target/aui-job-1.0.jar /opt/aui-job/aui-job.jar

CMD ["java", "-jar", "/opt/aui-job/aui-job.jar"]
