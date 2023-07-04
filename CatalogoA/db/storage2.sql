DROP DATABASE IF EXISTS storage2;
CREATE DATABASE storage2;
USE storage2;

DROP TABLE IF EXISTS storage2;

DROP TABLE IF EXISTS `product`;
CREATE TABLE product (	
  code int primary key AUTO_INCREMENT,
  name char(60) not null,
  description char(200),
  price decimal(6,2) default 0,
  quantity int default 0,
  nome_immagine varchar(45) NOT NULL
);

DROP TABLE IF EXISTS `utente`;
CREATE TABLE utente (	
code int primary key AUTO_INCREMENT,
email char(60) not null,
password char(8) not null,
nome char(60) not null,
cognome char(60) not null,
indirizzo char(60) not null,
citta char(50) not null,
admin boolean not null
);


DROP TABLE IF EXISTS `consegna`;
CREATE TABLE `consegna` (
  `id_consegna` int NOT NULL,
  `via` varchar(45) NOT NULL,
  `cap` int NOT NULL,
  `numero` int NOT NULL,
  `citta` varchar(45) NOT NULL,
  `utente` int NOT NULL,
  PRIMARY KEY (`id_consegna`),
   FOREIGN KEY (`utente`) REFERENCES `utente` (`code`)
);

  

DROP TABLE IF EXISTS `metodo_pagamento`;
CREATE TABLE `metodo_pagamento` (
  `id_pagamento` int NOT NULL,
  `nominativo` varchar(80) NOT NULL,
  `CVV` int NOT NULL,
  `meseScadenza` int NOT NULL,
  `codice_carta` varchar(16) NOT NULL,
  `annoScadenza` int NOT NULL,
  `e_utente` int NOT NULL,
  PRIMARY KEY (`id_pagamento`),
  FOREIGN KEY (`e_utente`) REFERENCES `utente` (`code`)
);



DROP TABLE IF EXISTS `ordine`;
CREATE TABLE `ordine` (
  `id_ordine` int NOT NULL,
  `data_ordine` date NOT NULL,
  `stato_ordine` varchar(45) NOT NULL,
  `cod_consegna` int NOT NULL,
  `cod_pagamento` int NOT NULL,
  `cod_utente` int NOT NULL,
  `prezzo_totale` double(6,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id_ordine`),
  FOREIGN KEY (`cod_consegna`) REFERENCES `consegna` (`id_consegna`),
  FOREIGN KEY (`cod_pagamento`) REFERENCES `metodo_pagamento` (`id_pagamento`),
  FOREIGN KEY (`cod_utente`) REFERENCES `utente` (`code`)
);


INSERT INTO product values (1,"Ciotolola Doppia","Ciotola doppia per cani dal design unico, per contenere alimenti e/o acqua.",10.99,5,"1.jpg");
INSERT INTO product values (2,"Ciotolola in Ceramica","Perfetta per cani e gatti, questa ciotola è la soluzione migliore per i pasti di tutti i giorni del tuo pet.",15.99,5,"2.jpg");
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



INSERT INTO utente (email, password, nome, cognome, indirizzo, citta, admin) 
VALUES ("m.sorrentino2811@gmail.com", "ffff", "Mariapia", "Sorrentino","Via Pasquale Santoriello, 7", "Cava de tirreni, SA, 84013", 1);
INSERT INTO utente (email, password, nome, cognome, indirizzo, citta, admin) 
VALUES ("andreeacrintea3@gmail.com", "gggg", "Andreea", "Crintea","Via degli orti", "Santa Maria Capua Vetere, CA, 81055", 1);
INSERT INTO utente (email, password, nome, cognome, indirizzo, citta, admin) 
VALUES ("roksid09@gmail.com", "kkkk", "Roksana", "Duda","Via Pasquale Santoriello, 7", "Ottaviano, NA, 12345", 1);

INSERT INTO consegna (id_consegna, via, cap, numero, citta, utente)
VALUES (1, 'Via Roma 123', 00100, 5, 'Roma', 1);

INSERT INTO metodo_pagamento (id_pagamento, nominativo, CVV, meseScadenza, codice_carta, annoScadenza, e_utente)
VALUES (1, 'Mario Rossi', 123, 12, '1234567890123456', 2025, 1);

INSERT INTO ordine (id_ordine, data_ordine, stato_ordine, cod_consegna, cod_pagamento, cod_utente, prezzo_totale)
VALUES (1, '2023-07-04', 'In attesa', 1, 1, 1, 50.00);

