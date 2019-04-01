FROM mysql:latest
LABEL maintainer="andre.ticona.r@gmail.com"
#WORKDIR /workspace
#Podemos usar ADD o COPY
#ADD scripts/DDL.sql /docker-entrypoint-initdb.d/DDL.sql

ENV MYSQL_ROOT_PASSWORD=toor
ENV MYSQL_DATABASE=geoserviciosbd
ENV MYSQL_ROOT_HOST=%

COPY ./scripts-mysql/ /docker-entrypoint-initdb.d/
CMD ["--default-authentication-plugin=mysql_native_password"]