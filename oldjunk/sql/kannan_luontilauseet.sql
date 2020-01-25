CREATE TABLE Asiakas (
id int NOT NULL,
nimi varchar(100) NOT NULL,
kanta_asiakas boolean NOT NULL,
tilinumero varchar(32) NOT NULL,
puhelinnumero varchar(16),
PRIMARY KEY (id));

CREATE TABLE Kohde (
id int NOT NULL,
osoite varchar(255) NOT NULL,
asiakas_id int NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (asiakas_id) REFERENCES Asiakas);

CREATE TABLE Kaytetyttarvikkeet (
tyo_id int NOT NULL,
tarvike_id int NOT NULL,
FOREIGN KEY (tyo_id) REFERENCES Tyo,
FOREIGN KEY (tarvike_id) REFERENCES Tarvike);

CREATE TABLE Lasku (
id int NOT NULL,
hinta float(2) NOT NULL,
erapaiva date NOT NULL,
maksettu boolean NOT NULL,
alkper int,
asiakas_id int NOT NULL,
tyyppi_id int NOT NULL,
tyo_id int NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (asiakas_id) REFERENCES Asiakas,
FOREIGN KEY (tyyppi_id) REFERENCES Laskutyyppi,
FOREIGN KEY (tyo_id) REFERENCES Tyo);

CREATE TABLE Laskutyyppi (
id int NOT NULL,
nimi varchar(32) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE Tyo (
id int NOT NULL,
kesto int NOT NULL,
pvm date NOT NULL,
tyotyyppi int,
kohde_id int NOT NULL,
vero_id int NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (tyotyyppi) REFERENCES Tyotyyppi,
FOREIGN KEY (kohde_id) REFERENCES Kohde,
FOREIGN KEY (vero_id) REFERENCES Veroprosentti);

CREATE TABLE Tyotyyppi (
id int NOT NULL,
nimi varchar(32),
tuntihinta float(2) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE Tarvike (
id int NOT NULL,
nimi varchar(100) NOT NULL,
hinta float(2) NOT NULL,
tuotekoodi varchar(100) NOT NULL,
vero int NOT NULL,
PRIMARY KEY (id),
UNIQUE (tuotekoodi),
FOREIGN KEY (vero) REFERENCES Veroprosentti);

CREATE TABLE Veroprosentti (
id int NOT NULL,
nimi varchar(32),
prosentti float(2) NOT NULL,
PRIMARY KEY (id));