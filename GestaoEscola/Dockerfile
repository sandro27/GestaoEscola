FROM openjdk:17-alpine
VOLUME /tmp
ARG jar_file=target/*.jar
COPY target/*.jar GestaoEscola.jar
CMD ["java","-jar","GestaoEscola-3.1.11.jar"]
ENTRYPOINT ["top","-b"]

