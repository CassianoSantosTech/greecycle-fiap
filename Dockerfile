# Fase 1: Build
FROM maven:3.9.8-eclipse-temurin-21 AS build

RUN mkdir /opt/app

COPY . /opt/app

WORKDIR /opt/app

RUN mvn clean package

# Fase 2: Runtime
FROM eclipse-temurin:21-jre-alpine

RUN mkdir /opt/app

# Copia o JAR gerado na fase de build para o container final
COPY --from=build /opt/app/target/greencycle-0.0.1-SNAPSHOT.jar /opt/app/app.jar

WORKDIR /opt/app

# Define o perfil de execução como variável de ambiente (dev, prd, etc.)
ENV PROFILE=prd

# Expõe a porta 8080
EXPOSE 8080

# Comando de entrada para executar a aplicação Java
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar"]
