CREATE DATABASE 
  IF NOT EXISTS biayna_bi;
    DROP TABLE IF EXISTS `biayna_bi`.`users`;CREATE TABLE `biayna_bi`.`users` 
                 ( 
                              `email`  VARCHAR(50) NOT NULL, 
                              `password`  CHAR(128) NOT NULL, 
                              `firstname` VARCHAR(45) NOT NULL, 
                              `lastname`  VARCHAR(45) NULL, 
                              `phone`     VARCHAR(11) NULL, 
                              PRIMARY KEY (`email`) 
                 )
                 
CREATE DATABASE
   IF NOT EXISTS biayna_bi;
     DROP TABLE IF EXISTS `biayba_bi`.`authorities`;CREATE TABLE `biayba_bi`.`authorities`
     			(		
     							`email` VARCHAR(50) NOT NULL,
     							`authority` VARCHAR(30) NOT NULL,
     							FOREIGN KEY (`email`)
     		
     	)
                 