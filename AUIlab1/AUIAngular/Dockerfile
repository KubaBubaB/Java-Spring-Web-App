FROM nginx:1.23.3

LABEL org.opencontainers.image.title="AUI-lab-docker"
LABEL org.opencontainers.image.authors="Kuba Lisowski"
LABEL org.opencontainers.image.version="1.0.0-SNAPSHOT"
LABEL org.opencontainers.image.description="AUI-lab-docker angular container"
LABEL org.opencontainers.image.licenses="MIT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="1.0.0-SNAPSHOT"

ENV API_URL http://localhost:8080/work

EXPOSE 80

ADD dist/auiangular /usr/share/nginx/html/
ADD docker/etc/nginx/templates/ /etc/nginx/templates/

