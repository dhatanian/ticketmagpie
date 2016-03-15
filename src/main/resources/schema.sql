DROP SCHEMA PUBLIC CASCADE;

CREATE TABLE concerts (
  id IDENTITY,
  band        VARCHAR(255),
  description VARCHAR(255),
  image_url   VARCHAR(255)
);
