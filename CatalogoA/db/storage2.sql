DROP DATABASE IF EXISTS storage2;
CREATE DATABASE storage2;
USE storage2;

DROP TABLE IF EXISTS storage2;

CREATE TABLE product (	
  code int primary key AUTO_INCREMENT,
  name char(60) not null,
  description char(200),
  price decimal(6,2) default 0,
  quantity int default 0,
  nome_immagine varchar(45) NOT NULL
);

CREATE TABLE utente (	
code int primary key AUTO_INCREMENT,
name char(60) not null,
cognome char(60) not null,
password char(8) not null,
email char(60) not null,
valuta int default 0,
indirizzo char(60) not null,
admin boolean not null
);

INSERT INTO product values (1,"Ciotolola Doppia","Ciotola doppia per cani dal design unico, per contenere alimenti e/o acqua.",10.99,5,"1.jpg");
INSERT INTO product values (2,"Ciotolola in Ceramica","Perfetta per cani e gatti, questa ciotola � la soluzione migliore per i pasti di tutti i giorni del tuo pet.",15.99,5,"2.jpg");
INSERT INTO product values (3,"Virtus Dog Protein Selection","Alimento secco completo e bilanciato per cani adulti di qualsiasi razza e taglia ",25.99,13,"3.jpg");
INSERT INTO product values (4,"Voliera in legno","Una raffinata e spaziosa voliera per canarini, cocorite e uccelli esotici.",270.30,5,"4.jpg");
INSERT INTO product values (5,"Collare Outdoor Blu","Adatto ad un cane di piccola taglia",8.99,20,"5.jpg");
INSERT INTO product values (6,"Shampoo per Cani dal Pelo Riccio","Ideato per cani col manto riccio, questo shampoo contribuisce a render eil pelo morbido e luminoso",15.99,50,"6.jpg");
INSERT INTO product values (7,"Set Ossa di gomma","Gioco di gomma ",11.99,20,"7.jpg");
INSERT INTO product values (8,"Trasportino Lux Vision ","Adatta per lunghi viaggi ",74.99,22,"8.jpg");
INSERT INTO product values (9,"Croccantini per cani", "Alimento completo per cani di taglia media", 20.99, 100,"9.jpg");
INSERT INTO product values (10,"Croccantini per gatti", "Alimento completo per gatti adulti", 15.99, 50,"10.jpg");
INSERT INTO product values (11,"Cibo per pesci rossi", "Alimento in fiocchi per pesci rossi", 5.49, 200,"11.jpg");
INSERT INTO product values (12,"Letto per cani", "Letto imbottito per cani di piccola taglia", 35.99, 20,"12.jpg");
INSERT INTO product values (13,"Letto per gatti", "Letto in tessuto morbido per gatti", 25.99, 30,"13.jpg");
