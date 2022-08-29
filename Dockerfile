FROM eclipse-temurin:17-jdk-focal

ARG UID=2000
ARG GID=2000
ARG APP_USER=app

ENV APP_HOME /usr/local/sbapp

RUN groupadd -r ${APP_USER} && \
    useradd -r -g ${APP_USER} -d /usr/local/sbapp ${APP_USER} && \
    groupmod -o -g $GID ${APP_USER} && \
    usermod -o -u $UID ${APP_USER}

RUN mkdir -p /usr/local/sbapp/logs && \
    chown -R ${APP_USER}:${APP_USER} /usr/local/sbapp && \
    chmod -R 755 /usr/local/sbapp
    
COPY target/ppca-distribuicao-urnas-exec.jar /usr/local/sbapp/sbapp.jar