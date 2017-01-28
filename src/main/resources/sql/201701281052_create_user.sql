CREATE database visit_card;
USE visit_card;

CREATE  TABLE users (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (id));
  
  CREATE TABLE user_roles (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  user_id BIGINT(20) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uni_userid_role (role,user_id),
  KEY fk_user_id_idx (user_id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id));

INSERT INTO users(username,password,enabled)
VALUES ('admin','admin', true);
INSERT INTO users(username,password,enabled)
VALUES ('user','user', true);

INSERT INTO user_roles (user_id, role)
VALUES (1, 'ROLE_USER');
INSERT INTO user_roles (user_id, role)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO user_roles (user_id, role)
VALUES (2, 'ROLE_USER');