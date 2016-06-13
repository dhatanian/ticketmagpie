DROP TABLE IF EXISTS concerts CASCADE;
CREATE TABLE concerts (
  id           INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  band         VARCHAR(255),
  concert_date VARCHAR(255),
  description  VARCHAR(255),
  image_url    VARCHAR(255),
  image_blob   BLOB
);

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
  username VARCHAR(255) NOT NULL PRIMARY KEY,
  email    VARCHAR(255),
  password VARCHAR(255),
  role     VARCHAR(255)
);

DROP TABLE IF EXISTS tickets CASCADE;
CREATE TABLE tickets (
  id        INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  concertid INT NOT NULL,
  firstname VARCHAR(255),
  lastname  VARCHAR(255),
  address1  VARCHAR(255),
  address2  VARCHAR(255),
  address3  VARCHAR(255),
  postcode  VARCHAR(255),
  country   VARCHAR(255),
  user      VARCHAR(255)
);

DROP TABLE IF EXISTS comments CASCADE;
CREATE TABLE comments (
  username     VARCHAR(255),
  concert_id   VARCHAR(255),
  comment_text VARCHAR(4096)
);
