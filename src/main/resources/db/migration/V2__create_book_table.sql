CREATE TABLE book
(
    book_id SERIAL PRIMARY KEY NOT NULL,
    title VARCHAR(100),
    writer_id INT4 ,
    release_date DATE,
    kind VARCHAR(100),
    book_language VARCHAR(100),

    FOREIGN KEY (writer_id) REFERENCES writer(writer_id)
)

