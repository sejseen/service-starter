FROM postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD sevensensedb
ENV POSTGRES_DB sevensense
COPY init_schema.sql /docker-entrypoint-initdb.d/
EXPOSE 5432