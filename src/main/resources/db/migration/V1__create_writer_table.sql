CREATE TABLE writer
(
    writer_id SERIAL PRIMARY KEY NOT NULL,
    firstname VARCHAR(100),
    surname VARCHAR(100),
    birthdate DATE,
    country VARCHAR(100)
)

