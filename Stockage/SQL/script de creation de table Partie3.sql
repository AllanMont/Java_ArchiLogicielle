DROP TABLE Catalogues;
DROP SEQUENCE seqProdIdCatalogue;
DROP PROCEDURE nouveauCatalogue;

DROP SEQUENCE seqProdId;
DROP TABLE Produits;
DROP PROCEDURE nouveauProduit;

CREATE TABLE Catalogues (
    idCatalogue INTEGER,
    nomCatalogue VARCHAR(50),
    CONSTRAINT pk_catalogue primary key (idCatalogue)
);


CREATE SEQUENCE seqProdIdCatalogue
START WITH 1 INCREMENT BY 1;


CREATE OR REPLACE PROCEDURE nouveauCatalogue (p_nom IN Catalogues.nomCatalogue%TYPE) IS
BEGIN
    INSERT INTO Catalogues (idCatalogue, nomCatalogue) VALUES (seqProdIdCatalogue.nextval,p_nom);
END;



CREATE TABLE Produits (
    idProduit INTEGER,
    nomProduit VARCHAR(50),
    PrixHT FLOAT,
    quantite INTEGER,
    idCatalogue INTEGER,
    CONSTRAINT pk_produit PRIMARY KEY (idProduit),
    CONSTRAINT fk_produit_catalogue FOREIGN KEY (idCatalogue) REFERENCES Catalogues(idCatalogue)
);


CREATE SEQUENCE seqProdId
START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE PROCEDURE nouveauProduit (p_nom IN Produits.nomProduit%TYPE, p_prixHT IN Produits.PrixHT%TYPE, p_quantite IN Produits.quantite%TYPE, p_idCatalogue IN Produits.idCatalogue%TYPE) IS
BEGIN
    INSERT INTO Produits (idProduit, nomProduit, PrixHT, quantite, idCatalogue)
    VALUES (seqProdId.nextval, p_nom, p_prixHT, p_quantite, p_idCatalogue);
END;

-- SELECT --
SELECT * FROM Catalogues;
SELECT * FROM Produits;


-- FAUSSE DONNE --
CALL nouveauCatalogue('Epice');
CALL nouveauCatalogue('Viande');
CALL nouveauCatalogue('Bonbon');

CALL nouveauProduit('Aromate',5,10,1);
CALL nouveauProduit('Curry Jaune',5.99,5,1);
CALL nouveauProduit('Sel',1.990,15,1);
CALL nouveauProduit('Steack',20,60,2);
CALL nouveauProduit('Poulet',15.99,50,2);
CALL nouveauProduit('Tic Tac',5.99,5,3);


SELECT COUNT(*) AS nombre_de_produits
FROM Produits
WHERE idCatalogue = 1;

SELECT nomCatalogue FROM Catalogues;