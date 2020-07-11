-- smer
INSERT INTO "smer"("id", "naziv", "oznaka")
VALUES (nextval('smer_seq'), 'Inzenjerstvo informacionih sistema', 'IIS');
INSERT INTO "smer"("id", "naziv", "oznaka")
VALUES (nextval('smer_seq'), 'Softversko inzenjerstvo', 'SI');
INSERT INTO "smer"("id", "naziv", "oznaka")
VALUES (nextval('smer_seq'), 'Racunarstvo i automatika', 'RA');
INSERT INTO "smer"("id", "naziv", "oznaka")
VALUES (nextval('smer_seq'), 'Inzenjerski menadzment', 'IM');
--za test
INSERT INTO "smer"("id", "naziv", "oznaka")
VALUES (-100, 'Inzenjerski menadzment', 'IMM');

-- projekat
INSERT INTO "projekat"("id", "naziv", "oznaka", "opis")
VALUES (nextval('projekat_seq'), 'Viseslojne aplikacije', 'RVA', 'Napraviti frontend i backend.');
INSERT INTO "projekat"("id", "naziv", "oznaka", "opis")
VALUES (nextval('projekat_seq'), 'Dizajnerski obrasci', 'DO', 'Implementirati singleton i observer.');
INSERT INTO "projekat"("id", "naziv", "oznaka", "opis")
VALUES (nextval('projekat_seq'), 'Baze podataka', 'SPB', 'Napraviti sve.');

INSERT INTO "projekat"("id", "naziv", "oznaka", "opis")
VALUES (-100, 'Baze gas', 'SPG', 'ide gas.');

--grupa

INSERT INTO "grupa"("id", "oznaka", "smer")
VALUES (nextval('grupa_seq'), 'e2', 1);
INSERT INTO "grupa"("id", "oznaka", "smer")
VALUES (nextval('grupa_seq'), 'b1', 2);
INSERT INTO "grupa"("id", "oznaka", "smer")
VALUES (nextval('grupa_seq'), 'c3', 3);
INSERT INTO "grupa"("id", "oznaka", "smer")
VALUES (nextval('grupa_seq'), 'f5', 4);
INSERT INTO "grupa"("id", "oznaka", "smer")
VALUES (nextval('grupa_seq'), 'f7', 4);

INSERT INTO "grupa"("id", "oznaka", "smer")
VALUES (-100, 'p4', -100);

--student

INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Petar', 'Petrovic', 'IT57-2017', 2, 2);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Milos', 'Petrovic', 'IT55-2017', 2, 3);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Jovana', 'Djuric', 'IT53-2017', 2, 2);

INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Jelena', 'Petrovic', 'IT32-2014', 2, 3);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Aljosa', 'Krizan', 'IT69-2017', 1, 2);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Pavle', 'Marinkovic', 'IT58-2017', 3, 1);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Sasa', 'Kovacevic', 'IT78-2017', 3, 2);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Sasa', 'Kovacevic', 'IT78-2017', 4, 3);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Nevena', 'Bozovic', 'GR22-2015', 5, 1);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Osman', 'Hadzic', 'RA23-2018', 5, 1);

INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (-100, 'Pavle', 'Mar', 'RA23-2018', 5, 1);