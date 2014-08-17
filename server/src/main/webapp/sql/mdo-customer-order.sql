# phpMyAdmin MySQL-Dump
# version 2.4.0-rc1
# http://www.phpmyadmin.net/ (download page)
#
# Serveur: localhost
# GÈnÈrÈ le : Dimanche 08 Juin 2003 ‡ 11:05
# Version du serveur: 3.23.53
# Version de PHP: 4.3.0
# Base de donnÈes: `montagnesdor`
# --------------------------------------------------------

#
# Structure de la table `commandes`
#

CREATE TABLE commandes (
  idCommande int(10) unsigned NOT NULL auto_increment,
  idTable int(10) unsigned NOT NULL default '0',
  codeProduit varchar(5) NOT NULL default '',
  designation varchar(50) NOT NULL default '',
  quantite float NOT NULL default '0',
  prix float NOT NULL default '0',
  montant float NOT NULL default '0',
  PRIMARY KEY  (idCommande),
  KEY codeProduit (codeProduit),
  KEY designation (designation),
  KEY quantite (quantite),
  KEY prix (prix),
  KEY montant (montant)
) TYPE=MyISAM;

#
# Contenu de la table `commandes`
#

INSERT INTO commandes VALUES (21043, 3233, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21044, 3233, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (20991, 3197, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (20988, 3195, '11', 'NEM', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (20989, 3195, '12', 'SALADE  AUX  CREVETTES', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (20990, 3196, '15', 'OMELETTE  AUX  CREVETTES', '1', '4', '4');
INSERT INTO commandes VALUES (20987, 3194, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (20986, 3193, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (20983, 3192, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (20982, 3192, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (20984, 3192, '89', 'GIGOT  THA√è SUR  PLAQUE  CHAUFFANTE ¬´¬†Nouveaut√', '1', '10.5', '10.5');
INSERT INTO commandes VALUES (20985, 3192, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (20992, 3198, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (20993, 3199, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (20994, 3199, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (20995, 3200, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (20996, 3201, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (20997, 3202, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (20998, 3203, '1', 'SOUPE AUX TROIS FRAICHEURS', '1', '7.5', '7.5');
INSERT INTO commandes VALUES (20999, 3204, '11', 'NEM', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21000, 3205, '12', 'SALADE  AUX  CREVETTES', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21001, 3205, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (21002, 3206, '55', 'PORC  AUX  CHAMPIGNONS  NOIRS', '1', '6', '6');
INSERT INTO commandes VALUES (21003, 3206, '72', 'RIZ  CANTONAIS', '5', '3.5', '17.5');
INSERT INTO commandes VALUES (21004, 3206, '45', 'CANARD  AUX  CHAMPIGNONS  NOIRS', '3', '7', '21');
INSERT INTO commandes VALUES (21005, 3207, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (21006, 3208, '5', 'POTAGE  PIQUANT  PEKINOIS', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21007, 3208, '9', 'SOUPE  PHNOM-PENH  SPECIALE', '1', '5.5', '5.5');
INSERT INTO commandes VALUES (21008, 3208, '11', 'NEM', '3', '4.5', '13.5');
INSERT INTO commandes VALUES (21009, 3209, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (21010, 3209, '5', 'POTAGE  PIQUANT  PEKINOIS', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21011, 3210, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (21012, 3210, '23', 'CREVETTES  SAUTEES  NATURE', '2', '9', '18');
INSERT INTO commandes VALUES (21013, 3210, '33', 'COQUILLES  SAINT-JACQUES  SAUTEES  NATURE', '1', '10', '10');
INSERT INTO commandes VALUES (21014, 3211, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (21015, 3211, '33', 'COQUILLES  SAINT-JACQUES  SAUTEES  NATURE', '1', '10', '10');
INSERT INTO commandes VALUES (21016, 3211, '43', 'CANARD  AUX  POUSSES  DE  BAMBOU', '1', '7', '7');
INSERT INTO commandes VALUES (21019, 3214, '/', 'PLAT EMPORTER', '1', '119.5', '119.5');
INSERT INTO commandes VALUES (21018, 3213, '/', 'PLAT EMPORTER', '1', '107.5', '107.5');
INSERT INTO commandes VALUES (21021, 3216, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21024, 3219, '/', 'PLAT A EMPORTER', '1', '105', '105');
INSERT INTO commandes VALUES (21023, 3218, '/', 'PLAT A EMPORTER', '1', '119', '119');
INSERT INTO commandes VALUES (21022, 3217, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '5', '7', '35');
INSERT INTO commandes VALUES (21027, 3221, 'CF', 'CAFE DECAFEINE', '2', '2', '4');
INSERT INTO commandes VALUES (21026, 3221, '9', 'SOUPE  PHNOM-PENH  SPECIALE', '1', '5.5', '5.5');
INSERT INTO commandes VALUES (21028, 3222, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (21029, 3223, '6', 'SOUPE  DE  NOUILLES  AUX  RAVIOLIS  CHINOIS', '1', '7', '7');
INSERT INTO commandes VALUES (21030, 3224, '6', 'SOUPE  DE  NOUILLES  AUX  RAVIOLIS  CHINOIS', '1', '7', '7');
INSERT INTO commandes VALUES (21031, 3225, '6', 'SOUPE  DE  NOUILLES  AUX  RAVIOLIS  CHINOIS', '1', '7', '7');
INSERT INTO commandes VALUES (21032, 3226, '6', 'SOUPE  DE  NOUILLES  AUX  RAVIOLIS  CHINOIS', '2', '7', '14');
INSERT INTO commandes VALUES (21033, 3227, '5', 'POTAGE  PIQUANT  PEKINOIS', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21034, 3228, '6', 'SOUPE  DE  NOUILLES  AUX  RAVIOLIS  CHINOIS', '1', '7', '7');
INSERT INTO commandes VALUES (21035, 3229, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21036, 3230, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (21037, 3231, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (21038, 3222, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '2', '4', '8');
INSERT INTO commandes VALUES (21039, 3221, '1', 'SOUPE AUX TROIS FRAICHEURS', '3', '7.5', '22.5');
INSERT INTO commandes VALUES (21040, 3222, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (21041, 3223, '6', 'SOUPE  DE  NOUILLES  AUX  RAVIOLIS  CHINOIS', '1', '7', '7');
INSERT INTO commandes VALUES (21042, 3232, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21045, 3233, '4', 'POTAGE  ASPERGES  AU  CRABE', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21083, 3252, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (21059, 3244, '19', 'BEIGNETS  DE  RAVIOLIS', '1', '5.5', '5.5');
INSERT INTO commandes VALUES (21079, 3251, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (21057, 3243, '11', 'NEM', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21058, 3243, '12', 'SALADE  AUX  CREVETTES', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21077, 3250, '/', 'EMPORTER', '1', '107.5', '107.5');
INSERT INTO commandes VALUES (21078, 3251, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21060, 3244, '18', 'BEIGNETS DE CREVETTES', '1', '7.5', '7.5');
INSERT INTO commandes VALUES (21061, 3245, '11', 'NEM', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21062, 3245, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21082, 3252, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21075, 3249, '11', 'NEM', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21074, 3248, '5', 'POTAGE  PIQUANT  PEKINOIS', '4', '4.5', '18');
INSERT INTO commandes VALUES (21066, 3246, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21067, 3246, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '2', '4', '8');
INSERT INTO commandes VALUES (21068, 3246, '4', 'POTAGE  ASPERGES  AU  CRABE', '3', '4.5', '13.5');
INSERT INTO commandes VALUES (21069, 3246, '5', 'POTAGE  PIQUANT  PEKINOIS', '4', '4.5', '18');
INSERT INTO commandes VALUES (21070, 3246, '6', 'SOUPE  DE  NOUILLES  AUX  RAVIOLIS  CHINOIS', '5', '7', '35');
INSERT INTO commandes VALUES (21071, 3246, '7', 'SOUPE  AUX  NOUILLES  ET  AU  POULET', '6', '3', '18');
INSERT INTO commandes VALUES (21076, 3248, '11', 'NEM', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21073, 3233, '4', 'POTAGE  ASPERGES  AU  CRABE', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21084, 3252, '4', 'POTAGE  ASPERGES  AU  CRABE', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21085, 3252, '5', 'POTAGE  PIQUANT  PEKINOIS', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21086, 3252, '6', 'SOUPE  DE  NOUILLES  AUX  RAVIOLIS  CHINOIS', '1', '7', '7');
INSERT INTO commandes VALUES (21087, 3253, '12', 'SALADE  AUX  CREVETTES', '1', '4.5', '4.5');
INSERT INTO commandes VALUES (21088, 3253, '13', 'SALADE  AU  CRABE', '2', '5.5', '11');
INSERT INTO commandes VALUES (21089, 3254, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (21090, 3255, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21091, 3256, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21094, 3259, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21092, 3257, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21093, 3258, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21095, 3259, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21096, 3260, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21097, 3260, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (21098, 3261, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21100, 3263, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21106, 3268, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21102, 3261, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21103, 3261, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21104, 3267, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21105, 3267, '3', 'POTAGE  AUX  MAIS ET JAMBONS', '1', '4', '4');
INSERT INTO commandes VALUES (21107, 3261, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21108, 3269, '23', 'CREVETTES  SAUTEES  NATURE', '1', '9', '9');
INSERT INTO commandes VALUES (21109, 3261, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21110, 3270, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
INSERT INTO commandes VALUES (21111, 3271, '2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '1', '7', '7');
# --------------------------------------------------------

#
# Structure de la table `encaissements`
#

CREATE TABLE encaissements (
  idEncaissement int(10) unsigned NOT NULL auto_increment,
  idTable int(10) unsigned NOT NULL default '0',
  espece float NOT NULL default '0',
  ticket float NOT NULL default '0',
  cheque float NOT NULL default '0',
  carte float NOT NULL default '0',
  impaye float NOT NULL default '0',
  PRIMARY KEY  (idEncaissement),
  KEY espece (espece),
  KEY ticket (ticket),
  KEY cheque (cheque),
  KEY carte (carte),
  KEY impaye (impaye)
) TYPE=MyISAM;

#
# Contenu de la table `encaissements`
#

INSERT INTO encaissements VALUES (43, 3244, '0', '13', '0', '0', '0');
INSERT INTO encaissements VALUES (42, 3243, '9', '0', '0', '0', '0');
INSERT INTO encaissements VALUES (4, 3192, '0', '0', '0', '0', '28.5');
INSERT INTO encaissements VALUES (5, 3193, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (6, 3194, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (7, 3195, '0', '0', '0', '0', '9');
INSERT INTO encaissements VALUES (8, 3196, '0', '0', '0', '0', '4');
INSERT INTO encaissements VALUES (9, 3198, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (10, 3199, '0', '0', '0', '0', '11');
INSERT INTO encaissements VALUES (11, 3197, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (12, 3200, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (13, 3201, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (14, 3202, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (15, 3203, '0', '0', '0', '0', '7.5');
INSERT INTO encaissements VALUES (16, 3204, '0', '0', '0', '0', '4.5');
INSERT INTO encaissements VALUES (17, 3205, '0.5', '8', '0', '0', '0');
INSERT INTO encaissements VALUES (18, 3207, '4', '0', '0', '0', '0');
INSERT INTO encaissements VALUES (19, 3206, '44', '0.5', '0', '0', '0');
INSERT INTO encaissements VALUES (20, 3208, '0', '23.5', '0', '0', '0');
INSERT INTO encaissements VALUES (21, 3209, '0', '0', '0', '8', '0.5');
INSERT INTO encaissements VALUES (22, 3210, '0', '3', '0', '29', '0');
INSERT INTO encaissements VALUES (23, 3211, '0', '0', '0', '0', '21');
INSERT INTO encaissements VALUES (25, 3214, '0', '0', '0', '119.5', '0');
INSERT INTO encaissements VALUES (24, 3213, '0', '0', '107.5', '0', '0');
INSERT INTO encaissements VALUES (26, 3216, '-22', '0', '28', '0', '0');
INSERT INTO encaissements VALUES (27, 3217, '-6.5', '0', '40', '0', '0');
INSERT INTO encaissements VALUES (28, 3218, '0', '0', '0', '119', '0');
INSERT INTO encaissements VALUES (29, 3219, '-2', '0', '107', '0', '0');
INSERT INTO encaissements VALUES (30, 3222, '0', '0', '0', '0', '16');
INSERT INTO encaissements VALUES (31, 3221, '0', '0', '0', '0', '31.5');
INSERT INTO encaissements VALUES (32, 3230, '0', '0', '0', '0', '4');
INSERT INTO encaissements VALUES (33, 3231, '0', '0', '0', '0', '4');
INSERT INTO encaissements VALUES (34, 3232, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (35, 3223, '0', '0', '0', '0', '14');
INSERT INTO encaissements VALUES (36, 3224, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (37, 3225, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (38, 3226, '0', '0', '0', '0', '14');
INSERT INTO encaissements VALUES (39, 3227, '0', '0', '0', '0', '4.5');
INSERT INTO encaissements VALUES (40, 3228, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (41, 3229, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (44, 3245, '11.5', '0', '0', '0', '0');
INSERT INTO encaissements VALUES (45, 3246, '0', '99.5', '0', '0', '0');
INSERT INTO encaissements VALUES (46, 3248, '0', '22.5', '0', '0', '0');
INSERT INTO encaissements VALUES (48, 3251, '0', '0', '0', '0', '11');
INSERT INTO encaissements VALUES (47, 3250, '0', '0', '107.5', '0', '0');
INSERT INTO encaissements VALUES (49, 3233, '0', '0', '0', '0', '20');
INSERT INTO encaissements VALUES (50, 3252, '0', '0', '0', '0', '27');
INSERT INTO encaissements VALUES (51, 3254, '0', '0', '0', '0', '4');
INSERT INTO encaissements VALUES (52, 3249, '0', '0', '0', '0', '4.5');
INSERT INTO encaissements VALUES (53, 3253, '0', '0', '0', '0', '15.5');
INSERT INTO encaissements VALUES (54, 3255, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (55, 3256, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (56, 3257, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (57, 3258, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (58, 3259, '0', '0', '0', '0', '14');
INSERT INTO encaissements VALUES (59, 3260, '0', '0', '0', '0', '11');
INSERT INTO encaissements VALUES (60, 3263, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (61, 3267, '0', '0', '0', '0', '11');
INSERT INTO encaissements VALUES (62, 3268, '0', '0', '0', '0', '7');
INSERT INTO encaissements VALUES (63, 3269, '0', '0', '0', '0', '9');
# --------------------------------------------------------

#
# Structure de la table `produitpartype`
#

CREATE TABLE produitpartype (
  codeProduit varchar(5) NOT NULL default '0',
  idTypeProduit int(10) NOT NULL default '0',
  quantite float NOT NULL default '0',
  PRIMARY KEY  (codeProduit,idTypeProduit)
) TYPE=MyISAM;

#
# Contenu de la table `produitpartype`
#

INSERT INTO produitpartype VALUES ('1', 3, '0');
INSERT INTO produitpartype VALUES ('1/4', 9, '0');
INSERT INTO produitpartype VALUES ('10', 5, '0');
INSERT INTO produitpartype VALUES ('10', 13, '0');
INSERT INTO produitpartype VALUES ('100', 3, '0');
INSERT INTO produitpartype VALUES ('100', 5, '0');
INSERT INTO produitpartype VALUES ('101', 3, '0');
INSERT INTO produitpartype VALUES ('101', 13, '0');
INSERT INTO produitpartype VALUES ('102', 3, '0');
INSERT INTO produitpartype VALUES ('103', 3, '0');
INSERT INTO produitpartype VALUES ('104', 3, '0');
INSERT INTO produitpartype VALUES ('104', 2, '0');
INSERT INTO produitpartype VALUES ('105', 3, '0');
INSERT INTO produitpartype VALUES ('106', 5, '0');
INSERT INTO produitpartype VALUES ('107', 3, '0');
INSERT INTO produitpartype VALUES ('107', 5, '0');
INSERT INTO produitpartype VALUES ('108', 3, '0');
INSERT INTO produitpartype VALUES ('109', 3, '0');
INSERT INTO produitpartype VALUES ('11', 5, '0');
INSERT INTO produitpartype VALUES ('11', 13, '0');
INSERT INTO produitpartype VALUES ('110', 7, '0');
INSERT INTO produitpartype VALUES ('111', 3, '0');
INSERT INTO produitpartype VALUES ('112', 3, '0');
INSERT INTO produitpartype VALUES ('113', 3, '0');
INSERT INTO produitpartype VALUES ('114', 3, '0');
INSERT INTO produitpartype VALUES ('115', 3, '0');
INSERT INTO produitpartype VALUES ('116', 3, '0');
INSERT INTO produitpartype VALUES ('116', 13, '0');
INSERT INTO produitpartype VALUES ('117', 3, '0');
INSERT INTO produitpartype VALUES ('117', 13, '0');
INSERT INTO produitpartype VALUES ('118', 7, '0');
INSERT INTO produitpartype VALUES ('119', 3, '0');
INSERT INTO produitpartype VALUES ('12', 3, '0');
INSERT INTO produitpartype VALUES ('12', 13, '0');
INSERT INTO produitpartype VALUES ('120', 5, '0');
INSERT INTO produitpartype VALUES ('121', 7, '0');
INSERT INTO produitpartype VALUES ('122', 3, '0');
INSERT INTO produitpartype VALUES ('123', 5, '0');
INSERT INTO produitpartype VALUES ('124', 3, '0');
INSERT INTO produitpartype VALUES ('125', 5, '0');
INSERT INTO produitpartype VALUES ('125', 13, '0');
INSERT INTO produitpartype VALUES ('126', 5, '0');
INSERT INTO produitpartype VALUES ('127', 5, '0');
INSERT INTO produitpartype VALUES ('128', 3, '0');
INSERT INTO produitpartype VALUES ('129', 5, '0');
INSERT INTO produitpartype VALUES ('13', 3, '0');
INSERT INTO produitpartype VALUES ('13', 13, '0');
INSERT INTO produitpartype VALUES ('130', 5, '0');
INSERT INTO produitpartype VALUES ('131', 3, '0');
INSERT INTO produitpartype VALUES ('132', 7, '0');
INSERT INTO produitpartype VALUES ('14', 7, '0');
INSERT INTO produitpartype VALUES ('14', 13, '0');
INSERT INTO produitpartype VALUES ('15', 3, '0');
INSERT INTO produitpartype VALUES ('16', 3, '0');
INSERT INTO produitpartype VALUES ('18', 3, '0');
INSERT INTO produitpartype VALUES ('19', 3, '0');
INSERT INTO produitpartype VALUES ('2', 3, '0');
INSERT INTO produitpartype VALUES ('20', 3, '0');
INSERT INTO produitpartype VALUES ('21', 3, '0');
INSERT INTO produitpartype VALUES ('22', 3, '0');
INSERT INTO produitpartype VALUES ('22', 6, '0');
INSERT INTO produitpartype VALUES ('23', 3, '0');
INSERT INTO produitpartype VALUES ('24', 3, '0');
INSERT INTO produitpartype VALUES ('25', 3, '0');
INSERT INTO produitpartype VALUES ('25', 13, '0');
INSERT INTO produitpartype VALUES ('26', 3, '0');
INSERT INTO produitpartype VALUES ('27', 3, '0');
INSERT INTO produitpartype VALUES ('28', 3, '0');
INSERT INTO produitpartype VALUES ('29', 3, '0');
INSERT INTO produitpartype VALUES ('3', 7, '0');
INSERT INTO produitpartype VALUES ('30', 3, '0');
INSERT INTO produitpartype VALUES ('31', 3, '0');
INSERT INTO produitpartype VALUES ('32', 3, '0');
INSERT INTO produitpartype VALUES ('33', 3, '0');
INSERT INTO produitpartype VALUES ('34', 7, '0');
INSERT INTO produitpartype VALUES ('35', 7, '0');
INSERT INTO produitpartype VALUES ('35', 13, '0');
INSERT INTO produitpartype VALUES ('36', 7, '0');
INSERT INTO produitpartype VALUES ('37', 7, '0');
INSERT INTO produitpartype VALUES ('38', 7, '0');
INSERT INTO produitpartype VALUES ('39', 7, '0');
INSERT INTO produitpartype VALUES ('4', 3, '0');
INSERT INTO produitpartype VALUES ('40', 7, '0');
INSERT INTO produitpartype VALUES ('41', 7, '0');
INSERT INTO produitpartype VALUES ('41', 13, '0');
INSERT INTO produitpartype VALUES ('42', 7, '0');
INSERT INTO produitpartype VALUES ('43', 7, '0');
INSERT INTO produitpartype VALUES ('44', 7, '0');
INSERT INTO produitpartype VALUES ('45', 7, '0');
INSERT INTO produitpartype VALUES ('45', 13, '0');
INSERT INTO produitpartype VALUES ('46', 7, '0');
INSERT INTO produitpartype VALUES ('47', 7, '0');
INSERT INTO produitpartype VALUES ('48', 5, '0');
INSERT INTO produitpartype VALUES ('5', 7, '0');
INSERT INTO produitpartype VALUES ('50', 5, '0');
INSERT INTO produitpartype VALUES ('51', 5, '0');
INSERT INTO produitpartype VALUES ('52', 5, '0');
INSERT INTO produitpartype VALUES ('53', 5, '0');
INSERT INTO produitpartype VALUES ('54', 5, '0');
INSERT INTO produitpartype VALUES ('55', 5, '0');
INSERT INTO produitpartype VALUES ('55', 13, '0');
INSERT INTO produitpartype VALUES ('56', 5, '0');
INSERT INTO produitpartype VALUES ('56', 13, '0');
INSERT INTO produitpartype VALUES ('57', 5, '0');
INSERT INTO produitpartype VALUES ('57', 13, '0');
INSERT INTO produitpartype VALUES ('58', 5, '0');
INSERT INTO produitpartype VALUES ('58', 13, '0');
INSERT INTO produitpartype VALUES ('59', 5, '0');
INSERT INTO produitpartype VALUES ('59', 13, '0');
INSERT INTO produitpartype VALUES ('6', 3, '0');
INSERT INTO produitpartype VALUES ('60', 5, '0');
INSERT INTO produitpartype VALUES ('61', 5, '0');
INSERT INTO produitpartype VALUES ('61', 13, '0');
INSERT INTO produitpartype VALUES ('62', 5, '0');
INSERT INTO produitpartype VALUES ('63', 5, '0');
INSERT INTO produitpartype VALUES ('64', 5, '0');
INSERT INTO produitpartype VALUES ('65', 5, '0');
INSERT INTO produitpartype VALUES ('65', 13, '0');
INSERT INTO produitpartype VALUES ('66', 13, '0');
INSERT INTO produitpartype VALUES ('67', 13, '0');
INSERT INTO produitpartype VALUES ('68', 13, '0');
INSERT INTO produitpartype VALUES ('69', 7, '0');
INSERT INTO produitpartype VALUES ('69', 13, '0');
INSERT INTO produitpartype VALUES ('7', 7, '0');
INSERT INTO produitpartype VALUES ('7', 13, '0');
INSERT INTO produitpartype VALUES ('70', 5, '0');
INSERT INTO produitpartype VALUES ('70', 13, '0');
INSERT INTO produitpartype VALUES ('71', 5, '0');
INSERT INTO produitpartype VALUES ('71', 13, '0');
INSERT INTO produitpartype VALUES ('72', 6, '0');
INSERT INTO produitpartype VALUES ('73', 6, '0');
INSERT INTO produitpartype VALUES ('73B', 6, '0');
INSERT INTO produitpartype VALUES ('74', 3, '0');
INSERT INTO produitpartype VALUES ('74', 6, '0');
INSERT INTO produitpartype VALUES ('75', 13, '0');
INSERT INTO produitpartype VALUES ('76', 6, '0');
INSERT INTO produitpartype VALUES ('76', 7, '0');
INSERT INTO produitpartype VALUES ('77', 13, '0');
INSERT INTO produitpartype VALUES ('78', 3, '0');
INSERT INTO produitpartype VALUES ('78', 13, '0');
INSERT INTO produitpartype VALUES ('8', 7, '0');
INSERT INTO produitpartype VALUES ('8', 13, '0');
INSERT INTO produitpartype VALUES ('85', 3, '0');
INSERT INTO produitpartype VALUES ('86', 5, '0');
INSERT INTO produitpartype VALUES ('87', 3, '0');
INSERT INTO produitpartype VALUES ('88', 5, '0');
INSERT INTO produitpartype VALUES ('89', 5, '0');
INSERT INTO produitpartype VALUES ('9', 3, '0');
INSERT INTO produitpartype VALUES ('9', 5, '0');
INSERT INTO produitpartype VALUES ('9', 13, '0');
INSERT INTO produitpartype VALUES ('90', 5, '0');
INSERT INTO produitpartype VALUES ('91', 3, '0');
INSERT INTO produitpartype VALUES ('92', 5, '0');
INSERT INTO produitpartype VALUES ('93', 3, '0');
INSERT INTO produitpartype VALUES ('94', 3, '0');
INSERT INTO produitpartype VALUES ('95', 3, '0');
INSERT INTO produitpartype VALUES ('96', 3, '0');
INSERT INTO produitpartype VALUES ('97', 7, '0');
INSERT INTO produitpartype VALUES ('98', 3, '0');
INSERT INTO produitpartype VALUES ('98', 5, '0');
INSERT INTO produitpartype VALUES ('98', 7, '0');
INSERT INTO produitpartype VALUES ('98', 13, '0');
INSERT INTO produitpartype VALUES ('99', 3, '0');
INSERT INTO produitpartype VALUES ('A', 2, '0');
INSERT INTO produitpartype VALUES ('A0', 11, '0');
INSERT INTO produitpartype VALUES ('A1', 8, '0');
INSERT INTO produitpartype VALUES ('A2', 8, '0');
INSERT INTO produitpartype VALUES ('A3', 8, '0');
INSERT INTO produitpartype VALUES ('A4', 8, '0');
INSERT INTO produitpartype VALUES ('AP', 8, '0');
INSERT INTO produitpartype VALUES ('B', 2, '0');
INSERT INTO produitpartype VALUES ('BC', 12, '0');
INSERT INTO produitpartype VALUES ('BE', 13, '0');
INSERT INTO produitpartype VALUES ('BF', 8, '0');
INSERT INTO produitpartype VALUES ('BF', 13, '0');
INSERT INTO produitpartype VALUES ('BFM', 8, '0');
INSERT INTO produitpartype VALUES ('BFM', 13, '0');
INSERT INTO produitpartype VALUES ('BH', 12, '0');
INSERT INTO produitpartype VALUES ('BJ', 9, '0');
INSERT INTO produitpartype VALUES ('BJ/', 9, '0');
INSERT INTO produitpartype VALUES ('BO', 9, '0');
INSERT INTO produitpartype VALUES ('BO/', 9, '0');
INSERT INTO produitpartype VALUES ('BOI', 8, '0');
INSERT INTO produitpartype VALUES ('BOI', 9, '0');
INSERT INTO produitpartype VALUES ('BT', 12, '0');
INSERT INTO produitpartype VALUES ('BX', 9, '0');
INSERT INTO produitpartype VALUES ('BX/', 9, '0');
INSERT INTO produitpartype VALUES ('BY', 9, '0');
INSERT INTO produitpartype VALUES ('BY/', 9, '0');
INSERT INTO produitpartype VALUES ('C', 2, '0');
INSERT INTO produitpartype VALUES ('CA', 9, '0');
INSERT INTO produitpartype VALUES ('CA/', 9, '0');
INSERT INTO produitpartype VALUES ('CF', 10, '0');
INSERT INTO produitpartype VALUES ('CFO', 10, '0');
INSERT INTO produitpartype VALUES ('CH', 9, '0');
INSERT INTO produitpartype VALUES ('CH/', 9, '0');
INSERT INTO produitpartype VALUES ('CO', 11, '0');
INSERT INTO produitpartype VALUES ('CP', 9, '0');
INSERT INTO produitpartype VALUES ('CP/', 9, '0');
INSERT INTO produitpartype VALUES ('CR', 9, '0');
INSERT INTO produitpartype VALUES ('CR/', 9, '0');
INSERT INTO produitpartype VALUES ('CV', 10, '0');
INSERT INTO produitpartype VALUES ('D', 2, '0');
INSERT INTO produitpartype VALUES ('D', 5, '0');
INSERT INTO produitpartype VALUES ('DG', 8, '0');
INSERT INTO produitpartype VALUES ('DG1', 8, '0');
INSERT INTO produitpartype VALUES ('DG2', 8, '0');
INSERT INTO produitpartype VALUES ('DGO', 8, '0');
INSERT INTO produitpartype VALUES ('E', 2, '0');
INSERT INTO produitpartype VALUES ('F', 2, '0');
INSERT INTO produitpartype VALUES ('FF', 13, '0');
INSERT INTO produitpartype VALUES ('FN', 13, '0');
INSERT INTO produitpartype VALUES ('G', 2, '0');
INSERT INTO produitpartype VALUES ('GL', 14, '0');
INSERT INTO produitpartype VALUES ('GL1', 14, '0');
INSERT INTO produitpartype VALUES ('GL2', 14, '0');
INSERT INTO produitpartype VALUES ('GL3', 14, '0');
INSERT INTO produitpartype VALUES ('GLF', 8, '0');
INSERT INTO produitpartype VALUES ('GLF', 14, '0');
INSERT INTO produitpartype VALUES ('GLO', 14, '0');
INSERT INTO produitpartype VALUES ('GP', 14, '0');
INSERT INTO produitpartype VALUES ('H', 2, '0');
INSERT INTO produitpartype VALUES ('IR', 8, '0');
INSERT INTO produitpartype VALUES ('IR', 10, '0');
INSERT INTO produitpartype VALUES ('J', 11, '0');
INSERT INTO produitpartype VALUES ('MO', 9, '0');
INSERT INTO produitpartype VALUES ('MO/', 9, '0');
INSERT INTO produitpartype VALUES ('MU', 9, '0');
INSERT INTO produitpartype VALUES ('MU/', 9, '0');
INSERT INTO produitpartype VALUES ('O', 11, '0');
INSERT INTO produitpartype VALUES ('O/', 11, '0');
INSERT INTO produitpartype VALUES ('P', 2, '0');
INSERT INTO produitpartype VALUES ('PC', 13, '0');
INSERT INTO produitpartype VALUES ('RI', 9, '0');
INSERT INTO produitpartype VALUES ('RI/', 9, '0');
INSERT INTO produitpartype VALUES ('SC', 9, '0');
INSERT INTO produitpartype VALUES ('SC/', 9, '0');
INSERT INTO produitpartype VALUES ('ST', 9, '0');
INSERT INTO produitpartype VALUES ('ST/', 9, '0');
INSERT INTO produitpartype VALUES ('T', 10, '0');
INSERT INTO produitpartype VALUES ('T1', 3, '0');
INSERT INTO produitpartype VALUES ('T10', 3, '0');
INSERT INTO produitpartype VALUES ('T11', 3, '0');
INSERT INTO produitpartype VALUES ('T12', 3, '0');
INSERT INTO produitpartype VALUES ('T13', 7, '0');
INSERT INTO produitpartype VALUES ('T14', 7, '0');
INSERT INTO produitpartype VALUES ('T15', 5, '0');
INSERT INTO produitpartype VALUES ('T16', 5, '0');
INSERT INTO produitpartype VALUES ('T17', 7, '0');
INSERT INTO produitpartype VALUES ('T18', 3, '0');
INSERT INTO produitpartype VALUES ('T18', 5, '0');
INSERT INTO produitpartype VALUES ('T18', 7, '0');
INSERT INTO produitpartype VALUES ('T18', 13, '0');
INSERT INTO produitpartype VALUES ('T19', 3, '0');
INSERT INTO produitpartype VALUES ('T2', 5, '0');
INSERT INTO produitpartype VALUES ('T2', 13, '0');
INSERT INTO produitpartype VALUES ('T20', 3, '0');
INSERT INTO produitpartype VALUES ('T21', 3, '0');
INSERT INTO produitpartype VALUES ('T22', 6, '0');
INSERT INTO produitpartype VALUES ('T23', 3, '0');
INSERT INTO produitpartype VALUES ('T3', 3, '0');
INSERT INTO produitpartype VALUES ('T3', 13, '0');
INSERT INTO produitpartype VALUES ('T4', 3, '0');
INSERT INTO produitpartype VALUES ('T5', 3, '0');
INSERT INTO produitpartype VALUES ('T6', 7, '0');
INSERT INTO produitpartype VALUES ('T6', 13, '0');
INSERT INTO produitpartype VALUES ('T7', 5, '0');
INSERT INTO produitpartype VALUES ('T8', 3, '0');
INSERT INTO produitpartype VALUES ('T9', 3, '0');
INSERT INTO produitpartype VALUES ('TV', 9, '0');
INSERT INTO produitpartype VALUES ('TV/', 9, '0');
INSERT INTO produitpartype VALUES ('ABC', 2, '0');
INSERT INTO produitpartype VALUES ('104', 5, '0');
# --------------------------------------------------------

#
# Structure de la table `produits`
#

CREATE TABLE produits (
  codeProduit varchar(5) NOT NULL default '0',
  designation varchar(50) NOT NULL default '',
  prix float NOT NULL default '0',
  PRIMARY KEY  (codeProduit),
  KEY designation (designation),
  KEY prix (prix)
) TYPE=MyISAM;

#
# Contenu de la table `produits`
#

INSERT INTO produits VALUES ('12', 'SALADE  AUX  CREVETTES', '4.5');
INSERT INTO produits VALUES ('R', 'REPAS COMPLET :', '0');
INSERT INTO produits VALUES ('10', 'SOUPE  TONKINOISE  (PHO)', '5.5');
INSERT INTO produits VALUES ('9', 'SOUPE  PHNOM-PENH  SPECIALE', '5.5');
INSERT INTO produits VALUES ('8', 'SOUPE  AUX  VERMICELLES  CHINOIS  ET  AU  POULET', '3');
INSERT INTO produits VALUES ('7', 'SOUPE  AUX  NOUILLES  ET  AU  POULET', '3');
INSERT INTO produits VALUES ('6', 'SOUPE  DE  NOUILLES  AUX  RAVIOLIS  CHINOIS', '7');
INSERT INTO produits VALUES ('5', 'POTAGE  PIQUANT  PEKINOIS', '4.5');
INSERT INTO produits VALUES ('4', 'POTAGE  ASPERGES  AU  CRABE', '4.5');
INSERT INTO produits VALUES ('2', 'POTAGE  AUX  AILERONS  DE  REQUINS', '7');
INSERT INTO produits VALUES ('3', 'POTAGE  AUX  MAIS ET JAMBONS', '4');
INSERT INTO produits VALUES ('97', 'CAILLE  A  LA  SAUCE  CITRON', '7');
INSERT INTO produits VALUES ('96', 'GAMBAS  GRILLEES  AU  POIVRE', '11.5');
INSERT INTO produits VALUES ('95', 'NOUILLES  AUX  CREVETTES  SUR  PLAQUE  CHAUFFANTE', '11.5');
INSERT INTO produits VALUES ('94', 'CREVETTES  SUR  PLAQUE  CHAUFFANTE', '11.5');
INSERT INTO produits VALUES ('93', 'COQUILLES  SAINT-JACQUES  SUR  PLAQUE  CHAUFFANTE', '11.5');
INSERT INTO produits VALUES ('92', 'BOEUF  AU  SATE  SUR  PLAQUE  CHAUFFANTEO', '11');
INSERT INTO produits VALUES ('91', 'FRUITS  DE  MER  A  LIMPERIALE  SUR  PLAQUE  CHAU', '12');
INSERT INTO produits VALUES ('90', 'GIGOT  AU  POIVRE  SUR  PLAQUE  CHAUFFANTE', '10.5');
INSERT INTO produits VALUES ('89', 'GIGOT  THAœ SUR  PLAQUE  CHAUFFANTE ´†NouveautÈ†ª', '10.5');
INSERT INTO produits VALUES ('88', 'BOEUF  AU  POIVRE  SUR  PLAQUE  CHAUFFANTE', '11');
INSERT INTO produits VALUES ('87', 'FRUITS  DE  MER  AU  SATE  SUR  PLAQUE  CHAUFFANTE', '12');
INSERT INTO produits VALUES ('86', 'BOEUF  LOC-LAC´†NOUVEAUT', '9.5');
INSERT INTO produits VALUES ('85', 'MOULES AUX  BASILICS', '8');
INSERT INTO produits VALUES ('77', 'BROCOLIS  SAUTES  NATURES', '4');
INSERT INTO produits VALUES ('76', 'RIZ SAUTE  AU  POULET', '3.5');
INSERT INTO produits VALUES ('75', 'NOUILLES  SAUTEES  NATURES', '5');
INSERT INTO produits VALUES ('74', 'RIZ  COCOTTE  DU  CHEF', '7.5');
INSERT INTO produits VALUES ('73', 'RIZ  NATURE', '2');
INSERT INTO produits VALUES ('72', 'RIZ  CANTONAIS', '3.5');
INSERT INTO produits VALUES ('71', 'NOUILLES  SAUTEES  AU  BOEUF', '6');
INSERT INTO produits VALUES ('70', 'NOUILLES  SAUTEES  AU  PORC', '6');
INSERT INTO produits VALUES ('69', 'NOUILLES  SAUTEES  AU  POULET', '6');
INSERT INTO produits VALUES ('68', 'LEGUMES  CHOP-SUEY', '3.5');
INSERT INTO produits VALUES ('67', 'GERMES  DE  SOJA  SAUTES  NATURE', '3');
INSERT INTO produits VALUES ('66', 'CHAMPIGNONS  NOIRS  SAUTES  NATURE', '3.5');
INSERT INTO produits VALUES ('65', 'BOEUF  AUX  CHAMPIGNONS  NOIRS', '7');
INSERT INTO produits VALUES ('64', 'BOEUF  AUX POUSSES DE BAMBOU  ET  AUX CHAMPIGNONS', '7');
INSERT INTO produits VALUES ('63', 'BOEUF  AUX  POUSSES  DE  BAMBOU', '7');
INSERT INTO produits VALUES ('62', 'BOEUF  SAUCE  PIQUANTE', '7');
INSERT INTO produits VALUES ('61', 'BOEUF  SAUTE  AUX  BROCOLIS', '7');
INSERT INTO produits VALUES ('60', 'BOEUF  AU  CURRY', '7');
INSERT INTO produits VALUES ('59', 'BOEUF  AUX  GERMES  DE  SOJA', '7');
INSERT INTO produits VALUES ('58', 'BOEUF AUX  POIVRONS', '7');
INSERT INTO produits VALUES ('57', 'BOEUF  AUX  OIGNONS', '7');
INSERT INTO produits VALUES ('56', 'BOEUF  AUX  TOMATES', '7');
INSERT INTO produits VALUES ('55', 'PORC  AUX  CHAMPIGNONS  NOIRS', '6');
INSERT INTO produits VALUES ('54', 'PORC  AUX POUSSES DE BAMBOU  ET  AUX CHAMPIGNONS P', '6');
INSERT INTO produits VALUES ('53', 'PORC  A  LA  SAUCE  PIQUANTE  (EPICE)', '6');
INSERT INTO produits VALUES ('52', 'PORC  A  LA  SAUCE  AIGRE-DOUCE', '6');
INSERT INTO produits VALUES ('51', 'PORC  SAUTE  AUX  BROCOLIS', '6');
INSERT INTO produits VALUES ('50', 'PORC  AU  CURRY', '6');
INSERT INTO produits VALUES ('49', 'BOULETTES  DE  PORC  A  LA  SAUCE  PIQUANTE', '6');
INSERT INTO produits VALUES ('48', 'PORC  LAQUE', '6');
INSERT INTO produits VALUES ('47', 'FILET  DE  CANARD  LAQUE', '8.5');
INSERT INTO produits VALUES ('46', 'CANARD  AUX  ANANAS', '7');
INSERT INTO produits VALUES ('45', 'CANARD  AUX  CHAMPIGNONS  NOIRS', '7');
INSERT INTO produits VALUES ('44', 'CANARD AUX POUSSES DE BAMBOU ET AUX CHAMPIGNONS PA', '7');
INSERT INTO produits VALUES ('43', 'CANARD  AUX  POUSSES  DE  BAMBOU', '7');
INSERT INTO produits VALUES ('42', 'CANARD  AU  CURRY', '7');
INSERT INTO produits VALUES ('41', 'POULET  CHOP-SUEY', '6');
INSERT INTO produits VALUES ('40', 'POULET  AU  CURRY', '4.5');
INSERT INTO produits VALUES ('39', 'POULET  A  L?IMPERIALE', '6.5');
INSERT INTO produits VALUES ('38', 'POULET  AUX  AMANDES', '6.5');
INSERT INTO produits VALUES ('37', 'POULET  AUX  NOIX  DE  CAJOU', '6.5');
INSERT INTO produits VALUES ('36', 'POULET  AUX  ANANAS', '6');
INSERT INTO produits VALUES ('35', 'POULET  AUX  CHAMPIGNONS  NOIRS', '6');
INSERT INTO produits VALUES ('34', 'POULET  AUX  POUSSES DE BAMBOU  ET  AUX CHAMPIGNON', '6');
INSERT INTO produits VALUES ('33', 'COQUILLES  SAINT-JACQUES  SAUTEES  NATURE', '10');
INSERT INTO produits VALUES ('32', 'COQUILLES  SAINT-JACQUES  A  L?IMPERIALE  (PIQUANT', '10');
INSERT INTO produits VALUES ('31', 'BEIGNETS  DE  COQUILLES  SAINT-JACQUES', '7.5');
INSERT INTO produits VALUES ('30', 'BEIGNETS  DE  POISSON  AU  CURRY', '4.5');
INSERT INTO produits VALUES ('29', 'BEIGNETS  DE  POISSON  A  LA  SAUCE  AIGRE-DOUCE', '4.5');
INSERT INTO produits VALUES ('28', 'BEIGNETS  DE  POISSON', '4.5');
INSERT INTO produits VALUES ('27', 'SEICHE  SAUTEE  AU  POIVRE  PARFUME', '6');
INSERT INTO produits VALUES ('26', 'SEICHE  A  L?IMPERIALE  (PIQUANTE)', '6');
INSERT INTO produits VALUES ('25', 'SEICHE  SAUTE  AU  SATE  ET  AUX  BROCOLIS´†Nouvea', '6');
INSERT INTO produits VALUES ('24', 'CREVETTES  A  L?IMPERIALE  (PIQUANTE)', '9');
INSERT INTO produits VALUES ('23', 'CREVETTES  SAUTEES  NATURE', '9');
INSERT INTO produits VALUES ('22', 'CREVETTES  AUX  CREPES  DE RIZ', '7');
INSERT INTO produits VALUES ('21', 'BEIGNETS DE CREVETTES A LA SAUCE AIGRE-DOUCE', '7');
INSERT INTO produits VALUES ('20', 'BEIGNETS  DE  CREVETTES  AU  CURRY', '7');
INSERT INTO produits VALUES ('19', 'BEIGNETS  DE  RAVIOLIS', '5.5');
INSERT INTO produits VALUES ('18', 'BEIGNETS DE CREVETTES', '7.5');
INSERT INTO produits VALUES ('H', 'CREPES  DE  RIZ  AU  PORC  (BANH CUONG', '3');
INSERT INTO produits VALUES ('G', 'BRIOCHES  AU  PORC  LAQUE', '3');
INSERT INTO produits VALUES ('E', 'BOUCHES AUX CREVETTES†´ NouveautÈ†ª', '4.5');
INSERT INTO produits VALUES ('D', 'RAVIOLIS  DE  BOEUF  A  LA  VAPEUR', '4');
INSERT INTO produits VALUES ('C', 'PETITS  CROISSANTS  A  LA  VAPEUR', '3.5');
INSERT INTO produits VALUES ('B', 'RAVIOLIS  DE PORC  A  LA  VAPEUR', '4');
INSERT INTO produits VALUES ('A', 'RAVIOLIS  DE  CREVETTES  A  LA VAPEUR', '4.5');
INSERT INTO produits VALUES ('17', 'OMELETTE  AU POULET', '3.5');
INSERT INTO produits VALUES ('16', 'OMELETTE  AU  CRABE', '4');
INSERT INTO produits VALUES ('15', 'OMELETTE  AUX  CREVETTES', '4');
INSERT INTO produits VALUES ('14', 'SALADE  AU  POULET', '4');
INSERT INTO produits VALUES ('13', 'SALADE  AU  CRABE', '5.5');
INSERT INTO produits VALUES ('1', 'SOUPE AUX TROIS FRAICHEURS', '7.5');
INSERT INTO produits VALUES ('98', 'FONDUE  VIETNAMIENNE  (POUR 2 PERSONNES)', '43');
INSERT INTO produits VALUES ('99', 'BOULETTES  DE  CREVETTES  DU  CHEF', '5.5');
INSERT INTO produits VALUES ('101', 'HORS D OEUVRE SPECIAL CHAUD(POUR 2 PERSONNES)', '10.5');
INSERT INTO produits VALUES ('102', 'GAMBAS  SAUTEES  SPECIALITE  DU  CHEF  (PIQUANTE)', '11.5');
INSERT INTO produits VALUES ('103', 'CREVETTES  AUX  QUEUES  DE  PHENIX', '11');
INSERT INTO produits VALUES ('104', 'ORS D\'OEUVRE SPECIAL  CHAUD(2 PERSONNES)', '18.5');
INSERT INTO produits VALUES ('105', 'CREVETTES  AUX  GINGEMBRE  ET  CIBOULETTE', '9.5');
INSERT INTO produits VALUES ('106', 'GIGOT  SAUTE AUX GIMGEMBRES', '9');
INSERT INTO produits VALUES ('107', 'MARMITE DE FRUIT DE MER', '9.5');
INSERT INTO produits VALUES ('108', 'LES  TROIS  MERVEILLES', '9.5');
INSERT INTO produits VALUES ('109', 'NID  DE  CRUSTACES  VARIES', '9.5');
INSERT INTO produits VALUES ('110', 'POULET  FRIT  A  LA  CANTONAISE', '7');
INSERT INTO produits VALUES ('111', 'BEIGNETS  DE  CUISSES  DE  GRENOUILLES', '8');
INSERT INTO produits VALUES ('112', 'CUISSES  DE  GRENOUILLES  A  L\'IMPERIALE', '8');
INSERT INTO produits VALUES ('113', 'CUISSES  DE  GRENOUILLES  SAUTEES  SPECIALITE  DU', '8');
INSERT INTO produits VALUES ('114', 'CUISSES  DE  GRENOUILLES  A  LA  SAUCE  PIQUANTE', '8');
INSERT INTO produits VALUES ('115', 'CUISSES  DE  GRENOUILLES  SAUTEES  AU  POIVRE  PAR', '8');
INSERT INTO produits VALUES ('116', 'CREVETTES  SAUTEES  AUX  BROCOLIS', '9');
INSERT INTO produits VALUES ('117', 'FRUITS  DE  MER  SAUTES  AUX  BROCOLIS', '9.5');
INSERT INTO produits VALUES ('118', 'POULET  GRILLE  AU  POIVRE  DE  CAYENNE', '7.5');
INSERT INTO produits VALUES ('119', 'FILETS  DE  CABILLAUD  A  LA  VAPEUR', '7.5');
INSERT INTO produits VALUES ('120', 'TRAVERS  DE  PORC  PEKINOIS', '9');
INSERT INTO produits VALUES ('121', 'BEIGNET  DE  POULET  AU  CITRON', '5.5');
INSERT INTO produits VALUES ('122', 'FILET  DE  CABILLAUD  A  LA  CANTONAISE', '7.5');
INSERT INTO produits VALUES ('123', 'PORC  AU  CARAMEL', '6');
INSERT INTO produits VALUES ('124', 'ROULEAU  DE  PRINTEMPS  (GOI-CUON)', '3.5');
INSERT INTO produits VALUES ('125', 'BOEUF  AUX  VERMICELLES  DE  RIZ  (BO-BONG', '7.5');
INSERT INTO produits VALUES ('126', 'BROCHETTES  DE  BOEUF  GRILLE  (BO-LUI', '6');
INSERT INTO produits VALUES ('127', 'BOULETTES  DE  PORC  GRILLE  (NEM-NUONG)', '7');
INSERT INTO produits VALUES ('128', 'CREVETTES  GRILLEES  (TOM-NUONG)', '12');
INSERT INTO produits VALUES ('129', 'TRAVERS  DE  PORC  GRILLES  A  LA  CITRONELLE', '11');
INSERT INTO produits VALUES ('130', 'BOEUF  SAUTE  AU  GINGEMBRE  (CIBOULETTE', '7');
INSERT INTO produits VALUES ('131', 'FILETS  DE  POISSON  SAUTES  AU  GINGEMBRE  (CIBOU', '6.5');
INSERT INTO produits VALUES ('132', 'CANARD  AU  CARAMEL  (CITRON)', '8.5');
INSERT INTO produits VALUES ('T1', 'SOUPE  AUX  CREVETTES  PIMENTEES', '8.5');
INSERT INTO produits VALUES ('T2', 'SALADE  DE  B?UF  MACERE  PIMENTE', '7');
INSERT INTO produits VALUES ('T3', 'SALADE  THAI  AUX  CREVETTES  PIMENTEES', '8.5');
INSERT INTO produits VALUES ('T4', 'POTAGE  AUX  CREVETTES  AU  LAIT  DE  COCO  EPICE', '8.5');
INSERT INTO produits VALUES ('T5', 'POTAGE  DE  COQUILLES  SAINT-JACQUES  AU  LAIT  DE', '8.5');
INSERT INTO produits VALUES ('T6', 'PATES  IMPERIAUX  THAI', '5.5');
INSERT INTO produits VALUES ('T7', 'TRAVERS  DE  PORC  AU  POIVRE  PARFUME', '9');
INSERT INTO produits VALUES ('T8', 'FRUITS  DE  MER  SAUTES  AU  BASILIC  ET  PIMENT', '10');
INSERT INTO produits VALUES ('T9', 'CREVETTES  SAUTEES  AU  BASILIC  ET  PIMENT  FRAIS', '9');
INSERT INTO produits VALUES ('T10', 'COQUILLES  SAINT-JACQUES  SAUTEES  AU  BASILIC  ET', '10');
INSERT INTO produits VALUES ('T11', 'CUISSES  DE  GRENOUILLES  SAUTEES  AU  BASILIC  ET', '8');
INSERT INTO produits VALUES ('T12', 'SEICHE  SAUTEE  AU  BASILIC  ET  PIMENT  FRAIS', '6.5');
INSERT INTO produits VALUES ('T13', 'CANARD  SAUTE  AU  BASILIC  ET  PIMENT  FRAIS', '7.5');
INSERT INTO produits VALUES ('T14', 'POULET  SAUTE  AU  BASILIC  ET  PIMENT  FRAIS', '6.5');
INSERT INTO produits VALUES ('T15', 'BOEUF SAUTE AU BASILIC ET PIMENT FRAIS', '7.5');
INSERT INTO produits VALUES ('T16', 'BROCHETTES  DE  GIGOT  GRILLE  AUX  EPICES', '6.5');
INSERT INTO produits VALUES ('T17', 'BROCHETTES  DE  POULET  GRILLE  AUX  EPICES', '6.5');
INSERT INTO produits VALUES ('T18', 'FONDUE  THAILANDAISE  (POUR 2 PERSONNES)', '43');
INSERT INTO produits VALUES ('T19', 'POISSON  A  LA  SAUCE  PATTAYA', '9');
INSERT INTO produits VALUES ('T20', 'SOLE  ENTIERE  A  LA  VAPEUR', '13.5');
INSERT INTO produits VALUES ('T21', 'SOLE  FRITE', '13.5');
INSERT INTO produits VALUES ('T22', 'RIZ  GLUANT', '2.5');
INSERT INTO produits VALUES ('F', 'ASSORTIMENT A LA VAPEUR', '6');
INSERT INTO produits VALUES ('100', 'CRABE FARCI SAIGONNAIS (A LA VAPEUR)', '7');
INSERT INTO produits VALUES ('RI/', '1/2 RIESLING', '8.5');
INSERT INTO produits VALUES ('RI', 'RIESLING', '14');
INSERT INTO produits VALUES ('T23', 'HA MOK', '9');
INSERT INTO produits VALUES ('CO', 'COCA-COLA, ORANGINA TONIC JUS DE FRUIT', '3');
INSERT INTO produits VALUES ('A2', 'COKTAIL MAISON', '3.5');
INSERT INTO produits VALUES ('A3', 'WHISKY', '4.5');
INSERT INTO produits VALUES ('A1', 'PORTO, MARTINI, SUZE, RICARD, PASTIS, KIR, AMERICA', '3');
INSERT INTO produits VALUES ('GC', 'GATEAU COCO', '3');
INSERT INTO produits VALUES ('CF', 'CAFE DECAFEINE', '2');
INSERT INTO produits VALUES ('B2', '2 BOULES DE GLACE', '3.5');
INSERT INTO produits VALUES ('TV', 'TAVEL', '18');
INSERT INTO produits VALUES ('TV/', '1/2 TAVEL', '10.5');
INSERT INTO produits VALUES ('DS', 'FRUITS AU SIROP(LYCHEES, LONGANES, MANGUES, FRAISE', '2.5');
INSERT INTO produits VALUES ('1/4', '1/4 VIN DE TABLE', '2.5');
INSERT INTO produits VALUES ('BC', 'BIERE CHINOISE', '3.5');
INSERT INTO produits VALUES ('CP', 'COTE DE PROVENCE', '11');
INSERT INTO produits VALUES ('CP/', '1/2 COTE DE PROVENCE', '7.5');
INSERT INTO produits VALUES ('CR', 'COTE DU RHONE', '11');
INSERT INTO produits VALUES ('CR/', '1/2 COTE DU RHONE', '7.5');
INSERT INTO produits VALUES ('CA', 'CABERNET D ANJOU', '11');
INSERT INTO produits VALUES ('ST', 'SAINT TROPEZ', '15');
INSERT INTO produits VALUES ('ST/', '1/2 ST TROPEZ', '9.5');
INSERT INTO produits VALUES ('BX', 'BORDEAUX', '14');
INSERT INTO produits VALUES ('BX/', '1/2 BORDEAUX', '8.5');
INSERT INTO produits VALUES ('BJ', 'BEAUJOLAIS VILLAGES', '14.5');
INSERT INTO produits VALUES ('BJ/', '1/2 BEAUJOLAIS VILLAGES', '9');
INSERT INTO produits VALUES ('BO', 'BOURGOGNE', '17.5');
INSERT INTO produits VALUES ('BO/', '1/2 BOURGOGNE', '9.5');
INSERT INTO produits VALUES ('SC', 'SAUMUR', '18');
INSERT INTO produits VALUES ('SC/', '1/2 SAUMUR', '10');
INSERT INTO produits VALUES ('BY', 'BROUILLY', '18.5');
INSERT INTO produits VALUES ('BY/', '1/2 BROUILLY', '10.5');
INSERT INTO produits VALUES ('MO', 'MOULIN A VENT', '19.5');
INSERT INTO produits VALUES ('MO/', '1/2 MOULIN A VENT', '11');
INSERT INTO produits VALUES ('MU', 'MUSCADET', '12');
INSERT INTO produits VALUES ('MU/', '1/2 MUSCADET', '7.5');
INSERT INTO produits VALUES ('CH', 'CHAMPAGNE', '45');
INSERT INTO produits VALUES ('CH/', '1/2 CHAMPAGNE', '24.5');
INSERT INTO produits VALUES ('BT', 'BIERE THAI', '4');
INSERT INTO produits VALUES ('BH', 'CALSBERG HEINEKEN', '3.5');
INSERT INTO produits VALUES ('O', 'VITTEL 1L BADOIT 1L', '4.5');
INSERT INTO produits VALUES ('O/', '1/2 VITTEL 1/2 BADOIT PERRIER', '3');
INSERT INTO produits VALUES ('DG1', 'ALCOOL DE RIZ', '3.5');
INSERT INTO produits VALUES ('DG', 'COINTREAU GRAND MARNIER CALVADOS', '3.5');
INSERT INTO produits VALUES ('DG2', 'COGNAC', '4.5');
INSERT INTO produits VALUES ('A4', 'TEQUILA VOLDKA SOHO MALIBU GIN GET 27', '4.5');
INSERT INTO produits VALUES ('M1', 'MENU MIDI', '11');
INSERT INTO produits VALUES ('M2', 'MENU MIDI', '10');
INSERT INTO produits VALUES ('M3', 'MENU MIDI', '9');
INSERT INTO produits VALUES ('M27', 'MENU DRAGON', '27');
INSERT INTO produits VALUES ('M20', 'MENU OISEAU PARADIS', '20');
INSERT INTO produits VALUES ('M14', 'MENU A 14.5', '14.5');
INSERT INTO produits VALUES ('GL', 'MYSTERE MOUSCOCO TRUFFE.....', '3.5');
INSERT INTO produits VALUES ('GL2', 'GLACE LIEGEOIS', '5.5');
INSERT INTO produits VALUES ('GL3', 'COLONEL BAN SPLIT', '6.5');
INSERT INTO produits VALUES ('B1', 'BOULE DE GLACE', '2');
INSERT INTO produits VALUES ('B3', 'SORBET DE GLACE', '4.5');
INSERT INTO produits VALUES ('GL1', 'SOUFFLE MAN COCO ANANAS GIVRE....', '4.5');
INSERT INTO produits VALUES ('GP', 'PROFITEROL MANGUE GIVRE', '6');
INSERT INTO produits VALUES ('P', 'PERLE DE COCO', '3.5');
INSERT INTO produits VALUES ('BE', 'BEIGNET DE FRUIT', '3');
INSERT INTO produits VALUES ('BF', 'BEIGNET FLAMBE', '5.5');
INSERT INTO produits VALUES ('BFM', 'BEIGNET FLAMBE MKL', '6');
INSERT INTO produits VALUES ('PC', 'BEIGNET AU CARAMEL', '7');
INSERT INTO produits VALUES ('COU', 'COUPE DE FRUIT', '3.5');
INSERT INTO produits VALUES ('AC', 'ASSORTIMENT CONFIT', '3.5');
INSERT INTO produits VALUES ('AG', 'ANANAS GIN KIRSH', '3.5');
INSERT INTO produits VALUES ('AS', 'ANANAS SIROP', '2');
INSERT INTO produits VALUES ('FN', 'FRUIT NATURE', '2');
INSERT INTO produits VALUES ('FF', 'MANGUE NATURE', '6');
INSERT INTO produits VALUES ('IR', 'IRISH COOFEE', '7');
INSERT INTO produits VALUES ('AP', 'APPERITIF OFFERT', '0');
INSERT INTO produits VALUES ('BOI', 'BOISSON OFFERT', '0');
INSERT INTO produits VALUES ('11', 'NEM', '4.5');
INSERT INTO produits VALUES ('CFO', 'CAFE OFFERT', '0');
INSERT INTO produits VALUES ('T', 'THE,VERVEINE,NOISETTE', '2.5');
INSERT INTO produits VALUES ('EM', 'PLAT A EMPORTER', '0');
INSERT INTO produits VALUES ('A0', 'COCKTAIL SANS ALCOOL', '3');
INSERT INTO produits VALUES ('78', 'NOUILLE AUX CREVETTES', '9');
INSERT INTO produits VALUES ('S', 'BIERE THAI', '4');
INSERT INTO produits VALUES ('DGO', 'DIGESTIF OFFERT', '0');
INSERT INTO produits VALUES ('73B', 'RIZ LOC-LAC', '3');
INSERT INTO produits VALUES ('SU', 'SUPPLEMENT', '1');
INSERT INTO produits VALUES ('CA/', '1/2CABERNET D ANJOU', '7.5');
INSERT INTO produits VALUES ('J', 'COCA-COLA, ORANGINA TONIC JUS DE FRUIT', '3');
INSERT INTO produits VALUES ('CV', 'CHOCOLAT OU CAFE VIENNOIS, CAPUCCINO', '3');
INSERT INTO produits VALUES ('PO', 'PLAT OFFERT', '0');
INSERT INTO produits VALUES ('GLF', 'GLACE FLAMBE', '7.5');
INSERT INTO produits VALUES ('GLO', 'GLACE OFFERTE', '0');
INSERT INTO produits VALUES ('GR', 'SIROP', '1');
INSERT INTO produits VALUES ('ABC', 'VAPEUR ABC', '15');
# --------------------------------------------------------

#
# Structure de la table `recettesjournalieres`
#

CREATE TABLE recettesjournalieres (
  idRecetteJour int(10) unsigned NOT NULL auto_increment,
  dateRecette date NOT NULL default '0000-00-00',
  dateCloture date default NULL,
  espece float NOT NULL default '0',
  ticket float NOT NULL default '0',
  cheque float NOT NULL default '0',
  carte float NOT NULL default '0',
  montant float NOT NULL default '0',
  typetable int(1) NOT NULL default '0',
  impaye float NOT NULL default '0',
  dateImpression date default NULL,
  PRIMARY KEY  (idRecetteJour),
  KEY dateRecette (dateRecette),
  KEY espece (espece),
  KEY ticket (ticket),
  KEY cheque (cheque),
  KEY carte (carte),
  KEY montant (montant),
  KEY impaye (impaye)
) TYPE=MyISAM;

#
# Contenu de la table `recettesjournalieres`
#

INSERT INTO recettesjournalieres VALUES (3, '2002-08-03', '2002-08-03', '16', '17.5', '391.25', '1103.25', '1528', 0, '0', '2002-08-03');
INSERT INTO recettesjournalieres VALUES (2, '2002-08-02', '2002-09-19', '10.5', '0', '91.5', '1153', '1255', 0, '0', '2002-08-02');
INSERT INTO recettesjournalieres VALUES (92, '2002-09-20', '2002-09-26', '0', '0', '0', '0', '0', 1, '0', '2002-09-20');
INSERT INTO recettesjournalieres VALUES (4, '2002-08-08', '2002-09-19', '0', '0', '0', '19', '19', 1, '0', '2002-08-08');
INSERT INTO recettesjournalieres VALUES (5, '2002-08-08', '2002-09-19', '20.9', '19.6', '541.5', '525', '1107', 0, '0', '2002-08-08');
INSERT INTO recettesjournalieres VALUES (6, '2002-08-09', '2002-09-19', '0', '0', '0', '20', '20', 1, '0', '2002-08-09');
INSERT INTO recettesjournalieres VALUES (7, '2002-08-09', '2002-09-19', '33.5', '0', '193', '409.5', '636', 0, '0', '2002-08-09');
INSERT INTO recettesjournalieres VALUES (8, '2002-08-10', '2002-08-10', '0', '0', '0', '150', '150', 1, '0', '2002-08-10');
INSERT INTO recettesjournalieres VALUES (9, '2002-08-10', '2002-08-10', '15', '13.5', '343', '1035.5', '1407', 0, '0', '2002-08-10');
INSERT INTO recettesjournalieres VALUES (10, '2002-08-01', '2002-09-19', '0', '0', '46.5', '130', '176.5', 1, '0', '2002-08-01');
INSERT INTO recettesjournalieres VALUES (11, '2002-08-01', '2002-09-19', '153', '0', '402.5', '896.5', '1452', 0, '0', '2002-08-01');
INSERT INTO recettesjournalieres VALUES (12, '2002-08-02', '2002-09-19', '0', '0', '21', '72', '93', 1, '0', '2002-08-02');
INSERT INTO recettesjournalieres VALUES (13, '2002-08-03', '2002-08-03', '0', '0', '0', '23', '23', 1, '0', '2002-08-03');
INSERT INTO recettesjournalieres VALUES (14, '2002-08-04', '2002-08-03', '0', '0', '0', '102', '102', 1, '0', '2002-08-04');
INSERT INTO recettesjournalieres VALUES (15, '2002-08-04', '2002-08-03', '0', '0', '576', '1179.5', '1755.5', 0, '0', '2002-08-04');
INSERT INTO recettesjournalieres VALUES (16, '2002-08-06', '2002-09-19', '0', '0', '0', '87', '87', 1, '0', '2002-08-06');
INSERT INTO recettesjournalieres VALUES (17, '2002-08-06', '2002-09-19', '8.6', '120.65', '156.75', '735.5', '1021.5', 0, '0', '2002-08-06');
INSERT INTO recettesjournalieres VALUES (18, '2002-08-07', '2002-09-19', '0', '0', '0', '22.5', '22.5', 1, '0', '2002-08-07');
INSERT INTO recettesjournalieres VALUES (19, '2002-08-07', '2002-09-19', '15.4', '14.1', '565.5', '755', '1350', 0, '0', '2002-08-07');
INSERT INTO recettesjournalieres VALUES (20, '2002-08-11', '2002-09-19', '0', '0', '0', '78', '78', 1, '0', '2002-08-11');
INSERT INTO recettesjournalieres VALUES (21, '2002-08-11', '2002-09-19', '10.5', '0', '348.5', '808', '1167', 0, '0', '2002-08-11');
INSERT INTO recettesjournalieres VALUES (22, '2002-08-13', '2002-09-19', '0', '0', '0', '11', '11', 1, '0', '2002-08-13');
INSERT INTO recettesjournalieres VALUES (23, '2002-08-13', '2002-09-19', '20', '20', '142', '400.5', '582.5', 0, '0', '2002-08-13');
INSERT INTO recettesjournalieres VALUES (24, '2002-08-14', '2002-09-19', '42', '0', '0', '54.5', '96.5', 1, '0', '2002-08-14');
INSERT INTO recettesjournalieres VALUES (25, '2002-08-14', '2002-09-19', '14.22', '57.78', '184', '904.5', '1160.5', 0, '0', '2002-08-14');
INSERT INTO recettesjournalieres VALUES (26, '2002-08-15', '2002-09-19', '11.5', '0', '0', '16', '27.5', 1, '0', '2002-08-15');
INSERT INTO recettesjournalieres VALUES (27, '2002-08-15', '2002-09-19', '0', '30', '380.5', '1174', '1584.5', 0, '0', '2002-08-15');
INSERT INTO recettesjournalieres VALUES (28, '2002-08-16', '2002-09-19', '0', '0', '0', '55', '55', 1, '0', '2002-08-16');
INSERT INTO recettesjournalieres VALUES (29, '2002-08-16', '2002-09-19', '9.5', '19.5', '125', '790.5', '944.5', 0, '0', '2002-08-16');
INSERT INTO recettesjournalieres VALUES (30, '2002-08-17', '2002-09-19', '0', '0', '0', '41', '41', 1, '0', '2002-08-17');
INSERT INTO recettesjournalieres VALUES (31, '2002-08-17', '2002-09-19', '0', '12', '274.5', '430', '716.5', 0, '0', '2002-08-17');
INSERT INTO recettesjournalieres VALUES (32, '2002-08-18', '2002-09-19', '0', '0', '0', '101', '101', 1, '0', '2002-08-18');
INSERT INTO recettesjournalieres VALUES (33, '2002-08-18', '2002-09-19', '18.5', '20', '358.5', '850', '1247', 0, '0', '2002-08-18');
INSERT INTO recettesjournalieres VALUES (34, '2002-08-20', '2002-09-19', '0', '0', '19.5', '71.5', '91', 1, '0', '2002-08-20');
INSERT INTO recettesjournalieres VALUES (35, '2002-08-20', '2002-09-19', '15.9', '6.1', '75', '638', '735', 0, '0', '2002-08-20');
INSERT INTO recettesjournalieres VALUES (36, '2002-08-21', '2002-09-19', '0', '0', '0', '75', '75', 1, '0', '2002-08-21');
INSERT INTO recettesjournalieres VALUES (37, '2002-08-21', '2002-09-19', '9.5', '0', '425.5', '537', '972', 0, '0', '2002-08-21');
INSERT INTO recettesjournalieres VALUES (38, '2002-08-22', '2002-09-19', '0', '0', '0', '22.5', '22.5', 1, '0', '2002-08-22');
INSERT INTO recettesjournalieres VALUES (39, '2002-08-22', '2002-09-19', '73', '6', '515', '1307.5', '1901.5', 0, '0', '2002-08-22');
INSERT INTO recettesjournalieres VALUES (40, '2002-08-23', '2002-09-19', '30.5', '0', '26', '86', '142.5', 1, '0', '2002-08-23');
INSERT INTO recettesjournalieres VALUES (41, '2002-08-23', '2002-09-19', '9.6', '9.2', '385.3', '1327.9', '1732', 0, '0', '2002-08-23');
INSERT INTO recettesjournalieres VALUES (42, '2002-08-24', '2002-09-19', '0', '0', '76', '250.1', '326.1', 1, '0', '2002-08-24');
INSERT INTO recettesjournalieres VALUES (43, '2002-08-24', '2002-09-19', '0', '0', '312', '804', '1116', 0, '0', '2002-08-24');
INSERT INTO recettesjournalieres VALUES (44, '2002-08-25', '2002-09-19', '0', '0', '0', '88', '88', 1, '0', '2002-08-25');
INSERT INTO recettesjournalieres VALUES (45, '2002-08-25', '2002-09-19', '17', '6.5', '442.5', '1076.5', '1542.5', 0, '0', '2002-08-25');
INSERT INTO recettesjournalieres VALUES (46, '2002-08-27', '2002-09-19', '0', '0', '0', '158.5', '158.5', 1, '0', '2002-08-27');
INSERT INTO recettesjournalieres VALUES (47, '2002-08-27', '2002-09-19', '114', '122', '310', '309.5', '855.5', 0, '0', '2002-08-27');
INSERT INTO recettesjournalieres VALUES (48, '2002-08-28', '2002-09-19', '0', '0', '0', '60', '60', 1, '0', '2002-08-28');
INSERT INTO recettesjournalieres VALUES (49, '2002-08-28', '2002-09-19', '10', '0', '191', '392.5', '593.5', 0, '0', '2002-08-28');
INSERT INTO recettesjournalieres VALUES (50, '2002-08-29', '2002-09-19', '0', '0', '0', '73', '73', 1, '0', '2002-08-29');
INSERT INTO recettesjournalieres VALUES (51, '2002-08-29', '2002-09-19', '12.9', '14', '288.5', '765.6', '1081', 0, '0', '2002-08-29');
INSERT INTO recettesjournalieres VALUES (52, '2002-08-30', '2002-09-19', '0', '0', '0', '197.5', '197.5', 1, '0', '2002-08-30');
INSERT INTO recettesjournalieres VALUES (53, '2002-08-30', '2002-09-19', '18.5', '29', '437', '1231.5', '1716', 0, '0', '2002-08-30');
INSERT INTO recettesjournalieres VALUES (54, '2002-08-31', '2002-09-19', '0', '0', '0', '141', '141', 1, '0', '2002-08-31');
INSERT INTO recettesjournalieres VALUES (55, '2002-08-31', '2002-09-19', '18.8', '89.2', '818.5', '1257.5', '2184', 0, '0', '2002-08-31');
INSERT INTO recettesjournalieres VALUES (56, '2002-09-12', '2002-09-20', '0', '0', '0', '15.5', '15.5', 1, '0', '2002-09-12');
INSERT INTO recettesjournalieres VALUES (57, '2002-09-12', '2002-09-20', '19.5', '31', '262', '432', '744.5', 0, '0', '2002-09-12');
INSERT INTO recettesjournalieres VALUES (58, '2002-09-13', '2002-09-20', '0', '0', '0', '67.5', '67.5', 1, '0', '2002-09-13');
INSERT INTO recettesjournalieres VALUES (59, '2002-09-13', '2002-09-20', '28.5', '15', '316', '648.5', '1008', 0, '0', '2002-09-13');
INSERT INTO recettesjournalieres VALUES (60, '2002-09-14', '2002-09-20', '0', '0', '0', '42', '42', 1, '0', '2002-09-14');
INSERT INTO recettesjournalieres VALUES (61, '2002-09-14', '2002-09-20', '18.3', '62', '507', '1160.7', '1748', 0, '0', '2002-09-14');
INSERT INTO recettesjournalieres VALUES (62, '2002-09-11', '2002-09-20', '22.5', '0', '225.49', '102.5', '350.49', 1, '0', '2002-09-11');
INSERT INTO recettesjournalieres VALUES (63, '2002-09-11', '2002-09-20', '0', '15.24', '39', '296.26', '350.5', 0, '0', '2002-09-11');
INSERT INTO recettesjournalieres VALUES (64, '2002-09-15', '2002-09-20', '0', '0', '0', '92.5', '92.5', 1, '0', '2002-09-15');
INSERT INTO recettesjournalieres VALUES (65, '2002-09-15', '2002-09-20', '0', '0', '434.75', '460.25', '895', 0, '0', '2002-09-15');
INSERT INTO recettesjournalieres VALUES (66, '2002-09-16', '2002-09-20', '0', '0', '0', '113', '113', 1, '0', '2002-09-16');
INSERT INTO recettesjournalieres VALUES (67, '2002-09-16', '2002-09-20', '17.8', '12.2', '166.5', '716.5', '913', 0, '0', '2002-09-16');
INSERT INTO recettesjournalieres VALUES (68, '2002-09-17', '2002-09-20', '0', '0', '0', '100', '100', 1, '0', '2002-09-17');
INSERT INTO recettesjournalieres VALUES (69, '2002-09-17', '2002-09-20', '17', '13', '141.75', '769.75', '941.5', 0, '0', '2002-09-17');
INSERT INTO recettesjournalieres VALUES (70, '2002-09-18', '2002-09-20', '0', '0', '74.5', '86', '153.5', 1, '0', '2002-09-18');
INSERT INTO recettesjournalieres VALUES (71, '2002-09-18', '2002-09-20', '31.5', '0', '0', '173.5', '205', 0, '0', '2002-09-18');
INSERT INTO recettesjournalieres VALUES (72, '2002-09-19', '2002-09-20', '0', '0', '0', '67', '67', 1, '0', '2002-09-19');
INSERT INTO recettesjournalieres VALUES (73, '2002-09-19', '2002-09-20', '33.5', '5.8', '216.2', '576', '831.5', 0, '0', '2002-09-19');
INSERT INTO recettesjournalieres VALUES (74, '2002-09-01', '2002-09-20', '0', '0', '0', '165', '165', 1, '0', '2002-09-01');
INSERT INTO recettesjournalieres VALUES (75, '2002-09-01', '2002-09-20', '14.2', '23.8', '373', '818.5', '1229.5', 0, '0', '2002-09-01');
INSERT INTO recettesjournalieres VALUES (76, '2002-09-09', '2002-09-20', '0', '0', '0', '42', '42', 1, '0', '2002-09-09');
INSERT INTO recettesjournalieres VALUES (77, '2002-09-09', '2002-09-20', '62', '0', '161.5', '279.5', '503', 0, '0', '2002-09-09');
INSERT INTO recettesjournalieres VALUES (78, '2002-09-08', '2002-09-20', '0', '0', '0', '0', '0', 1, '0', '2002-09-08');
INSERT INTO recettesjournalieres VALUES (79, '2002-09-08', '2002-09-20', '0', '0', '306', '395', '701', 0, '0', '2002-09-08');
INSERT INTO recettesjournalieres VALUES (80, '2002-09-10', '2002-09-20', '0', '0', '0', '60', '60', 1, '0', '2002-09-10');
INSERT INTO recettesjournalieres VALUES (81, '2002-09-10', '2002-09-20', '21', '42', '149.5', '487', '699.5', 0, '0', '2002-09-10');
INSERT INTO recettesjournalieres VALUES (82, '2002-09-07', '2002-09-20', '0', '0', '0', '113', '113', 1, '0', '2002-09-07');
INSERT INTO recettesjournalieres VALUES (83, '2002-09-07', '2002-09-20', '17.5', '14.3', '593.2', '862.5', '1487.5', 0, '0', '2002-09-07');
INSERT INTO recettesjournalieres VALUES (84, '2002-09-06', '2002-09-20', '0', '0', '0', '78', '78', 1, '0', '2002-09-06');
INSERT INTO recettesjournalieres VALUES (85, '2002-09-06', '2002-09-20', '17.8', '75.92', '763.78', '979.5', '1837', 0, '0', '2002-09-06');
INSERT INTO recettesjournalieres VALUES (86, '2002-09-05', '2002-09-20', '0', '0', '0', '254', '254', 1, '0', '2002-09-05');
INSERT INTO recettesjournalieres VALUES (87, '2002-09-05', '2002-09-20', '22.5', '0', '160', '719.01', '901.51', 0, '0', '2002-09-05');
INSERT INTO recettesjournalieres VALUES (88, '2002-09-03', '2002-09-20', '0', '0', '91.5', '193.5', '285', 1, '0', '2002-09-03');
INSERT INTO recettesjournalieres VALUES (89, '2002-09-03', '2002-09-20', '11.5', '0', '21', '478.3', '510.8', 0, '0', '2002-09-03');
INSERT INTO recettesjournalieres VALUES (90, '2002-09-02', '2002-09-20', '0', '0', '0', '0', '0', 1, '0', '2002-09-02');
INSERT INTO recettesjournalieres VALUES (91, '2002-09-02', '2002-09-20', '12.51', '16.47', '180.5', '485.52', '695', 0, '0', '2002-09-02');
INSERT INTO recettesjournalieres VALUES (93, '2002-09-20', '2002-09-26', '48.5', '145.5', '219.5', '1907', '2320.5', 0, '0', '2002-09-20');
INSERT INTO recettesjournalieres VALUES (94, '2002-09-21', '2002-09-26', '13', '0', '0', '26', '39', 1, '0', '2002-09-21');
INSERT INTO recettesjournalieres VALUES (95, '2002-09-21', '2002-09-26', '-0.5', '0', '170.5', '1027.5', '1197.5', 0, '0', '2002-09-21');
INSERT INTO recettesjournalieres VALUES (96, '2002-09-22', '2002-09-26', '0', '0', '0', '61.5', '61.5', 1, '0', '2002-09-22');
INSERT INTO recettesjournalieres VALUES (97, '2002-09-22', '2002-09-26', '0', '7.5', '84.5', '690', '782', 0, '0', '2002-09-22');
INSERT INTO recettesjournalieres VALUES (98, '2002-09-23', '2002-09-26', '0', '0', '0', '0', '0', 1, '0', '2002-09-23');
INSERT INTO recettesjournalieres VALUES (99, '2002-09-23', '2002-09-26', '52.5', '11.5', '255', '281.5', '600.5', 0, '0', '2002-09-23');
INSERT INTO recettesjournalieres VALUES (100, '2002-09-24', '2002-09-26', '20.5', '0', '0', '74', '94.5', 1, '0', '2002-09-24');
INSERT INTO recettesjournalieres VALUES (101, '2002-09-24', '2002-09-26', '11.5', '36', '495.5', '556', '1099', 0, '0', '2002-09-24');
INSERT INTO recettesjournalieres VALUES (102, '2002-09-25', '2002-09-26', '0', '20.1', '78.5', '64.9', '158', 1, '0', '2002-09-25');
INSERT INTO recettesjournalieres VALUES (103, '2002-09-25', '2002-09-26', '10', '13', '318', '203.5', '544.5', 0, '0', '2002-09-25');
INSERT INTO recettesjournalieres VALUES (104, '2002-09-26', '2002-10-03', '0', '0', '0', '95.5', '95.5', 1, '0', '2002-09-26');
INSERT INTO recettesjournalieres VALUES (105, '2002-09-26', '2002-10-03', '22', '15', '293', '624.5', '954.5', 0, '0', '2002-09-26');
INSERT INTO recettesjournalieres VALUES (106, '2002-09-27', '2002-10-03', '0', '0', '33', '36.5', '69.5', 1, '0', '2002-09-27');
INSERT INTO recettesjournalieres VALUES (107, '2002-09-27', '2002-10-03', '67.5', '87.26', '257.16', '907.58', '1319.5', 0, '0', '2002-09-27');
INSERT INTO recettesjournalieres VALUES (108, '2002-10-01', '2003-01-12', '0', '0', '145.5', '157', '302.5', 1, '0', '2002-10-01');
INSERT INTO recettesjournalieres VALUES (109, '2002-10-01', '2003-01-12', '0', '0', '295.5', '705.8', '1001.3', 0, '0', '2002-10-01');
INSERT INTO recettesjournalieres VALUES (110, '2002-07-23', '2002-10-27', '0', '0', '0', '146', '146', 1, '0', '2002-10-27');
INSERT INTO recettesjournalieres VALUES (111, '2002-07-23', '2002-10-27', '19.5', '12', '326', '566', '923.5', 0, '0', '2002-10-27');
INSERT INTO recettesjournalieres VALUES (112, '2002-07-27', '2002-10-27', '0', '0', '0', '174', '174', 1, '0', '2002-10-27');
INSERT INTO recettesjournalieres VALUES (113, '2002-07-27', '2002-10-27', '0', '83.5', '241.5', '497', '822', 0, '0', '2002-10-27');
INSERT INTO recettesjournalieres VALUES (114, '2002-09-28', '2003-04-12', '0', '0', '0', '134', '134', 1, '0', '2003-04-12');
INSERT INTO recettesjournalieres VALUES (115, '2002-09-28', '2003-04-12', '20.43', '23.57', '509.5', '1327.5', '1881', 0, '0', '2003-04-12');
INSERT INTO recettesjournalieres VALUES (116, '2002-07-01', '2002-07-01', '0', '0', '0', '0', '0', 1, '0', '2003-04-12');
INSERT INTO recettesjournalieres VALUES (204, '2003-04-18', '2003-04-19', '0', '0', '0', '0', '0', 1, '0', '2003-04-19');
INSERT INTO recettesjournalieres VALUES (118, '2003-01-01', '2003-01-12', '0', '0', '0', '0', '0', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (119, '2003-01-01', '2003-01-12', '0', '0', '0', '0', '8.5', 0, '8.5', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (120, '2003-01-12', '2003-01-12', '0', '0', '0', '0', '0', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (121, '2003-01-12', '2003-01-12', '0', '0', '0', '0', '18', 0, '18', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (122, '2002-07-02', '2002-07-02', '0', '110', '0', '0', '110', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (123, '2002-07-02', '2002-07-02', '15.35', '28.35', '289', '759.05', '1091.75', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (124, '2002-07-03', '2003-01-12', '0', '0', '177', '167.5', '344.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (125, '2002-07-03', '2003-01-12', '4.3', '9.2', '269', '528', '810.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (126, '2002-07-04', '2003-01-12', '0', '0', '0', '0', '0', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (127, '2002-07-04', '2003-01-12', '0', '16', '278', '316', '610', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (128, '2002-07-05', '2003-01-12', '0', '0', '0', '40.5', '40.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (129, '2002-07-05', '2003-01-12', '22.51', '66', '449.5', '1412.99', '1951', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (130, '2002-07-06', '2003-01-12', '0', '0', '41', '78.6', '119.6', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (131, '2002-07-06', '2003-01-12', '20.75', '64.56', '195.67', '1218.52', '1499.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (132, '2002-07-07', '2003-01-12', '0', '0', '0', '33.5', '33.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (133, '2002-07-07', '2003-01-12', '-0.5', '23.5', '837', '640', '1500', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (134, '2002-07-08', '2003-01-12', '0', '0', '0', '45', '45', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (135, '2002-07-08', '2003-01-12', '86', '0', '288', '782.5', '1156.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (136, '2002-07-09', '2003-01-12', '0', '0', '78.5', '160.5', '239', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (137, '2002-07-09', '2003-01-12', '0', '47.5', '104', '588.5', '740', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (138, '2002-07-10', '2003-01-12', '0', '0', '40', '200.5', '240.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (139, '2002-07-10', '2003-01-12', '64', '45.47', '68.5', '720.53', '898.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (140, '2002-07-11', '2003-01-12', '27.55', '0', '0', '65.5', '93.05', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (141, '2002-07-11', '2003-01-12', '97.75', '13', '670', '627.25', '1408', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (142, '2002-07-12', '2003-01-12', '0', '0', '0', '91.5', '91.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (143, '2002-07-12', '2003-01-12', '16.38', '14.62', '323.5', '1031', '1385.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (144, '2002-07-13', '2003-01-12', '0', '0', '0', '29.5', '26.55', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (145, '2002-07-13', '2003-01-12', '111.3', '21.2', '407.25', '856.25', '1396', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (146, '2002-07-14', '2003-01-12', '0', '0', '9', '66.5', '75.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (147, '2002-07-14', '2003-01-12', '-3.5', '0', '208.5', '758', '963', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (148, '2002-07-15', '2003-01-12', '0', '0', '0', '74', '74', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (149, '2002-07-15', '2003-01-12', '30.1', '36.7', '457.5', '852.7', '1377', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (150, '2002-07-17', '2003-01-12', '0', '0', '20', '55', '75', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (151, '2002-07-17', '2003-01-12', '18.5', '0', '92', '551', '661.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (152, '2002-07-16', '2003-01-12', '0', '0', '0', '0', '0', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (153, '2002-07-16', '2003-01-12', '21.5', '39.34', '291', '232.16', '584', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (154, '2002-07-18', '2003-01-12', '0', '0', '0', '45.5', '45.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (155, '2002-07-18', '2003-01-12', '70.1', '48.73', '479.67', '1101', '1699.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (156, '2002-07-19', '2003-01-12', '0', '6.1', '0', '105.4', '111.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (157, '2002-07-19', '2003-01-12', '118.5', '0', '343.5', '907.5', '1369.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (158, '2002-07-20', '2003-01-12', '0', '0', '36', '23', '59', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (159, '2002-07-20', '2003-01-12', '4.3', '7', '655.2', '761.5', '1428', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (160, '2002-07-21', '2003-01-12', '0', '0', '28.5', '81.5', '110', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (161, '2002-07-21', '2003-01-12', '39', '50.5', '329', '239', '657.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (162, '2002-07-22', '2003-01-12', '0', '0', '0', '0', '0', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (163, '2002-07-22', '2003-01-12', '0', '0', '263', '377', '640', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (164, '2002-07-24', '2003-01-12', '0', '0', '170.5', '66', '236.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (165, '2002-07-24', '2003-01-12', '0', '0', '271', '722', '993', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (166, '2002-07-25', '2003-01-12', '23', '0', '0', '81.5', '98.45', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (167, '2002-07-25', '2003-01-12', '27', '0', '260', '646.5', '933.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (168, '2002-09-04', '2003-03-09', '0', '0', '90', '129', '219', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (169, '2002-09-04', '2003-03-09', '59.5', '13.5', '127.75', '361.5', '600.25', 0, '38', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (170, '2002-07-26', '2003-01-12', '0', '0', '0', '57.5', '57.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (171, '2002-07-26', '2003-01-12', '269.5', '7', '276.5', '728.5', '1281.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (172, '2002-09-29', '2003-01-12', '0', '0', '0', '45.5', '45.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (173, '2002-09-29', '2003-01-12', '25', '14.08', '1008', '720.42', '1767.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (174, '2002-09-30', '2003-01-12', '0', '0', '17.3', '134.3', '151.6', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (175, '2002-09-30', '2003-01-12', '34', '0', '0', '545.5', '579.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (176, '2002-10-02', '2003-01-12', '8', '0', '84', '218.74', '310.74', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (177, '2002-10-02', '2003-01-12', '10', '13', '139', '319', '481', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (178, '2002-10-03', '2003-01-12', '0', '0', '80.5', '156', '236.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (179, '2002-10-03', '2003-01-12', '34.83', '7.17', '82', '373.5', '497.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (180, '2002-10-04', '2003-01-12', '0', '0', '0', '66.5', '66.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (181, '2002-10-04', '2003-01-12', '17.8', '122', '379.2', '687.25', '1206.25', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (182, '2002-10-05', '2003-01-12', '0', '6.86', '0', '102.64', '109.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (183, '2002-10-05', '2003-01-12', '21', '20.88', '770.52', '1028.6', '1841', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (184, '2002-10-06', '2003-01-12', '0', '0', '0', '263.5', '263.5', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (185, '2002-10-06', '2003-01-12', '13.1', '24.4', '354', '849.5', '993.5', 0, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (186, '2002-10-07', '2003-05-05', '0', '20', '21', '0', '108.5', 1, '26.5', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (187, '2002-10-07', '2003-05-05', '0', '11.5', '122.5', '139', '305.76', 0, '32.76', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (188, '2002-12-02', '2003-01-12', '0', '0', '0', '0', '0', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (189, '2002-12-02', '2003-01-12', '0', '0', '0', '0', '59.5', 0, '59.5', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (190, '2002-12-24', '2003-01-14', '0', '0', '0', '0', '36.5', 1, '36.5', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (191, '2002-12-24', '2003-01-14', '0', '0', '0', '0', '18.5', 0, '18.5', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (192, '2002-12-28', '2003-01-12', '0', '0', '0', '0', '22', 1, '22', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (193, '2002-12-28', '2003-01-12', '0', '0', '0', '0', '45', 0, '45', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (194, '2002-12-31', '2003-01-12', '0', '0', '0', '0', '0', 1, '0', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (195, '2002-12-31', '2003-01-12', '6', '0', '0', '0', '33', 0, '27', '2003-01-12');
INSERT INTO recettesjournalieres VALUES (196, '2003-01-13', '2003-01-13', '0', '0', '0', '0', '0', 1, '0', '2003-01-13');
INSERT INTO recettesjournalieres VALUES (197, '2003-01-13', '2003-01-13', '0', '0', '0', '0', '4', 0, '4', '2003-01-13');
INSERT INTO recettesjournalieres VALUES (198, '2003-01-18', '2003-01-18', '0', '0', '0', '0', '0', 1, '0', '2003-01-20');
INSERT INTO recettesjournalieres VALUES (199, '2003-01-18', '2003-01-18', '14', '0', '0', '13', '27', 0, '0', '2003-01-20');
INSERT INTO recettesjournalieres VALUES (200, '2003-03-16', '2003-03-16', '57', '0', '78', '0', '135', 1, '0', '2003-03-16');
INSERT INTO recettesjournalieres VALUES (201, '2003-03-16', '2003-03-16', '0', '0', '0', '0', '0', 0, '0', '2003-03-16');
INSERT INTO recettesjournalieres VALUES (202, '2003-03-25', NULL, '0', '0', '0', '0', '0', 1, '0', '2003-03-25');
INSERT INTO recettesjournalieres VALUES (203, '2003-03-25', NULL, '0', '0', '0', '0', '0', 0, '0', '2003-03-25');
INSERT INTO recettesjournalieres VALUES (205, '2003-04-18', '2003-04-19', '7', '12', '0', '0', '19', 0, '0', '2003-04-19');
INSERT INTO recettesjournalieres VALUES (206, '2003-04-02', NULL, '0', '0', '0', '0', '0', 1, '0', '2003-04-20');
INSERT INTO recettesjournalieres VALUES (207, '2003-04-02', NULL, '0', '0', '0', '0', '0', 0, '0', '2003-04-20');
INSERT INTO recettesjournalieres VALUES (208, '2003-03-23', NULL, '0', '0', '0', '0', '0', 1, '0', '2003-04-20');
INSERT INTO recettesjournalieres VALUES (209, '2003-03-23', NULL, '0', '0', '0', '0', '0', 0, '0', '2003-04-20');
INSERT INTO recettesjournalieres VALUES (210, '2003-03-30', NULL, '0', '0', '0', '0', '0', 1, '0', '2003-04-20');
INSERT INTO recettesjournalieres VALUES (211, '2003-03-30', NULL, '0', '0', '0', '0', '0', 0, '0', '2003-04-20');
# --------------------------------------------------------

#
# Structure de la table `statsconsoproduits`
#

CREATE TABLE statsconsoproduits (
  idStats int(10) unsigned NOT NULL auto_increment,
  codeProduit varchar(5) NOT NULL default '',
  dateStats date NOT NULL default '0000-00-00',
  quantite float NOT NULL default '0',
  PRIMARY KEY  (idStats),
  UNIQUE KEY codeProduit (codeProduit),
  KEY codeProduit_2 (codeProduit),
  KEY dateStats (dateStats),
  KEY quantite (quantite)
) TYPE=MyISAM;

#
# Contenu de la table `statsconsoproduits`
#

INSERT INTO statsconsoproduits VALUES (1, '1/4', '2002-10-07', '348');
INSERT INTO statsconsoproduits VALUES (2, '4', '2003-05-29', '41');
INSERT INTO statsconsoproduits VALUES (3, '3', '2003-06-08', '28');
INSERT INTO statsconsoproduits VALUES (4, '2', '2003-06-08', '49');
INSERT INTO statsconsoproduits VALUES (5, '1', '2003-04-02', '13');
INSERT INTO statsconsoproduits VALUES (6, '110', '2002-10-07', '18');
INSERT INTO statsconsoproduits VALUES (7, '125', '2002-12-31', '37');
INSERT INTO statsconsoproduits VALUES (8, '129', '2002-12-28', '103');
INSERT INTO statsconsoproduits VALUES (9, '5', '2003-05-29', '62');
INSERT INTO statsconsoproduits VALUES (10, 'BC', '2002-10-04', '139');
INSERT INTO statsconsoproduits VALUES (11, '12', '2003-05-29', '134');
INSERT INTO statsconsoproduits VALUES (12, '33', '2003-03-09', '22');
INSERT INTO statsconsoproduits VALUES (13, '53', '2002-10-01', '16');
INSERT INTO statsconsoproduits VALUES (14, '72', '2003-03-09', '590');
INSERT INTO statsconsoproduits VALUES (15, 'A2', '2002-10-07', '502');
INSERT INTO statsconsoproduits VALUES (16, 'TV/', '2002-10-06', '29');
INSERT INTO statsconsoproduits VALUES (17, '107', '2002-09-28', '18');
INSERT INTO statsconsoproduits VALUES (18, '47', '2002-10-06', '79');
INSERT INTO statsconsoproduits VALUES (19, '69', '2002-10-06', '72');
INSERT INTO statsconsoproduits VALUES (20, '18', '2003-04-20', '147');
INSERT INTO statsconsoproduits VALUES (21, 'DS', '2002-10-06', '143');
INSERT INTO statsconsoproduits VALUES (22, 'AP', '2002-10-07', '357');
INSERT INTO statsconsoproduits VALUES (23, '9', '2003-04-02', '31');
INSERT INTO statsconsoproduits VALUES (24, '16', '2002-10-06', '27');
INSERT INTO statsconsoproduits VALUES (25, '124', '2002-10-06', '123');
INSERT INTO statsconsoproduits VALUES (26, 'T1', '2002-10-07', '16');
INSERT INTO statsconsoproduits VALUES (27, 'T9', '2002-10-01', '31');
INSERT INTO statsconsoproduits VALUES (28, '73', '2002-10-07', '294');
INSERT INTO statsconsoproduits VALUES (29, 'CFO', '2002-08-11', '1');
INSERT INTO statsconsoproduits VALUES (30, 'BF', '2002-10-06', '73');
INSERT INTO statsconsoproduits VALUES (31, 'CO', '2002-10-07', '147');
INSERT INTO statsconsoproduits VALUES (32, 'M2', '2003-01-18', '69');
INSERT INTO statsconsoproduits VALUES (33, 'M3', '2003-01-18', '54');
INSERT INTO statsconsoproduits VALUES (34, 'CF', '2003-04-02', '1083');
INSERT INTO statsconsoproduits VALUES (35, 'CP', '2002-10-06', '129');
INSERT INTO statsconsoproduits VALUES (36, 'A3', '2002-10-06', '164');
INSERT INTO statsconsoproduits VALUES (37, '101', '2002-10-05', '14.5');
INSERT INTO statsconsoproduits VALUES (38, '102', '2002-10-06', '64');
INSERT INTO statsconsoproduits VALUES (39, '120', '2002-10-06', '29');
INSERT INTO statsconsoproduits VALUES (40, '75', '2002-10-06', '222');
INSERT INTO statsconsoproduits VALUES (41, 'BX', '2002-10-06', '22');
INSERT INTO statsconsoproduits VALUES (42, 'O/', '2002-10-06', '161');
INSERT INTO statsconsoproduits VALUES (43, '11', '2003-05-29', '588');
INSERT INTO statsconsoproduits VALUES (44, 'GL2', '2002-10-05', '79');
INSERT INTO statsconsoproduits VALUES (45, 'GL1', '2002-10-06', '134');
INSERT INTO statsconsoproduits VALUES (46, 'ST', '2002-10-06', '47');
INSERT INTO statsconsoproduits VALUES (47, 'O', '2002-10-07', '151');
INSERT INTO statsconsoproduits VALUES (48, '98', '2002-10-06', '19');
INSERT INTO statsconsoproduits VALUES (49, 'T3', '2002-12-31', '38');
INSERT INTO statsconsoproduits VALUES (50, 'T10', '2002-10-05', '30');
INSERT INTO statsconsoproduits VALUES (51, '70', '2002-09-28', '19');
INSERT INTO statsconsoproduits VALUES (52, 'T7', '2002-10-03', '32');
INSERT INTO statsconsoproduits VALUES (53, '34', '2002-09-30', '8');
INSERT INTO statsconsoproduits VALUES (54, '36', '2002-10-05', '56');
INSERT INTO statsconsoproduits VALUES (55, '37', '2002-10-03', '15');
INSERT INTO statsconsoproduits VALUES (56, '57', '2002-10-06', '58');
INSERT INTO statsconsoproduits VALUES (57, '14', '2002-10-07', '58');
INSERT INTO statsconsoproduits VALUES (58, 'T6', '2002-10-05', '95');
INSERT INTO statsconsoproduits VALUES (59, 'M20', '2002-10-07', '328');
INSERT INTO statsconsoproduits VALUES (60, 'BJ', '2002-09-29', '20');
INSERT INTO statsconsoproduits VALUES (61, 'D', '2002-10-02', '19');
INSERT INTO statsconsoproduits VALUES (62, 'BY/', '2002-10-05', '20');
INSERT INTO statsconsoproduits VALUES (63, 'A', '2002-10-06', '157');
INSERT INTO statsconsoproduits VALUES (64, 'T15', '2002-10-06', '17');
INSERT INTO statsconsoproduits VALUES (65, '21', '2002-10-06', '47');
INSERT INTO statsconsoproduits VALUES (66, '62', '2002-10-06', '44');
INSERT INTO statsconsoproduits VALUES (67, 'B', '2002-10-06', '28');
INSERT INTO statsconsoproduits VALUES (68, 'T4', '2002-09-20', '9');
INSERT INTO statsconsoproduits VALUES (69, 'PC', '2002-10-01', '19.5');
INSERT INTO statsconsoproduits VALUES (70, 'TV', '2002-09-25', '28');
INSERT INTO statsconsoproduits VALUES (71, 'A1', '2003-01-18', '304');
INSERT INTO statsconsoproduits VALUES (72, '60', '2002-10-05', '13');
INSERT INTO statsconsoproduits VALUES (73, '74', '2002-10-06', '89');
INSERT INTO statsconsoproduits VALUES (74, 'T', '2002-10-07', '221');
INSERT INTO statsconsoproduits VALUES (75, 'M27', '2002-10-06', '138');
INSERT INTO statsconsoproduits VALUES (76, 'M14', '2002-10-06', '288');
INSERT INTO statsconsoproduits VALUES (77, 'SU', '2002-10-06', '22');
INSERT INTO statsconsoproduits VALUES (78, 'CP/', '2002-10-06', '106');
INSERT INTO statsconsoproduits VALUES (79, 'T22', '2002-10-07', '93');
INSERT INTO statsconsoproduits VALUES (80, '73B', '2002-09-21', '10');
INSERT INTO statsconsoproduits VALUES (81, 'BOI', '2002-09-20', '45');
INSERT INTO statsconsoproduits VALUES (82, '13', '2003-05-29', '96');
INSERT INTO statsconsoproduits VALUES (83, '96', '2002-10-06', '79');
INSERT INTO statsconsoproduits VALUES (84, '126', '2002-12-31', '39');
INSERT INTO statsconsoproduits VALUES (85, 'GL3', '2002-10-06', '88');
INSERT INTO statsconsoproduits VALUES (86, 'F', '2002-10-07', '181');
INSERT INTO statsconsoproduits VALUES (87, '76', '2002-09-29', '33');
INSERT INTO statsconsoproduits VALUES (88, 'BE', '2002-10-06', '41');
INSERT INTO statsconsoproduits VALUES (89, 'P', '2002-10-06', '65');
INSERT INTO statsconsoproduits VALUES (90, '71', '2002-10-06', '49');
INSERT INTO statsconsoproduits VALUES (91, 'T5', '2002-09-29', '10');
INSERT INTO statsconsoproduits VALUES (92, '22', '2002-09-12', '6');
INSERT INTO statsconsoproduits VALUES (93, '108', '2002-10-06', '24');
INSERT INTO statsconsoproduits VALUES (94, 'A0', '2002-10-06', '97');
INSERT INTO statsconsoproduits VALUES (95, '86', '2002-09-30', '14');
INSERT INTO statsconsoproduits VALUES (96, '114', '2002-09-27', '9');
INSERT INTO statsconsoproduits VALUES (97, 'BFM', '2002-10-02', '21');
INSERT INTO statsconsoproduits VALUES (98, 'GL', '2002-10-07', '341');
INSERT INTO statsconsoproduits VALUES (99, 'BX/', '2002-10-06', '23');
INSERT INTO statsconsoproduits VALUES (100, '95', '2002-10-07', '44');
INSERT INTO statsconsoproduits VALUES (101, '26', '2002-10-02', '19');
INSERT INTO statsconsoproduits VALUES (102, 'T16', '2002-10-06', '32');
INSERT INTO statsconsoproduits VALUES (103, '20', '2002-09-23', '11');
INSERT INTO statsconsoproduits VALUES (104, '35', '2002-10-03', '28');
INSERT INTO statsconsoproduits VALUES (105, '24', '2002-10-06', '73');
INSERT INTO statsconsoproduits VALUES (106, '109', '2002-09-15', '10');
INSERT INTO statsconsoproduits VALUES (107, 'T8', '2002-09-20', '16');
INSERT INTO statsconsoproduits VALUES (108, '113', '2002-10-06', '8');
INSERT INTO statsconsoproduits VALUES (109, 'SC/', '2002-10-06', '17');
INSERT INTO statsconsoproduits VALUES (110, '118', '2002-10-07', '42');
INSERT INTO statsconsoproduits VALUES (111, '15', '2003-03-09', '37');
INSERT INTO statsconsoproduits VALUES (112, '56', '2002-09-29', '12');
INSERT INTO statsconsoproduits VALUES (113, 'T17', '2002-10-04', '29');
INSERT INTO statsconsoproduits VALUES (114, '7', '2003-04-21', '19');
INSERT INTO statsconsoproduits VALUES (115, '32', '2002-10-07', '46');
INSERT INTO statsconsoproduits VALUES (116, '122', '2002-10-07', '19');
INSERT INTO statsconsoproduits VALUES (117, '29', '2002-09-29', '13');
INSERT INTO statsconsoproduits VALUES (118, 'GP', '2002-10-06', '66');
INSERT INTO statsconsoproduits VALUES (119, '78', '2002-12-31', '34');
INSERT INTO statsconsoproduits VALUES (120, '52', '2002-10-06', '45');
INSERT INTO statsconsoproduits VALUES (121, 'BJ/', '2002-10-05', '12');
INSERT INTO statsconsoproduits VALUES (122, '85', '2002-09-24', '18');
INSERT INTO statsconsoproduits VALUES (123, 'M1', '2002-10-05', '51');
INSERT INTO statsconsoproduits VALUES (124, '48', '2002-10-06', '7');
INSERT INTO statsconsoproduits VALUES (125, 'AC', '2002-09-29', '12');
INSERT INTO statsconsoproduits VALUES (126, '40', '2002-10-06', '53');
INSERT INTO statsconsoproduits VALUES (127, '111', '2002-09-02', '3');
INSERT INTO statsconsoproduits VALUES (128, 'A4', '2002-10-03', '39');
INSERT INTO statsconsoproduits VALUES (129, '6', '2003-05-29', '68');
INSERT INTO statsconsoproduits VALUES (130, '115', '2002-10-05', '28');
INSERT INTO statsconsoproduits VALUES (131, '88', '2002-09-25', '17');
INSERT INTO statsconsoproduits VALUES (132, 'T2', '2002-09-30', '16');
INSERT INTO statsconsoproduits VALUES (133, '10', '2002-09-26', '14');
INSERT INTO statsconsoproduits VALUES (134, '106', '2002-09-30', '14');
INSERT INTO statsconsoproduits VALUES (135, 'BH', '2002-12-31', '20');
INSERT INTO statsconsoproduits VALUES (136, '92', '2002-10-05', '19');
INSERT INTO statsconsoproduits VALUES (137, 'CR/', '2002-10-06', '36');
INSERT INTO statsconsoproduits VALUES (138, '49', '2002-09-28', '14');
INSERT INTO statsconsoproduits VALUES (139, '94', '2002-10-06', '26');
INSERT INTO statsconsoproduits VALUES (140, '39', '2002-10-06', '13');
INSERT INTO statsconsoproduits VALUES (141, 'CA', '2003-01-18', '27');
INSERT INTO statsconsoproduits VALUES (142, '46', '2002-10-06', '24');
INSERT INTO statsconsoproduits VALUES (143, 'E', '2002-10-06', '21');
INSERT INTO statsconsoproduits VALUES (144, 'CA/', '2002-10-06', '35');
INSERT INTO statsconsoproduits VALUES (145, 'T12', '2002-10-05', '8');
INSERT INTO statsconsoproduits VALUES (146, '87', '2002-09-29', '10');
INSERT INTO statsconsoproduits VALUES (147, '77', '2002-10-06', '18');
INSERT INTO statsconsoproduits VALUES (148, 'H', '2002-09-27', '11');
INSERT INTO statsconsoproduits VALUES (149, '28', '2002-10-05', '8');
INSERT INTO statsconsoproduits VALUES (150, '123', '2002-09-30', '30');
INSERT INTO statsconsoproduits VALUES (151, 'AS', '2002-10-06', '3');
INSERT INTO statsconsoproduits VALUES (152, 'T13', '2002-09-29', '24');
INSERT INTO statsconsoproduits VALUES (153, 'J', '2002-10-06', '158');
INSERT INTO statsconsoproduits VALUES (154, '67', '2002-09-11', '8');
INSERT INTO statsconsoproduits VALUES (155, '93', '2002-10-06', '41');
INSERT INTO statsconsoproduits VALUES (156, 'BY', '2002-10-03', '21');
INSERT INTO statsconsoproduits VALUES (157, 'C', '2002-09-23', '13');
INSERT INTO statsconsoproduits VALUES (158, '90', '2002-09-22', '14');
INSERT INTO statsconsoproduits VALUES (159, 'B2', '2002-10-06', '37');
INSERT INTO statsconsoproduits VALUES (160, 'CV', '2002-10-06', '13');
INSERT INTO statsconsoproduits VALUES (161, 'B1', '2002-10-06', '37');
INSERT INTO statsconsoproduits VALUES (162, 'DG1', '2002-09-25', '27');
INSERT INTO statsconsoproduits VALUES (163, '68', '2002-10-06', '42');
INSERT INTO statsconsoproduits VALUES (164, 'ST/', '2002-10-02', '20');
INSERT INTO statsconsoproduits VALUES (165, 'T14', '2002-10-02', '15');
INSERT INTO statsconsoproduits VALUES (166, '105', '2002-09-28', '16');
INSERT INTO statsconsoproduits VALUES (167, '66', '2002-10-02', '23');
INSERT INTO statsconsoproduits VALUES (168, '25', '2002-10-07', '8');
INSERT INTO statsconsoproduits VALUES (169, '119', '2002-09-26', '9');
INSERT INTO statsconsoproduits VALUES (170, '31', '2002-10-06', '16');
INSERT INTO statsconsoproduits VALUES (171, '65', '2002-09-29', '19');
INSERT INTO statsconsoproduits VALUES (172, 'IR', '2002-09-29', '27');
INSERT INTO statsconsoproduits VALUES (173, '104', '2002-10-06', '33');
INSERT INTO statsconsoproduits VALUES (174, '27', '2002-09-29', '21');
INSERT INTO statsconsoproduits VALUES (175, '23', '2003-06-08', '20');
INSERT INTO statsconsoproduits VALUES (176, '19', '2003-04-20', '23');
INSERT INTO statsconsoproduits VALUES (177, 'BO/', '2002-10-06', '10');
INSERT INTO statsconsoproduits VALUES (178, '59', '2002-08-11', '1');
INSERT INTO statsconsoproduits VALUES (179, '100', '2002-10-06', '18');
INSERT INTO statsconsoproduits VALUES (180, '121', '2002-10-06', '27');
INSERT INTO statsconsoproduits VALUES (181, 'B3', '2002-09-20', '3');
INSERT INTO statsconsoproduits VALUES (182, 'S', '2002-10-01', '13');
INSERT INTO statsconsoproduits VALUES (183, '99', '2002-10-06', '11');
INSERT INTO statsconsoproduits VALUES (184, '38', '2002-09-28', '23');
INSERT INTO statsconsoproduits VALUES (185, 'MU', '2002-08-11', '1');
INSERT INTO statsconsoproduits VALUES (186, '44', '2002-09-29', '8');
INSERT INTO statsconsoproduits VALUES (187, 'CR', '2002-10-06', '31');
INSERT INTO statsconsoproduits VALUES (188, 'SC', '2002-10-06', '14');
INSERT INTO statsconsoproduits VALUES (189, 'DGO', '2002-10-06', '51');
INSERT INTO statsconsoproduits VALUES (190, 'T18', '2002-08-23', '2');
INSERT INTO statsconsoproduits VALUES (191, '103', '2002-08-22', '6');
INSERT INTO statsconsoproduits VALUES (192, 'DG2', '2002-08-29', '7');
INSERT INTO statsconsoproduits VALUES (193, 'T11', '2002-10-05', '12');
INSERT INTO statsconsoproduits VALUES (194, 'G', '2002-09-20', '13');
INSERT INTO statsconsoproduits VALUES (195, 'GC', '2002-09-29', '10');
INSERT INTO statsconsoproduits VALUES (196, 'COU', '2002-09-30', '28');
INSERT INTO statsconsoproduits VALUES (197, 'AG', '2002-10-01', '5');
INSERT INTO statsconsoproduits VALUES (198, 'T20', '2002-08-25', '3');
INSERT INTO statsconsoproduits VALUES (199, 'T23', '2002-10-07', '29');
INSERT INTO statsconsoproduits VALUES (200, '127', '2002-12-31', '19');
INSERT INTO statsconsoproduits VALUES (201, '55', '2003-03-09', '11');
INSERT INTO statsconsoproduits VALUES (202, 'MO', '2002-09-20', '8');
INSERT INTO statsconsoproduits VALUES (203, '132', '2002-10-06', '19');
INSERT INTO statsconsoproduits VALUES (204, '128', '2002-09-30', '15');
INSERT INTO statsconsoproduits VALUES (205, 'MU/', '2002-10-06', '5');
INSERT INTO statsconsoproduits VALUES (206, 'T19', '2002-09-21', '7');
INSERT INTO statsconsoproduits VALUES (207, 'RI/', '2002-10-05', '7');
INSERT INTO statsconsoproduits VALUES (208, '97', '2002-09-26', '6');
INSERT INTO statsconsoproduits VALUES (209, '89', '2003-03-09', '7');
INSERT INTO statsconsoproduits VALUES (210, '51', '2002-09-29', '3');
INSERT INTO statsconsoproduits VALUES (211, 'RI', '2002-08-25', '4');
INSERT INTO statsconsoproduits VALUES (212, '117', '2002-09-25', '5');
INSERT INTO statsconsoproduits VALUES (213, '58', '2002-10-06', '10');
INSERT INTO statsconsoproduits VALUES (214, '41', '2002-09-27', '7');
INSERT INTO statsconsoproduits VALUES (215, '42', '2002-10-06', '7');
INSERT INTO statsconsoproduits VALUES (216, '50', '2002-09-22', '3');
INSERT INTO statsconsoproduits VALUES (217, '8', '2002-10-06', '23');
INSERT INTO statsconsoproduits VALUES (218, 'BO', '2002-08-25', '3');
INSERT INTO statsconsoproduits VALUES (219, '131', '2002-10-06', '13');
INSERT INTO statsconsoproduits VALUES (220, '91', '2002-10-05', '11');
INSERT INTO statsconsoproduits VALUES (221, 'T21', '2002-09-08', '5');
INSERT INTO statsconsoproduits VALUES (222, '45', '2003-03-09', '16');
INSERT INTO statsconsoproduits VALUES (223, '112', '2002-09-29', '5');
INSERT INTO statsconsoproduits VALUES (224, 'BT', '2002-08-11', '5');
INSERT INTO statsconsoproduits VALUES (225, '54', '2002-10-06', '10');
INSERT INTO statsconsoproduits VALUES (226, '17', '2002-10-07', '12');
INSERT INTO statsconsoproduits VALUES (227, '64', '2002-10-06', '7');
INSERT INTO statsconsoproduits VALUES (228, '116', '2002-10-06', '2');
INSERT INTO statsconsoproduits VALUES (229, '43', '2003-03-09', '4');
INSERT INTO statsconsoproduits VALUES (230, 'MO/', '2002-08-28', '2');
INSERT INTO statsconsoproduits VALUES (231, 'FF', '2002-08-21', '1');
INSERT INTO statsconsoproduits VALUES (232, '130', '2002-10-04', '4');
INSERT INTO statsconsoproduits VALUES (233, '30', '2002-08-27', '1');
INSERT INTO statsconsoproduits VALUES (234, 'DG', '2002-09-25', '3');
INSERT INTO statsconsoproduits VALUES (235, 'GLF', '2002-09-01', '3');
INSERT INTO statsconsoproduits VALUES (236, '61', '2002-10-06', '5');
INSERT INTO statsconsoproduits VALUES (237, 'GR', '2002-09-17', '1');
INSERT INTO statsconsoproduits VALUES (238, 'ABC', '2002-12-28', '2');
# --------------------------------------------------------

#
# Structure de la table `tablediner`
#

CREATE TABLE tablediner (
  idTable int(10) unsigned NOT NULL auto_increment,
  idSalle int(10) unsigned NOT NULL default '0',
  numeroTable varchar(5) NOT NULL default '',
  nombrePersonnes int(3) unsigned NOT NULL default '0',
  sommeQuantites float NOT NULL default '0',
  sommeMontant float NOT NULL default '0',
  tauxReduction float NOT NULL default '0',
  sommeDue float NOT NULL default '0',
  dateEntreeOccupation datetime default NULL,
  dateImpression datetime default NULL,
  dateEncaissement datetime default NULL,
  tauxReductionChanged int(1) unsigned NOT NULL default '0',
  PRIMARY KEY  (idTable),
  KEY numeroTable (numeroTable),
  KEY nombrePersonnes (nombrePersonnes),
  KEY sommeQuantites (sommeQuantites),
  KEY sommeDue (sommeDue),
  KEY dateEntreeOccupation (dateEntreeOccupation),
  KEY dateImpression (dateImpression)
) TYPE=MyISAM;

#
# Contenu de la table `tablediner`
#

INSERT INTO tablediner VALUES (3194, 0, 'E', 0, '1', '7', '0', '7', '2003-03-09 11:43:48', '2003-03-09 11:43:53', '2003-03-09 12:31:36', 0);
INSERT INTO tablediner VALUES (3192, 0, '2', 2, '4', '28.5', '0', '28.5', '2003-03-09 10:08:20', '2003-03-09 10:09:01', '2003-03-09 11:12:23', 0);
INSERT INTO tablediner VALUES (3254, 0, '4', 2, '1', '4', '0', '4', '2003-05-18 11:10:37', '2003-05-25 19:14:52', '2003-05-29 21:58:24', 0);
INSERT INTO tablediner VALUES (3193, 0, 'E', 0, '1', '7', '0', '7', '2003-03-09 11:12:48', '2003-03-09 11:14:02', '2003-03-09 11:14:17', 0);
INSERT INTO tablediner VALUES (3233, 0, '1', 2, '4', '20', '0', '20', '2003-04-06 13:50:01', '0103-05-01 10:04:52', '2003-05-29 21:45:12', 1);
INSERT INTO tablediner VALUES (3195, 0, 'E2', 0, '2', '9', '0', '9', '2003-03-09 12:32:30', '2003-03-09 12:32:34', '2003-03-09 12:32:48', 0);
INSERT INTO tablediner VALUES (3196, 0, 'E3', 0, '1', '4', '0', '4', '2003-03-09 12:33:16', '2003-03-09 12:33:21', '2003-03-09 12:33:28', 0);
INSERT INTO tablediner VALUES (3197, 0, 'E2', 0, '1', '7', '0', '7', '2003-03-09 12:38:53', '2003-03-09 13:28:41', '2003-03-09 13:31:24', 0);
INSERT INTO tablediner VALUES (3198, 0, 'E', 0, '1', '7', '0', '7', '2003-03-09 13:13:55', '2003-03-09 13:22:36', '2003-03-09 13:29:00', 0);
INSERT INTO tablediner VALUES (3199, 0, '3', 2, '2', '11', '0', '11', '2003-03-09 13:29:18', '2003-03-09 13:30:08', '2003-03-09 13:30:20', 0);
INSERT INTO tablediner VALUES (3200, 0, 'E', 0, '1', '7', '0', '7', '2003-03-09 13:31:37', '2003-03-09 13:31:38', '2003-03-09 13:32:39', 0);
INSERT INTO tablediner VALUES (3201, 0, 'E', 0, '1', '7', '0', '7', '2003-03-09 13:33:58', '2003-03-09 13:33:59', '2003-03-09 13:34:09', 0);
INSERT INTO tablediner VALUES (3202, 0, 'E', 0, '1', '7', '0', '7', '2003-03-09 13:35:17', '2003-03-09 13:35:23', '2003-03-09 13:35:31', 0);
INSERT INTO tablediner VALUES (3203, 0, 'E', 0, '1', '7.5', '0', '7.5', '2003-03-09 13:35:58', '2003-03-09 13:36:00', '2003-03-09 13:36:11', 0);
INSERT INTO tablediner VALUES (3204, 0, 'E', 0, '1', '4.5', '0', '4.5', '2003-03-09 13:36:24', '2003-03-09 13:36:26', '2003-03-09 13:36:39', 0);
INSERT INTO tablediner VALUES (3205, 0, '1', 2, '2', '8.5', '0', '8.5', '2003-03-09 13:38:15', '2003-03-09 13:38:27', '2003-03-09 13:38:44', 0);
INSERT INTO tablediner VALUES (3206, 0, '5', 4, '9', '44.5', '0', '44.5', '2003-03-09 13:40:01', '2003-03-09 13:40:23', '2003-03-09 15:34:36', 0);
INSERT INTO tablediner VALUES (3207, 0, '1', 2, '1', '4', '0', '4', '2003-03-09 13:44:38', '2003-03-09 13:44:40', '2003-03-09 13:44:54', 0);
INSERT INTO tablediner VALUES (3208, 0, '8', 2, '5', '23.5', '0', '23.5', '2003-03-09 13:45:13', '2003-03-09 15:34:55', '2003-03-09 15:36:14', 0);
INSERT INTO tablediner VALUES (3209, 0, '13', 2, '2', '8.5', '0', '8.5', '2003-03-09 15:36:34', '2003-03-09 15:36:37', '2003-03-09 15:37:13', 0);
INSERT INTO tablediner VALUES (3210, 0, '19', 2, '4', '32', '0', '32', '2003-03-09 15:40:02', '2003-03-09 15:40:10', '2003-03-09 15:40:41', 0);
INSERT INTO tablediner VALUES (3211, 0, '21', 2, '3', '21', '0', '21', '2003-03-09 15:41:15', '2003-03-09 15:41:22', '2003-03-09 15:42:16', 0);
INSERT INTO tablediner VALUES (3213, 0, 'E', 0, '1', '107.5', '10', '97', '2003-03-23 21:30:09', '2003-03-23 21:39:16', '2003-03-23 21:41:18', 0);
INSERT INTO tablediner VALUES (3214, 0, 'E', 0, '1', '119.5', '10', '107.5', '2003-03-23 21:41:59', '2003-03-23 21:42:01', '2003-03-23 21:42:22', 0);
INSERT INTO tablediner VALUES (3216, 0, '139', 1, '1', '7', '15', '6', '2003-03-23 22:14:55', '2003-03-24 21:03:32', '2003-03-24 21:09:12', 0);
INSERT INTO tablediner VALUES (3218, 0, 'E', 0, '1', '119', '0', '119', '2003-03-25 21:26:18', '2003-03-25 21:27:26', '2003-03-25 21:27:38', 1);
INSERT INTO tablediner VALUES (3217, 0, 'E', 0, '5', '35', '5', '33.5', '2003-03-25 21:24:01', '2003-03-25 21:24:25', '2003-03-25 21:24:56', 1);
INSERT INTO tablediner VALUES (3219, 0, 'E1', 0, '1', '105', '0', '105', '2003-03-25 21:27:59', '2003-03-25 21:28:01', '2003-03-25 21:28:41', 1);
INSERT INTO tablediner VALUES (3223, 0, '2', 2, '2', '14', '0', '14', '2003-04-02 20:42:38', '2003-04-02 20:58:21', '2003-04-02 21:30:08', 0);
INSERT INTO tablediner VALUES (3221, 0, '10', 2, '6', '32', '2', '31.5', '2003-03-30 18:21:59', '2003-04-02 21:24:42', '2003-04-02 21:29:48', 1);
INSERT INTO tablediner VALUES (3222, 0, '1', 2, '4', '16', '0', '16', '2003-04-02 20:42:30', '2003-04-02 21:23:29', '2003-04-02 21:29:43', 0);
INSERT INTO tablediner VALUES (3224, 0, '3', 2, '1', '7', '0', '7', '2003-04-02 20:42:46', '2003-04-02 20:58:25', '2003-04-02 21:30:14', 0);
INSERT INTO tablediner VALUES (3225, 0, '4', 2, '1', '7', '0', '7', '2003-04-02 20:42:54', '2003-04-02 20:42:56', '2003-04-02 21:30:19', 0);
INSERT INTO tablediner VALUES (3226, 0, '5', 2, '2', '14', '0', '14', '2003-04-02 20:43:16', '2003-04-02 20:43:19', '2003-04-02 21:30:24', 0);
INSERT INTO tablediner VALUES (3227, 0, '6', 2, '1', '4.5', '0', '4.5', '2003-04-02 20:43:29', '2003-04-02 20:43:30', '2003-04-02 21:30:30', 0);
INSERT INTO tablediner VALUES (3228, 0, '7', 2, '1', '7', '0', '7', '2003-04-02 20:43:35', '2003-04-02 20:43:37', '2003-04-02 21:30:39', 0);
INSERT INTO tablediner VALUES (3229, 0, '8', 2, '1', '7', '0', '7', '2003-04-02 20:43:42', '2003-04-02 20:43:44', '2003-04-02 21:30:44', 0);
INSERT INTO tablediner VALUES (3230, 0, '11', 2, '1', '4', '0', '4', '2003-04-02 20:43:48', '2003-04-02 20:43:49', '2003-04-02 21:29:53', 0);
INSERT INTO tablediner VALUES (3231, 0, '12', 2, '1', '4', '0', '4', '2003-04-02 20:43:54', '2003-04-02 20:43:55', '2003-04-02 21:29:58', 0);
INSERT INTO tablediner VALUES (3232, 0, '13', 2, '1', '7', '0', '7', '2003-04-02 21:19:46', '2003-04-02 21:21:44', '2003-04-02 21:30:03', 0);
INSERT INTO tablediner VALUES (3251, 0, '2', 1, '2', '11', '0', '11', '2003-05-09 20:21:52', '2003-05-29 21:28:51', '2003-05-29 21:30:01', 0);
INSERT INTO tablediner VALUES (3250, 0, 'E', 0, '1', '107.5', '0', '107.5', '2003-03-19 17:04:44', '2003-05-06 17:06:21', '2003-05-06 17:07:30', 1);
INSERT INTO tablediner VALUES (3248, 0, '3', 3, '5', '22.5', '0', '22.5', '2003-04-22 00:01:54', '2003-05-04 09:51:08', '2003-05-04 23:26:24', 0);
INSERT INTO tablediner VALUES (3243, 0, 'E', 0, '2', '9', '0', '9', '2003-03-30 01:25:34', '2003-04-20 01:25:38', '2003-04-20 01:26:01', 0);
INSERT INTO tablediner VALUES (3244, 0, 'E1', 0, '2', '13', '0', '13', '2003-03-30 01:26:15', '2003-04-20 01:26:18', '2003-04-20 01:26:28', 0);
INSERT INTO tablediner VALUES (3253, 0, '6', 1, '3', '15.5', '0', '15.5', '2003-05-10 10:10:29', '2003-05-10 19:58:47', '2003-05-29 21:58:51', 0);
INSERT INTO tablediner VALUES (3245, 0, '11', 2, '2', '11.5', '0', '11.5', '2003-03-30 01:26:54', '2003-04-20 01:26:58', '2003-04-20 01:27:07', 0);
INSERT INTO tablediner VALUES (3246, 0, '123', 1, '21', '99.5', '0', '99.5', '2003-04-20 21:57:36', '2003-04-20 22:48:25', '2003-04-21 00:22:19', 0);
INSERT INTO tablediner VALUES (3252, 0, '3', 1, '5', '27', '0', '27', '2003-05-10 10:08:02', '2003-05-29 21:36:16', '2003-05-29 21:53:25', 0);
INSERT INTO tablediner VALUES (3249, 0, '5', 2, '1', '4.5', '0', '4.5', '2003-04-22 00:27:20', '2003-05-25 19:16:16', '2003-05-29 21:58:41', 0);
INSERT INTO tablediner VALUES (3255, 0, '1', 2, '1', '7', '0', '7', '2003-05-29 18:13:51', '2003-05-29 21:59:21', '2003-05-29 21:59:36', 0);
INSERT INTO tablediner VALUES (3256, 0, '1', 2, '1', '7', '0', '7', '2003-05-29 22:01:01', '2003-05-29 22:01:18', '2003-05-29 22:01:33', 0);
INSERT INTO tablediner VALUES (3257, 0, '1', 2, '1', '7', '0', '7', '2003-05-29 22:01:01', '2003-05-29 22:03:34', '2003-05-29 22:03:46', 0);
INSERT INTO tablediner VALUES (3258, 0, '1', 2, '1', '7', '0', '7', '2003-05-29 22:04:07', '2003-05-29 22:08:10', '2003-05-29 22:08:54', 0);
INSERT INTO tablediner VALUES (3259, 0, '1', 4, '2', '14', '0', '14', '2003-05-29 22:09:26', '2003-06-01 17:37:08', '2003-06-01 17:37:29', 0);
INSERT INTO tablediner VALUES (3260, 0, '1', 2, '2', '11', '0', '11', '2003-06-01 17:05:24', '2003-06-01 17:45:04', '2003-06-04 00:08:32', 0);
INSERT INTO tablediner VALUES (3261, 0, '1', 9, '5', '35', '0', '35', '2003-06-06 22:27:27', '2003-06-06 22:42:14', NULL, 0);
INSERT INTO tablediner VALUES (3270, 0, '2', 3, '1', '7', '0', '7', '2003-06-08 01:31:21', '2003-06-08 01:31:22', NULL, 0);
INSERT INTO tablediner VALUES (3263, 0, '3', 3, '1', '7', '0', '7', '2003-06-07 09:25:11', '2003-06-08 01:04:20', '2003-06-08 01:05:00', 0);
INSERT INTO tablediner VALUES (3269, 0, '4', 2, '1', '9', '0', '9', '2003-06-08 01:23:36', '2003-06-08 01:27:17', '2003-06-08 01:27:30', 0);
INSERT INTO tablediner VALUES (3267, 0, '7', 3, '2', '11', '0', '11', '2003-06-07 01:03:29', '2003-06-08 01:16:33', '2003-06-08 01:18:03', 0);
INSERT INTO tablediner VALUES (3271, 0, '3', 2, '1', '7', '0', '7', '2003-06-08 01:32:58', NULL, NULL, 0);
INSERT INTO tablediner VALUES (3268, 0, '3', 1, '1', '7', '0', '7', '2003-06-08 01:17:18', '2003-06-08 01:19:59', '2003-06-08 01:26:57', 0);
# --------------------------------------------------------

#
# Structure de la table `typeproduit`
#

CREATE TABLE typeproduit (
  idType int(2) unsigned NOT NULL auto_increment,
  libelle varchar(50) NOT NULL default '',
  PRIMARY KEY  (idType),
  UNIQUE KEY libelle (libelle)
) TYPE=MyISAM;

#
# Contenu de la table `typeproduit`
#

INSERT INTO typeproduit VALUES (10, 'CAFE THE INFUSION');
INSERT INTO typeproduit VALUES (9, 'VIN');
INSERT INTO typeproduit VALUES (6, 'RIZ');
INSERT INTO typeproduit VALUES (5, 'VIANDE');
INSERT INTO typeproduit VALUES (8, 'ALCOOL');
INSERT INTO typeproduit VALUES (3, 'CRUSTACES');
INSERT INTO typeproduit VALUES (2, 'VAPEURS');
INSERT INTO typeproduit VALUES (7, 'VOLAILLE');
INSERT INTO typeproduit VALUES (11, 'EAU JUS SODA');
INSERT INTO typeproduit VALUES (12, 'BIERE');
INSERT INTO typeproduit VALUES (13, 'FRUITS ET LEGUME');
INSERT INTO typeproduit VALUES (14, 'GLACE');
# --------------------------------------------------------

#
# Structure de la table `user`
#

CREATE TABLE user (
  id int(10) NOT NULL default '0',
  idauth int(10) NOT NULL default '0',
  idrole int(10) NOT NULL default '0',
  nom varchar(20) NOT NULL default '',
  prenom1 varchar(20) NOT NULL default '',
  prenom2 varchar(20) default NULL,
  datenaissance date NOT NULL default '0000-00-00',
  sexe tinyint(1) NOT NULL default '0',
  photo blob,
  PRIMARY KEY  (id),
  UNIQUE KEY idauth (idauth),
  KEY nom (nom,prenom1,datenaissance)
) TYPE=MyISAM;

#
# Contenu de la table `user`
#

INSERT INTO user VALUES (1, 1, 1, 'MA', 'Edouard', NULL, '1968-08-15', 0, NULL);
INSERT INTO user VALUES (2, 2, 2, 'MA', 'Mathieu', NULL, '1970-08-15', 0, NULL);
INSERT INTO user VALUES (3, 3, 3, 'MA', 'Maxime', 'Max', '2003-08-15', 1, NULL);
# --------------------------------------------------------

#
# Structure de la table `userauth`
#

CREATE TABLE userauth (
  id int(10) NOT NULL default '0',
  login varchar(20) NOT NULL default '',
  password varchar(20) NOT NULL default '',
  levelpass1 varchar(20) default NULL,
  levelpass2 varchar(20) default NULL,
  levelpass3 varchar(20) default NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY login (login)
) TYPE=MyISAM;

#
# Contenu de la table `userauth`
#

INSERT INTO userauth VALUES (1, 'kimsan', 'kimsan', 'kimsan', 'kimsan', 'kimsan');
INSERT INTO userauth VALUES (2, 'mathieu', 'mathieu', 'mathieu', NULL, NULL);
INSERT INTO userauth VALUES (3, 'maxime', 'maxime', NULL, NULL, NULL);
# --------------------------------------------------------

#
# Structure de la table `userrole`
#

CREATE TABLE userrole (
  id int(10) NOT NULL default '0',
  role varchar(20) NOT NULL default '',
  PRIMARY KEY  (id),
  UNIQUE KEY role (role)
) TYPE=MyISAM;

#
# Contenu de la table `userrole`
#

INSERT INTO userrole VALUES (1, 'administrateur');
INSERT INTO userrole VALUES (2, 'utilisateur');
INSERT INTO userrole VALUES (3, 'client');
INSERT INTO userrole VALUES (4, 'invite');

