CREATE OR REPLACE VIEW REGIME_UTILISATEUR AS
	SELECT U.*, R.IDREGIME, R.DATEDEBUT, R.DATEFIN, R.POIDSOBJECTIF
	FROM REGIME R
    JOIN UTILISATEUR U ON R.IDUTILISATEUR = U.IDUTILISATEUR;
CREATE OR REPLACE VIEW REGIME_ALIMENTATION AS
	SELECT R.*, A.IDALIMENTATION, REPAS, BOISSON, PERIODE, DATEALIMENTATION
	FROM ALIMENTATION A
	JOIN REGIME_UTILISATEUR R ON A.IDREGIME = R.IDREGIME;
CREATE OR REPLACE VIEW REGIME_SPORT AS
	SELECT R.*, RS.IDSPORT, DATESPORT, RYTHMESPORT, SPORT, ACTIVITES
	FROM SPORT_REGIME RS
	JOIN REGIME_UTILISATEUR R ON RS.IDREGIME = R.IDREGIME
	JOIN SPORT S ON RS.IDSPORT = S.IDSPORT;
CREATE OR REPLACE VIEW REGIME_POIDS AS
	SELECT R.*, IDPOIDS, POIDS, DATEPOIDS
	FROM POIDS P
	JOIN REGIME_UTILISATEUR R ON P.IDREGIME = R.IDREGIME;
CREATE OR REPLACE VIEW CONSEIL_SPORT AS
	SELECT CS.*, SPORT, ACTIVITES
	FROM CONSEILSPORT CS
	JOIN SPORT S ON CS.IDSPORT = S.IDSPORT;
  
INSERT INTO utilisateur (idutilisateur, nom, prenom, datenaissance, sexe, identifiant, password, adresse, email) VALUES (1, 'Henintsoa', 'Adri', '1996-08-20', 'FEMININ', 'adri', 'adri', 'Lot VB 83 Ambatoroka', 'adri@hotmail.com');

INSERT INTO regime (idregime, idutilisateur, datedebut, datefin, poidsobjectif) VALUES (1, 1, '2017-03-01', '2017-06-01', 50);

INSERT INTO alimentation (idalimentation, idregime, repas, boisson, periode, datealimentation) VALUES (1, 1, 'Yaourt - Pain beurré', 'Jus de fruit', 1, '2017-03-01');
INSERT INTO alimentation (idalimentation, idregime, repas, boisson, periode, datealimentation) VALUES (2, 1, 'Riz - Porc aux légume', 'Jus de fruit', 2, '2017-03-01');
INSERT INTO alimentation (idalimentation, idregime, repas, boisson, periode, datealimentation) VALUES (3, 1, 'Pomme', 'Eau plate', 3, '2017-03-01');
INSERT INTO alimentation (idalimentation, idregime, repas, boisson, periode, datealimentation) VALUES (6, 1, 'Riz - Kitoza sauté aux légumes', 'Jus de fruit', 2, '2017-03-02');
INSERT INTO alimentation (idalimentation, idregime, repas, boisson, periode, datealimentation) VALUES (7, 1, 'Riz - Saucisse', 'Jus de fruit', 3, '2017-03-02');
INSERT INTO alimentation (idalimentation, idregime, repas, boisson, periode, datealimentation) VALUES (4, 1, 'Yaourt - Pain confiture', 'Jus de fruit', 1, '2017-03-02');

INSERT INTO conseilalimentation (idconseilalimentation, nomconseilalimentation, matin, midi, soir) VALUES (1, 'Minceur Ventre Plat', 'Thé ou café (nature) - Porridge de flocons d''avoine (40 g de flocons cuits dans du lait d''amande) - 1 petite pomme cuite', 'Salade de pomme de terre tièdes, aneth - Brochette de poulet, courgettes poêlées dans huile d''olive, romarin - Yaourt au bifidus avec quelques cranberries - Café sans sucre', 'Endive aux noix et bleu - Magret de canard, riz basmati au curcuma - Banane poêlée au cacao - Infusion de verveine');

INSERT INTO sport (idsport, sport, activites) VALUES (1, 'Fitness', 'Corde à sauter');
INSERT INTO sport (idsport, sport, activites) VALUES (2, 'Fitness', 'Squat');
INSERT INTO sport (idsport, sport, activites) VALUES (3, 'Cardio', 'Velo Elliptique');
INSERT INTO sport (idsport, sport, activites) VALUES (4, 'Cardio', 'Tapis de gym');
INSERT INTO sport (idsport, sport, activites) VALUES (5, 'Natation', '100m');
INSERT INTO sport (idsport, sport, activites) VALUES (6, 'Natation', '200mètre');
INSERT INTO sport (idsport, sport, activites) VALUES (7, 'Running', 'Course à pied');
INSERT INTO sport (idsport, sport, activites) VALUES (8, 'Running', 'Marche à pied');
INSERT INTO sport (idsport, sport, activites) VALUES (9, 'Musculation', 'Altères');
INSERT INTO sport (idsport, sport, activites) VALUES (10, 'Musculation', 'Abdominaux');
INSERT INTO sport (idsport, sport, activites) VALUES (11, 'Musculation', 'Fessiers');

INSERT INTO conseilsport (idconseilsport, idsport, rythmeconseil, detail) VALUES (1, 1, 15, 'Un programme de corde à sauter suivi 3 fois par semaine à raison de 30 minutes par séance, s''accompagne d''une augmentation significative de l''endurance musculaire');
INSERT INTO conseilsport (idconseilsport, idsport, rythmeconseil, detail) VALUES (2, 3, 30, 'Le vélo elliptique améliore l''endurance cardio-vasculaire et ceux de la méthode de musculation au poids de corps pour avoir le ventre plat et des fessiers galbés mais non massifs.');

INSERT INTO poids (idpoids, idregime, poids, datepoids) VALUES (1, 1, 57, '2017-03-01');

INSERT INTO sport_regime (idregime, idsport, datesport, rythmesport) VALUES (1, 8, '2017-03-01', 30);