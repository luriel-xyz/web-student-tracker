DROP TABLE IF EXISTS student;

CREATE TABLE student (
  id SERIAL PRIMARY KEY,
  first_name varchar(45) default null,
  last_name varchar(45) default null,
  email varchar(45) default null
);

INSERT INTO student(first_name, last_name, email)
values ('Mary','Public','mary@luv2code.com'),
('John','Doe','john@luv2code.com'),
('Ajay','Rao','ajay@luv2code.com'),
('Bill','Neely','bill@luv2code.com'),
('Maxwell','Dixon','max@luv2code.com');