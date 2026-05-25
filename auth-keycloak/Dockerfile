FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

COPY src src
RUN ./mvnw package -DskipTests -B

RUN java -Djarmode=tools -jar target/*.jar extract --layers --launcher --destination extracted

FROM eclipse-temurin:21-jre-alpine AS runtime

RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

WORKDIR /app

COPY --from=builder /app/extracted/dependencies/    ./
COPY --from=builder /app/extracted/spring-boot-loader/ ./
COPY --from=builder /app/extracted/snapshot-dependencies/ ./
COPY --from=builder /app/extracted/application/    ./

EXPOSE 8081

ENV JAVA_OPTS="-XX:+UseContainerSupport \
               -XX:MaxRAMPercentage=75.0 \
               -XX:+UseG1GC \
               -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS org.springframework.boot.loader.launch.JarLauncher"]