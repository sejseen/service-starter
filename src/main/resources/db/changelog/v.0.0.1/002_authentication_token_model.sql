CREATE TABLE authentication_tokens(
	id serial PRIMARY KEY NOT NULL,
	creation_date timestamp DEFAULT current_timestamp,
	expiration_date timestamp NOT NULL,
	user_bid uuid NOT NULL REFERENCES users(business_id),
	jwt varchar (1024) NOT NULL
);
