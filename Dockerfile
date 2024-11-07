# Estágio de build
FROM gradle:8.10.1-jdk21-alpine AS build

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Compilação do projeto
RUN gradle build --no-daemon

# Estágio final para rodar a aplicação
FROM openjdk:21-jdk-slim

# Expor a porta do aplicativo
EXPOSE 8080

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR construído no primeiro estágio para o segundo
COPY --from=build /home/gradle/src/build/libs/*.jar /app/sinisterbusterdefinitive-java-api.jar

# Adicionar um usuário sem privilégios para executar o aplicativo
RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup
USER appuser

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "/app/sinisterbusterdefinitive-java-api.jar"]
