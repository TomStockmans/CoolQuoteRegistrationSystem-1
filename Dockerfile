FROM frolvlad/alpine-oraclejdk8:slim
# VOLUME /tmp
ADD cool-quote-registration-system-1.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENV ENV_OPTS=""
ENV PORT=8080
ENV SPRING_PROFILES=""
ENV MONGO_HOST=mongo
ENV MONGO_PORT=27017
ENV MONGO_DB=local
ENV MONGO_USER=""
ENV MONGO_PASSWORD=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS \
                        -Dspring.profiles.active=$SPRING_PROFILES \
                        -Djava.security.egd=file:/dev/./urandom \
                        -DPORT=$PORT \
                        -DMONGO_HOST=$MONGO_HOST \
                        -DMONGO_PORT=$MONGO_PORT \
                        -DMONGO_DB=$MONGO_DB \
                        -DMONGO_USER=$MONGO_USER \
                        -DMONGO_PASSWORD=$MONGO_PASSWORD \
                        $ENV_OPTS -jar /app.jar" ]