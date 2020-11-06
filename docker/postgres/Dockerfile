FROM postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD passdb
ENV POSTGRES_DB portal
COPY init_schema.sql /docker-entrypoint-initdb.d/
EXPOSE 5432