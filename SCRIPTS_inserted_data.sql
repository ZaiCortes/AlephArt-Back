-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema AlephArt
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema AlephArt
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `AlephArt` ;
USE `AlephArt` ;

-- -----------------------------------------------------
-- Table `AlephArt`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AlephArt`.`book` (
  `id_book` INT NOT NULL AUTO_INCREMENT,
  `book_photo` LONGBLOB NULL,
  `book_name` VARCHAR(100) NULL,
  `book_description` VARCHAR(1200) NULL,
  PRIMARY KEY (`id_book`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AlephArt`.`userprofile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AlephArt`.`userprofile` (
  `id_user_profile` INT NOT NULL AUTO_INCREMENT,
  `profile_photo` LONGBLOB NULL,
  `banner` LONGBLOB NULL,
  `about_me` VARCHAR(1200) NULL,
  `profession` VARCHAR(200) NULL,
  `book_id_book` INT NOT NULL,
  PRIMARY KEY (`id_user_profile`),
  INDEX `fk_userProfile_book1_idx` (`book_id_book` ASC) VISIBLE,
  CONSTRAINT `fk_userProfile_book1`
    FOREIGN KEY (`book_id_book`)
    REFERENCES `AlephArt`.`book` (`id_book`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AlephArt`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AlephArt`.`user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(70) NULL,
  `last_name` VARCHAR(70) NULL,
  `phone_number` BIGINT(13) NULL,
  `password` VARCHAR(30) NULL,
  `email` VARCHAR(70) NULL,
  `userprofile_id_user_profile` INT NOT NULL,
  PRIMARY KEY (`id_user`, `userprofile_id_user_profile`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_user_userProfile_idx` (`userprofile_id_user_profile` ASC) VISIBLE,
  CONSTRAINT `fk_user_userProfile`
    FOREIGN KEY (`userprofile_id_user_profile`)
    REFERENCES `AlephArt`.`userprofile` (`id_user_profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AlephArt`.`posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AlephArt`.`posts` (
  `id_posts` INT NOT NULL AUTO_INCREMENT,
  `posts_date` DATE NULL,
  `posts_description` VARCHAR(1200) NULL,
  `post_file` LONGBLOB NULL,
  `user_id_user` INT NOT NULL,
  `user_userprofile_id_user_profile` INT NOT NULL,
  PRIMARY KEY (`id_posts`),
  INDEX `fk_posts_user1_idx` (`user_id_user` ASC, `user_userprofile_id_user_profile` ASC) VISIBLE,
  CONSTRAINT `fk_posts_user1`
    FOREIGN KEY (`user_id_user` , `user_userprofile_id_user_profile`)
    REFERENCES `AlephArt`.`user` (`id_user` , `userprofile_id_user_profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AlephArt`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AlephArt`.`comments` (
  `id_comment` INT NOT NULL AUTO_INCREMENT,
  `comment_date` DATE NULL,
  `comment_description` VARCHAR(1200) NULL,
  `posts_id_posts` INT NOT NULL,
  PRIMARY KEY (`id_comment`),
  INDEX `fk_comments_posts1_idx` (`posts_id_posts` ASC) VISIBLE,
  CONSTRAINT `fk_comments_posts1`
    FOREIGN KEY (`posts_id_posts`)
    REFERENCES `AlephArt`.`posts` (`id_posts`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AlephArt`.`eventmode`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AlephArt`.`eventmode` (
  `id_event_mode` INT NOT NULL AUTO_INCREMENT,
  `mode_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id_event_mode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AlephArt`.`eventcategory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AlephArt`.`eventcategory` (
  `id_event_category` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(15) NULL,
  PRIMARY KEY (`id_event_category`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AlephArt`.`locationcity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AlephArt`.`locationcity` (
  `id_location_city` INT NOT NULL AUTO_INCREMENT,
  `city_name` VARCHAR(50) NULL,
  PRIMARY KEY (`id_location_city`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AlephArt`.`locationstate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AlephArt`.`locationstate` (
  `id_location_state` INT NOT NULL AUTO_INCREMENT,
  `state_name` VARCHAR(50) NULL,
  PRIMARY KEY (`id_location_state`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AlephArt`.`events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AlephArt`.`events` (
  `id_events` INT NOT NULL AUTO_INCREMENT,
  `event_name` VARCHAR(100) NULL,
  `event_description` VARCHAR(1500) NULL,
  `event_photo` LONGBLOB NULL,
  `event_date` DATE NULL,
  `event_time` TIME NULL,
  `user_id_user` INT NOT NULL,
  `user_userprofile_id_user_profile` INT NOT NULL,
  `eventmode_id_event_mode` INT NOT NULL,
  `eventcategory_id_event_category` INT NOT NULL,
  `locationcity_id_location_city` INT NOT NULL,
  `locationstate_id_location_state` INT NOT NULL,
  PRIMARY KEY (`id_events`, `eventmode_id_event_mode`, `eventcategory_id_event_category`, `locationcity_id_location_city`, `locationstate_id_location_state`),
  INDEX `fk_events_user1_idx` (`user_id_user` ASC, `user_userprofile_id_user_profile` ASC) VISIBLE,
  INDEX `fk_events_eventmode1_idx` (`eventmode_id_event_mode` ASC) VISIBLE,
  INDEX `fk_events_eventCategory1_idx` (`eventcategory_id_event_category` ASC) VISIBLE,
  INDEX `fk_events_locationCity1_idx` (`locationcity_id_location_city` ASC) VISIBLE,
  INDEX `fk_events_locationState1_idx` (`locationstate_id_location_state` ASC) VISIBLE,
  CONSTRAINT `fk_events_user1`
    FOREIGN KEY (`user_id_user` , `user_userprofile_id_user_profile`)
    REFERENCES `AlephArt`.`user` (`id_user` , `userprofile_id_user_profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_events_eventmode1`
    FOREIGN KEY (`eventmode_id_event_mode`)
    REFERENCES `AlephArt`.`eventmode` (`id_event_mode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_events_eventCategory1`
    FOREIGN KEY (`eventcategory_id_event_category`)
    REFERENCES `AlephArt`.`eventcategory` (`id_event_category`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_events_locationCity1`
    FOREIGN KEY (`locationcity_id_location_city`)
    REFERENCES `AlephArt`.`locationcity` (`id_location_city`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_events_locationState1`
    FOREIGN KEY (`locationstate_id_location_state`)
    REFERENCES `AlephArt`.`locationstate` (`id_location_state`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

select * from book;
select * from user;
select * from userprofile;

INSERT INTO `AlephArt`.`user` 
(`username`, `last_name`, `phone_number`, `password`, `email`, `userprofile_id_user_profile`) 
VALUES
('johndoe', 'Doe', 1234567890123, 'hashedpassword123', 'john.doe@example.com', 1),  -- Asume que `userprofile_id_user_profile = 1` existe en la tabla `userprofile`
('jane_smith', 'Smith', 1234567890124, 'hashed_password2', 'jane.smith@example.com', 2),
('michael_jones', 'Jones', 1234567890125, 'hashed_password3', 'michael.jones@example.com', 3),
('lisa_brown', 'Brown', 1234567890126, 'hashed_password4', 'lisa.brown@example.com', 4),
('robert_white', 'White', 1234567890127, 'hashed_password5', 'robert.white@example.com', 5);
select * from user;
select * from userprofile;

INSERT INTO `AlephArt`.`posts` 
(`posts_date`, `posts_description`, `post_file`, `user_id_user`, `user_userprofile_id_user_profile`) 
VALUES
(CURDATE(), 'Comparti mi más reciente obra de arte', NULL, 1, 1),  -- Asume que `user_id_user = 1` y `user_userprofile_id_user_profile = 1` existen en la tabla `user`
(CURDATE(), 'Mi nueva canción', NULL, 2, 2) ,
(CURDATE(), 'Una pintura extraordinaria', NULL, 3, 3), 
(CURDATE(), 'Película próximamente', NULL, 4, 4), 
(CURDATE(), 'Fotos del último casting', NULL, 5, 5);
select * from posts;
select * from user;

INSERT INTO `AlephArt`.`comments` 
(`comment_date`, `comment_description`, `posts_id_posts`) 
VALUES
(CURDATE(), 'Me encanta.', 1) , -- Asume que `posts_id_posts = 1` existe en la tabla `posts`
(CURDATE(), 'Super inspirador', 2) ,
(CURDATE(), 'Maravilloso trabajo.', 3), 
(CURDATE(), 'Qué buena interpretación del paisaje! Me encanta!', 4),
(CURDATE(), 'Wow, cuándo es tu siguiente exposición?', 5);
select * from comments;

INSERT INTO `AlephArt`.`eventmode` 
(`mode_name`) 
VALUES
('Presencial'),
('Virtual');
select * from eventmode;


INSERT INTO `AlephArt`.`eventcategory` 
(`category_name`) 
VALUES
('Arte Urbano'),
('Tecnologías'),
('Música'),
('Dibujo'),
('Talleres');
select * from eventcategory;

INSERT INTO `AlephArt`.`locationcity` 
(`city_name`) 
VALUES
('Acapulco de Juárez'),
('Aguascalientes'),
('Apatzingán de la Constitución'),
('Apodaca'),
('Buena Vista'),
('Cabo San Lucas'),
('Campeche'),
('Cancún'),
('Celaya'),
('Chalco de Díaz Covarrubias'),
('Chetumal'),
('Chicoloapan'),
('Chihuahua'),
('Chimalhuacán'),
('Chilpancingo de los Bravo'),
('Cholula de Rivadavia'),
('Ciudad Acuña'),
('Ciudad Cuauhtémoc'),
('CDMX'),
('Ciudad del Carmen'),
('Ciudad Guzmán'),
('Ciudad Juárez'),
('Ciudad López Mateos'),
('Ciudad Madero'),
('Ciudad Obregón'),
('Ciudad Valles'),
('Ciudad Victoria'),
('Coahuila'),
('San Luis Potosí'),
('Colima'),
('Comitán de Domínguez'),
('Córdoba'),
('Cuautitlán'),
('Cuautitlán Izcalli'),
('Cuautla'),
('Cuernavaca'),
('Culiacán Rosales'),
('Delicias'),
('Durango'),
('Ecatepec de Morelos'),
('Ensenada'),
('El Pueblito'),
('Fresnillo'),
('García'),
('General Escobedo'),
('Gómez Palacio'),
('Guadalajara'),
('Guadalupe'),
('Guanajuato'),
('Guaymas'),
('Hermosillo'),
('Hidalgo del Parral'),
('Iguala'),
('Irapuato'),
('Ixtapaluca'),
('Jiutepec'),
('Juárez'),
('Kanasín'),
('La Paz'),
('Lagos de Moreno'),
('León'),
('Los Mochis'),
('Manzanillo'),
('Matamoros'),
('Mazatlán'),
('Mérida'),
('Mexicali'),
('Minatitlán'),
('Miramar'),
('Monclova'),
('Monterrey'),
('Morelia'),
('Naucalpan de Juárez'),
('Navojoa'),
('Nezahualcóyotl'),
('Nogales'),
('Nuevo Laredo'),
('Oaxaca de Juárez'),
('Ojo de Agua'),
('Orizaba'),
('Pachuca de Soto'),
('Playa del Carmen'),
('Piedras Negras'),
('Poza Rica de Hidalgo'),
('Puebla de Zaragoza'),
('Puerto Vallarta'),
('Querétaro'),
('Ramos Arizpe'),
('Reynosa'),
('Rosarito'),
('Río Bravo'),
('Salamanca'),
('Saltillo'),
('San Cristóbal de las Casas'),
('San Francisco Coacalco'),
('San José del Cabo'),
('San Juan Bautista Tuxtepec'),
('San Juan del Río'),
('San Luis Potosí'),
('San Luis Río Colorado'),
('San Nicolás de los Garza'),
('San Miguel de Allende'),
('San Pablo de las Salinas'),
('San Pedro Garza García'),
('Santa Catarina'),
('Soledad de Graciano Sánchez'),
('Tampico'),
('Tapachula'),
('Tehuacán'),
('Temixco'),
('Tepexpan'),
('Tepic'),
('Tijuana'),
('Tlalnepantla de Baz'),
('Tlaquepaque'),
('Toluca de Lerdo'),
('Tonalá'),
('Torreón'),
('Tulancingo de Bravo'),
('Tulum'),
('Tuxtla Gutiérrez'),
('Uruapan del Progreso'),
('Veracruz'),
('Villa de Álvarez'),
('Villa Nicolás Romero'),
('Villahermosa'),
('Xalapa-Enríquez'),
('Xico'),
('Zacatecas'),
('Zamora de Hidalgo'),
('Zapopan');
SELECT * FROM locationcity;




INSERT INTO `AlephArt`.`locationstate` 
(`state_name`) 
VALUES
('Aguascalientes'),
('Baja California'),
('Baja California Sur'),
('Campeche'),
('Chiapas'),
('Chihuahua'),
('Coahuila'),
('Colima'),
('Durango'),
('Guanajuato'),
('Guerrero'),
('Hidalgo'),
('Jalisco'),
('Mexico'),
('Mexico City'),
('Michoacán'),
('Morelos'),
('Nayarit'),
('Nuevo León'),
('Oaxaca'),
('Puebla'),
('Querétaro'),
('Quintana Roo'),
('San Luis Potosí'),
('Sinaloa'),
('Sonora'),
('Tabasco'),
('Tamaulipas'),
('Tlaxcala'),
('Veracruz'),
('Yucatán'),
('Zacatecas');
SELECT * FROM locationstate;


SELECT * FROM events;
INSERT INTO `AlephArt`.`events` 
(`event_name`, `event_description`, `event_photo`, `event_date`, `event_time`, `user_id_user`, `user_userprofile_id_user_profile`, `eventmode_id_event_mode`, `eventcategory_id_event_category`, `locationcity_id_location_city`, `locationstate_id_location_state`) 
VALUES -- Asume que los valores de las claves foráneas existen
('AlephArt Expo', 'An event showcasing art from all over the world.', NULL, CURDATE(), CURTIME(), 1, 1, 1, 1, 1, 1),  -- evento creado por el usuario 1 
('Reunión de grafiti', 'An event showcasing art from all over the world.', NULL, CURDATE(), CURTIME(), 1, 1, 2, 1, 1, 1),  -- eventmode_id_event_mode=2=virtual
('Taller de acuarela', 'An event showcasing art from all over the world.', NULL, CURDATE(), CURTIME(), 1, 1, 1, 5, 1, 1),  -- eventcategory_id_event_category`=5= talleres
('Morelos, Anfitrión del mundo', 'An event showcasing art from all over the world.', NULL, CURDATE(), CURTIME(), 1, 1, 1, 5, 45, 17),  -- `locationcity_id_location_city`= 45= cuernavaca `locationstate_id_location_state`=17=morelos
('AlephArt Expo', 'Presentación Final 3 del proyecto Integrador', NULL, CURDATE(), CURTIME(), 4, 4, 2 , 2, 15 , 15);  -- evento creado por el usuario 4  en modalidad 2= virtual, categoría2=tecnologías, city=15=CDMX, state=15= CDMX