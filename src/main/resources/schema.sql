CREATE TABLE person (
  id       INT AUTO_INCREMENT,
  username VARCHAR(10) NOT NULL UNIQUE,
  password CHAR(60)    NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE client (
  id            INT AUTO_INCREMENT,
  client_id     VARCHAR(10) NOT NULL UNIQUE,
  client_secret CHAR(60)    NOT NULL,
  PRIMARY KEY (id)
);
