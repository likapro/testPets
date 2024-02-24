create table pet (
	id SERIAL PRIMARY KEY,
	name TEXT not null
);

INSERT into pet ("name") values ('Koko');
INSERT into pet ("name") values ('Archi');