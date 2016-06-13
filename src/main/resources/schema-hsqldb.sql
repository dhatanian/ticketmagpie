DROP SCHEMA PUBLIC CASCADE;

CREATE TABLE concerts (
  id IDENTITY,
  band         VARCHAR(255),
  concert_date VARCHAR(255),
  description  VARCHAR(255),
  image_url    VARCHAR(255),
  image_blob   BLOB
);

CREATE TABLE users (
  username VARCHAR(255),
  email    VARCHAR(255),
  password VARCHAR(255),
  role     VARCHAR(255)
);

CREATE TABLE comments (
  username     VARCHAR(255),
  concert_id   VARCHAR(255),
  comment_text VARCHAR(4096)
);

CREATE TABLE tickets (
  id IDENTITY,
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
