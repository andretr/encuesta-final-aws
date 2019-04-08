FROM mysql:latest
LABEL maintainer="andre.ticona.r@gmail.com"

ENV MYSQL_ROOT_PASSWORD=12345678
ENV MYSQL_DATABASE=encuesta
ENV MYSQL_ROOT_HOST=%

COPY ./scripts-mysql/ /docker-entrypoint-initdb.d/
CMD ["--default-authentication-plugin=mysql_native_password"]