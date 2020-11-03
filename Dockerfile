FROM openjdk:11
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","github.io.somesh.SpringAppApplication"]
# -Dspring.cloud.bootstrap.enabled=false , -Dspring.profile.active=local
ENTRYPOINT java -cp app:app/lib/* github.io.somesh.SpringAppApplication