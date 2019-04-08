CREATE DATABASE IF NOT EXISTS encuesta CHARACTER SET latin1 COLLATE latin1_swedish_ci;

USE encuesta;

SET foreign_key_checks = 0;

DROP TABLE IF EXISTS encuestas;

SET foreign_key_checks = 1;

CREATE TABLE encuestas(
   	id INT(5) NOT NULL AUTO_INCREMENT,
	  nombres TEXT NOT NULL,
	  apellidos TEXT NOT NULL,
	  edad INT(2) NOT NULL,
	  lenguaje TEXT NOT NULL,
	  fecha DATETIME NULL,
    id_usuario VARCHAR(50) NULL,
    PRIMARY KEY (`id`)
) ENGINE=INNODB;
