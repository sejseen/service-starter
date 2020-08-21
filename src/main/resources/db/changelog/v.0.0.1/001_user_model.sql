CREATE TABLE users(
	id serial PRIMARY KEY NOT NULL,
	business_id uuid NOT NULL UNIQUE,
	name varchar(255) NOT NULL,
	email varchar(255) NOT NULL UNIQUE,
	password varchar(255),
	creation_date timestamp DEFAULT current_timestamp
);

CREATE TABLE roles (
	id serial PRIMARY KEY NOT NULL,
	name varchar(50) UNIQUE NOT NULL
) ;

CREATE TABLE user_role (
	user_id bigint NOT NULL REFERENCES users(id),
	role_id bigint NOT NULL REFERENCES roles(id)
) ;

CREATE TABLE privileges(
	id serial PRIMARY KEY NOT NULL,
	name varchar(50) UNIQUE NOT NULL
);

CREATE TABLE role_privilege (
	role_id bigint NOT NULL REFERENCES roles(id),
	privilege_id bigint NOT NULL REFERENCES privileges(id)
);
