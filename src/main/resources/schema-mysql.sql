DROP TABLE IF EXISTS concerts CASCADE;
CREATE TABLE concerts (
  id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  band        VARCHAR(255),
  description VARCHAR(255),
  image_url   VARCHAR(255)
);
