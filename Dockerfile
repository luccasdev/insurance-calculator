FROM openjdk:17-oracle
ADD build/libs/*.jar app.jar
ENV JAVA_OPTS="-Xmx512m"
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]
