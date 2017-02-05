CREATE TABLE `visit_card`.`roles` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));


INSERT INTO `visit_card`.`roles` (`name`) VALUES ('ROLE_ADMIN');
INSERT INTO `visit_card`.`roles` (`name`) VALUES ('ROLE_USER');  

TRUNCATE visit_card.user_roles;
ALTER TABLE `visit_card`.`user_roles` 
CHANGE COLUMN `role` `role_id` BIGINT(20) NOT NULL,
ADD CONSTRAINT `fk_role_id`
  FOREIGN KEY (`role_id`)
  REFERENCES `visit_card`.`roles` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

INSERT INTO `visit_card`.`user_roles` (user_id, role_id)
VALUES (1, 2);
INSERT INTO `visit_card`.`user_roles` (user_id, role_id)
VALUES (1, 1);
INSERT INTO `visit_card`.`user_roles` (user_id, role_id)
VALUES (2, 2);
