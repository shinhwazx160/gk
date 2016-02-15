# Users schema

# --- !Ups

CREATE TABLE Directory2 (
    id int auto_increment  PRIMARY KEY,
    name varchar(50) NOT NULL DEFAULT "*Null",
    father_id int NOT NULL DEFAULT 0,
    FOREIGN KEY (father_id) Directory2.REFERENCES (id)
);

insert into Directory values();
# --- !Downs

DROP TABLE Directory;