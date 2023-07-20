DROP DATABASE IF EXISTS storage2;
CREATE DATABASE storage2;
USE storage2;


CREATE TABLE product (	
  categoria char(20) not null,
  specie char(20) not null,
  code int primary key AUTO_INCREMENT,
  name char(60) not null,
  description char(200) not null,
  price decimal(6,2) default 0,
  quantity int default 0,
  nome_immagine varchar(45) NOT NULL
);

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

DROP TABLE IF EXISTS `recensione`;
CREATE TABLE `recensione` (
  `idRecensione` int AUTO_INCREMENT,
  `prodotto` int NOT NULL,
  `utente` int NOT NULL,
  `voto` int NOT NULL,
  `testo` varchar(50) ,
  PRIMARY KEY (`idRecensione`),
  FOREIGN KEY (`prodotto`) REFERENCES `product` (`code`),
  FOREIGN KEY (`utente`) REFERENCES `utente` (`code`)
) ;


DROP TABLE IF EXISTS `consegna`;
CREATE TABLE `consegna` (
  `id_consegna` int PRIMARY KEY AUTO_INCREMENT,
  `via` varchar(45) NOT NULL,
  `cap` int NOT NULL,
  `numero` int NOT NULL,
  `citta` varchar(45) NOT NULL,
  `e_utente` int NOT NULL,
   FOREIGN KEY (`e_utente`) REFERENCES `utente` (`code`)
);

  

DROP TABLE IF EXISTS `metodo_pagamento`;
CREATE TABLE `metodo_pagamento` (
  `id_pagamento` int PRIMARY KEY AUTO_INCREMENT,
  `nominativo` varchar(80) NOT NULL,
  `CVV` int NOT NULL,
  `meseScadenza` varchar(10) NOT NULL,
  `codice_carta` varchar(16) NOT NULL,
  `annoScadenza` int NOT NULL,
  `e_utente` int NOT NULL,
  FOREIGN KEY (`e_utente`) REFERENCES `utente` (`code`)
);





DROP TABLE IF EXISTS `ordine`;
CREATE TABLE `ordine` (
  `id_ordine` int AUTO_INCREMENT,
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

DROP TABLE IF EXISTS `composizione`;
CREATE TABLE `composizione` (
  `id_composizione` int AUTO_INCREMENT,
  `cod_prodotto` int NOT NULL,
  `num_ordine` int NOT NULL,
  `quantita` double NOT NULL,
  `iva` double NOT NULL DEFAULT 22,
  `prezzo` double(6,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id_composizione`),
  FOREIGN KEY (`cod_prodotto`) REFERENCES `product` (`code`),
  FOREIGN KEY (`num_ordine`) REFERENCES `ordine` (`id_ordine`)
);


INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("accessori","Cani","Ciotolola Doppia","Ciotola doppia per cani dal design unico, per contenere alimenti e/o acqua.",10.99,5,"1.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("accessori","Gatti","Ciotolola in Ceramica","Perfetta per cani e gatti, questa ciotola è la soluzione migliore per i pasti di tutti i giorni del tuo pet.",15.99,5,"2.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("cibo","Cani","Virtus Dog Protein Selection","Alimento secco completo e bilanciato per cani adulti di qualsiasi razza e taglia ",25.99,13,"3.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("gabbia","Uccelli","Voliera in legno","Una raffinata e spaziosa voliera per canarini, cocorite e uccelli esotici.",270.30,5,"4.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("accessori","Cani","Collare Outdoor Blu","Adatto ad un cane di piccola taglia",8.99,20,"5.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("igiene","Cani","Shampoo per Cani dal Pelo Riccio","Ideato per cani col manto riccio, questo shampoo contribuisce a render eil pelo morbido e luminoso",15.99,50,"6.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("giocattoli","Cani","Set Ossa di gomma","Gioco di gomma ",11.99,20,"7.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("accessori","Gatti","Trasportino Lux Vision ","Adatta per lunghi viaggi ",74.99,22,"8.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("cibo","Cani","Croccantini per cani", "Alimento completo per cani di taglia media", 20.99, 100,"9.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("cibo","Gatti","Croccantini per gatti", "Alimento completo per gatti adulti", 15.99, 50,"10.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("cibo","Pesci","Cibo per pesci rossi", "Alimento in fiocchi per pesci rossi", 5.49, 200,"11.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine) 
values ("accessori","Cani","Letto per cani", "Letto imbottito per cani di piccola taglia", 35.99, 20,"12.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("accessori","Gatti","Letto per gatti", "Letto in tessuto morbido per gatti", 25.99, 30,"13.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("igiene","Gatti","Shampoo per Gatti alla Camomilla","Delicato shampoo alla camomilla per gatti dalla pelle sensibile",12.99,40,"14.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("accessori","Uccelli","Accessori per Uccelli ","Set di accessori per una gabbia",8.99,15,"15.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("cibo","Cani","Croccantini Grain-Free","Alimento secco per cani senza cereali, con proteine di alta qualità",29.99,50,"16.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("accessori","Cani","Collare e Guinzaglio in Pelle","Elegante collare e guinzaglio in pelle per cani di taglia media-grande",45.99,10,"17.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("cibo","Pesci","Cibo per Pesci Tropicali","Alimento in granuli per pesci tropicali, arricchito con vitamine e minerali",7.99,150,"18.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("accessori","Gatti","Scratcher per Gatti a Forma di Gatto","Scratcher in cartone a forma di gatto, perfetto per la cura delle unghie dei felini",18.99,25,"19.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("accessori","Cani","Pettorina per Cani","Comoda e resistente pettorina per cani di taglia media",22.99,30,"20.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("igiene","Gatti","Shampoo per gatti al profumo di fiori","Shampoo delicato per il manto dei gatti, arricchito con estratti di fiori per un profumo fresco e duraturo",12.99,30,"21.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("giocattoli","Gatti","Set di palline colorate","Set di 4 palline colorate per il divertimento dei gatti, adatte per giocare in casa o all'aperto",6.99,50,"22.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("cibo","Uccelli","Semi misti per uccelli esotici","Mix di semi ideale per la dieta degli uccelli esotici, confezionato con ingredienti di alta qualità",8.49,150,"23.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("accessori","Pesci","Termoriscaldatore per acquario","Termoriscaldatore adatto per acquari di piccola e media taglia, per garantire una temperatura costante dell'acqua",19.99,15,"24.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("igiene","Uccelli","Shampoo azzurro per pappagalli","Shampoo naturale all'essenza di mirtilli",7.99,40,"25.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("accessori","Pesci","Pietre decorative per acquario","Set di 5 pietre decorative per creare un ambiente naturale e piacevole nell'acquario",12.99,30,"26.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("cibo","Pesci","Cibo per pesci tropicale","Alimento in granuli per pesci tropicali, confezionato con ingredienti di alta qualità per una dieta sana ed equilibrata",6.99,100,"27.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("accessori","Cani","Rare felpa per cani","felpa colorata per canid di tutte le taglie",6.99,50,"28.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("giocattoli","Cani","Palla in gomma","Palla di gomma per far divertire il tuo cane ",8.49,150,"29.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("cibo","Cani","Snack per Cane ","Snack per Cane Anatra e Zucchine Happy Farm",19.99,15,"30.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("gabbia","Uccelli","Alba Gabbia per Canarini","Gabbia gialla per canarini",7.99,40,"31.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("accessori","Gatti","Tiragraffi per gatti ricambio – Azalea","Tiragraffi",12.99,30,"32.jpg");
INSERT INTO product (categoria, specie, name, description, price, quantity, nome_immagine)
values ("accessori","Cani","Ciotola per cani","Ciotola rosa per il tuo cucciolo",6.99,100,"33.jpg");




INSERT INTO utente (email, password, nome, cognome, indirizzo, citta, admin) 
VALUES ("m.sorrentino2811@gmail.com", "ffff", "Mariapia", "Sorrentino","Via Pasquale Santoriello, 7", "Cava de tirreni, SA, 84013", 1);
INSERT INTO utente (email, password, nome, cognome, indirizzo, citta, admin) 
VALUES ("andreeacrintea3@gmail.com", "gggg", "Andreea", "Crintea","Via degli orti", "Santa Maria Capua Vetere, CA, 81055", 1);
INSERT INTO utente (email, password, nome, cognome, indirizzo, citta, admin) 
VALUES ("roksid09@gmail.com", "kkkk", "Roksana", "Duda","Via Pasquale Santoriello, 7", "Ottaviano, NA, 12345", 1);



use storage2;
INSERT INTO consegna (via, cap, numero, citta, e_utente)
VALUES ('Via Roma 123', 00100, 5, 'Roma', 1);
INSERT INTO consegna (via, cap, numero, citta, e_utente)
VALUES ('Via Roma 123', 00100, 5, 'Roma', 3);

INSERT INTO metodo_pagamento (nominativo, CVV, meseScadenza, codice_carta, annoScadenza, e_utente)
VALUES ('Mario Rossi', 123, 12, '1234567890123456', 2025, 1);
INSERT INTO metodo_pagamento (nominativo, CVV, meseScadenza, codice_carta, annoScadenza, e_utente)
VALUES ('Mario Rossi', 123, 12, '1234567890122456', 2026, 3);

INSERT INTO ordine (data_ordine, stato_ordine, cod_consegna, cod_pagamento, cod_utente, prezzo_totale)
VALUES ('2023-07-04', 'In attesa', 1, 1, 1, 50.00);
INSERT INTO ordine (data_ordine, stato_ordine, cod_consegna, cod_pagamento, cod_utente, prezzo_totale)
VALUES ('2023-07-03', 'Spedito', 1, 1, 3, 50.00);