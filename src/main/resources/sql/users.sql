
CREATE DATABASE
   IF NOT EXISTS biayna_bi;
     DROP TABLE IF EXISTS `biayna_bi`.`user_role`;
     CREATE TABLE `biayna_bi`.`user_role`(
		`role_id` INT(11) NOT NULL,
		`role_name` VARCHAR(32) NOT NULL,
		UNIQUE INDEX `role_name` (`role_name`),
		PRIMARY KEY (`role_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

INSERT INTO biayna_bi.user_role (role_id, role_name) 
VALUES ('1','user'), ('2','producer'), ('3','admin');

CREATE DATABASE
   IF NOT EXISTS biayna_bi;
     DROP TABLE IF EXISTS `biayna_bi`.`user`;
     CREATE TABLE `biayna_bi`.`user` (
		`user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
		`firstname` VARCHAR(50) NOT NULL,
		`lastname` VARCHAR(50) NULL DEFAULT NULL,
		`role_id` INT(11) NOT NULL,
		PRIMARY KEY (`user_id`),
		INDEX `FK_user_role` (`role_id`),
		CONSTRAINT `FK_user_role` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE DATABASE
   IF NOT EXISTS biayna_bi;
     DROP TABLE IF EXISTS `biayna_bi`.`user_account`;
     CREATE TABLE `biayna_bi`.`user_account` (
	`email` VARCHAR(50) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	`enabled` TINYINT(1) NOT NULL,
	`login_attempts` SMALLINT(6) NOT NULL,
	`created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`last_login` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`last_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`user_id` BIGINT(20) NOT NULL,
	PRIMARY KEY (`email`),
	UNIQUE INDEX `user_id` (`user_id`),
	CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

