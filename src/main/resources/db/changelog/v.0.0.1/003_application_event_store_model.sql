CREATE TABLE application_events_store(
	id serial PRIMARY KEY NOT NULL,
	emitted_time timestamp NOT NULL,
	msg varchar (1024) NOT NULL,
	domain varchar (64) NOT NULL,
	resource_id varchar(128) NOT NULL
);
