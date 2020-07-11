CREATE TABLE smer(
	id integer not null,
    naziv varchar(100) not null,
    oznaka varchar(50)
);

CREATE TABLE projekat(
	id integer not null,
    naziv varchar(100) not null,
    oznaka varchar(10) not null,
    opis varchar(500)
);

CREATE TABLE grupa(
    id integer not null,
    oznaka varchar(10) not null,
    smer integer not null
);

CREATE TABLE student(
	id integer not null,
    ime varchar(50) not null,
    prezime varchar(50) not null,
    broj_indeksa varchar(20) not null,
    grupa integer not null,
    projekat integer not null
);

ALTER TABLE smer ADD CONSTRAINT
PK_Smer PRIMARY KEY(id);
ALTER TABLE projekat ADD CONSTRAINT
PK_Projekat PRIMARY KEY(id);
ALTER TABLE grupa ADD CONSTRAINT
PK_Grupa PRIMARY KEY(id);
ALTER TABLE student ADD CONSTRAINT
PK_Student PRIMARY KEY(id);

ALTER TABLE grupa ADD CONSTRAINT
FK_Grupa_Smer FOREIGN KEY(smer) REFERENCES smer(id);
ALTER TABLE student ADD CONSTRAINT
FK_Student_Grupa FOREIGN KEY(grupa) REFERENCES grupa(id);
ALTER TABLE student ADD CONSTRAINT
FK_Student_Projekat FOREIGN KEY(projekat) REFERENCES projekat(id);

CREATE INDEX IDXFK_Grupa_Smer
ON grupa(smer);
CREATE INDEX IDXFK_Student_Grupa
ON student(grupa);
CREATE INDEX IDXFK_Student_Projekat
ON student(projekat);

CREATE SEQUENCE smer_seq
INCREMENT 1;
CREATE SEQUENCE grupa_seq
INCREMENT 1;
CREATE SEQUENCE projekat_seq
INCREMENT 1;
CREATE SEQUENCE student_seq
INCREMENT 1;