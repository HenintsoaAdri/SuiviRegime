
INSERT INTO utilisateur (idutilisateur, nom, prenom, datenaissance, sexe, identifiant, password, adresse, email) VALUES (1, 'Henintsoa', 'Adri', '1996-08-20', 'FEMININ', 'adri', 'adri', 'Lot VB 83 Ambatoroka', 'adri@hotmail.com');


INSERT INTO regime (idregime, idutilisateur, datedebut, datefin, poidsobjectif) VALUES (1, 1, '2017-03-01', '2017-06-01', 50);


INSERT INTO alimentation (idalimentation, idregime, repas, boisson, periode, datealimentation) VALUES (1, 1, 'Yaourt - Pain beurré', 'Jus de fruit', 1, '2017-03-01');
INSERT INTO alimentation (idalimentation, idregime, repas, boisson, periode, datealimentation) VALUES (2, 1, 'Riz - Porc aux légume', 'Jus de fruit', 2, '2017-03-01');
INSERT INTO alimentation (idalimentation, idregime, repas, boisson, periode, datealimentation) VALUES (3, 1, 'Pomme', 'Eau plate', 3, '2017-03-01');
INSERT INTO alimentation (idalimentation, idregime, repas, boisson, periode, datealimentation) VALUES (6, 1, 'Riz - Kitoza sauté aux légumes', 'Jus de fruit', 2, '2017-03-02');
INSERT INTO alimentation (idalimentation, idregime, repas, boisson, periode, datealimentation) VALUES (7, 1, 'Riz - Saucisse', 'Jus de fruit', 3, '2017-03-02');
INSERT INTO alimentation (idalimentation, idregime, repas, boisson, periode, datealimentation) VALUES (4, 1, 'Yaourt - Pain confiture', 'Jus de fruit', 1, '2017-03-02');


