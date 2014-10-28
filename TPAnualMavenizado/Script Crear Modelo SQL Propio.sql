USE MySql
;

SET FOREIGN_KEY_CHECKS=0;




CREATE TABLE Amigos
(
	id_Amigo INTEGER NOT NULL AUTO_INCREMENT,
	Direccion VARCHAR(50) NULL,
	PRIMARY KEY (id_Amigo)

) 
;


CREATE TABLE Amigos_Por_Jugador
(
	id_Jugador INTEGER NOT NULL,
	id_Amigo INTEGER NOT NULL,
	PRIMARY KEY (id_Jugador, id_Amigo),
	KEY (id_Amigo)

) 
;


CREATE TABLE Condiciones
(
	id_Condicion INTEGER NOT NULL AUTO_INCREMENT,
	Condicion VARCHAR(50) NULL,
	PRIMARY KEY (id_Condicion)

) 
;


CREATE TABLE Criticas
(
	id_Critica INTEGER NOT NULL AUTO_INCREMENT,
	Nota INTEGER NULL,
	Texto VARCHAR(50) NULL,
	Partido INTEGER NULL,
	PRIMARY KEY (id_Critica),
	KEY (Partido)

) 
;


CREATE TABLE Denegaciones
(
	id_Denegacion INTEGER NOT NULL AUTO_INCREMENT,
	Motivo VARCHAR(50) NULL,
	id_Inscripcion INTEGER NOT NULL,
	Lugar VARCHAR(50) NULL,
	PRIMARY KEY (id_Denegacion),
	KEY (id_Inscripcion)

) 
;


CREATE TABLE Formaciones
(
	id_Formacion INTEGER NOT NULL AUTO_INCREMENT,
	equipoA INTEGER NULL,
	equipoB INTEGER NULL,
	PRIMARY KEY (id_Formacion)

) 
;


CREATE TABLE Formaciones_Equipo_A_Por_Inscripcion
(
	id_Formacion INTEGER NOT NULL,
	id_Inscripcion INTEGER NOT NULL,
	PRIMARY KEY (id_Formacion, id_Inscripcion),
	KEY (id_Inscripcion)

) 
;


CREATE TABLE Formaciones_EquipoB_Por_Inscripcion
(
	id_Formacion INTEGER NOT NULL,
	id_Inscripicion INTEGER NOT NULL,
	PRIMARY KEY (id_Formacion, id_Inscripicion),
	KEY (id_Inscripicion)

) 
;


CREATE TABLE Infracciones
(
	id_Infraccion INTEGER NOT NULL AUTO_INCREMENT,
	Momento VARCHAR(50) NULL,
	Numero INTEGER NULL,
	PRIMARY KEY (id_Infraccion)

) 
;


CREATE TABLE Inscripciones
(
	id_Inscripcion INTEGER NOT NULL AUTO_INCREMENT,
	Jugador INTEGER NOT NULL,
	Tipo_Inscripcion VARCHAR(50) NULL,
	Condicion Condicional INTEGER NULL,
	PRIMARY KEY (id_Inscripcion),
	KEY (Condicion Condicional),
	KEY (Jugador)

) 
;


CREATE TABLE Inscripciones_Por_Partido
(
	id_Inscripcion INTEGER NOT NULL,
	id_Partido INTEGER NOT NULL,
	PRIMARY KEY (id_Inscripcion, id_Partido),
	KEY (id_Inscripcion)

) 
;


CREATE TABLE Jugadores
(
	id_jugador INTEGER NOT NULL AUTO_INCREMENT,
	Nombre VARCHAR(50) NULL,
	Edad INTEGER NULL,
	Handicap INTEGER NULL,
	Amigos INTEGER NULL,
	Infracciones INTEGER NULL,
	Criticas INTEGER NULL,
	PRIMARY KEY (id_jugador),
	KEY (Infracciones),
	KEY (Criticas)

) 
;


CREATE TABLE Partidos
(
	id_Partido INTEGER NOT NULL AUTO_INCREMENT,
	Fecha DATE NOT NULL,
	Horario TIME(0) NOT NULL,
	Estado VARCHAR(50) NULL,
	Posibles_Jugadores INTEGER NULL,
	Inscripciones INTEGER NULL,
	Denegaciones INTEGER NULL,
	Formaciones_Tentativas INTEGER NULL,
	Formacion_Confirmada INTEGER NULL,
	PRIMARY KEY (id_Partido),
	KEY (Denegaciones),
	KEY (Denegaciones),
	KEY (Formaciones_Tentativas),
	KEY (Formacion_Confirmada)

) 
;


CREATE TABLE Posibles_Jugadores_Por_Partido
(
	id_Partido INTEGER NOT NULL,
	id_Inscripcion INTEGER NOT NULL,
	PRIMARY KEY (id_Partido, id_Inscripcion),
	KEY (id_Inscripcion)

) 
;



SET FOREIGN_KEY_CHECKS=1;


ALTER TABLE Amigos_Por_Jugador ADD CONSTRAINT FK_Amigos_Por_Jugador_Amigos 
	FOREIGN KEY (id_Amigo) REFERENCES Amigos (id_Amigo)
;

ALTER TABLE Criticas ADD CONSTRAINT FK_Criticas_Partidos 
	FOREIGN KEY (Partido) REFERENCES Partidos (id_Partido)
;

ALTER TABLE Denegaciones ADD CONSTRAINT FK_Denegaciones_Inscripciones 
	FOREIGN KEY (id_Inscripcion) REFERENCES Inscripciones (id_Inscripcion)
;

ALTER TABLE Formaciones_Equipo_A_Por_Inscripcion ADD CONSTRAINT FK_Formaciones_Equipo_A_Por_Inscripcion_Inscripciones 
	FOREIGN KEY (id_Inscripcion) REFERENCES Inscripciones (id_Inscripcion)
;

ALTER TABLE Formaciones_EquipoB_Por_Inscripcion ADD CONSTRAINT FK_Formaciones_EquipoB_Por_Inscripcion_Inscripciones 
	FOREIGN KEY (id_Inscripicion) REFERENCES Inscripciones (id_Inscripcion)
;

ALTER TABLE Inscripciones ADD CONSTRAINT FK_Inscripciones_Condiciones 
	FOREIGN KEY (Condicion Condicional) REFERENCES Condiciones (id_Condicion)
;

ALTER TABLE Inscripciones ADD CONSTRAINT FK_Inscripciones_Jugadores 
	FOREIGN KEY (Jugador) REFERENCES Jugadores (id_jugador)
;

ALTER TABLE Inscripciones_Por_Partido ADD CONSTRAINT FK_Inscripciones_Por_Partido_Inscripciones 
	FOREIGN KEY (id_Inscripcion) REFERENCES Inscripciones (id_Inscripcion)
;

ALTER TABLE Jugadores ADD CONSTRAINT FK_Jugadores_Infracciones 
	FOREIGN KEY (Infracciones) REFERENCES Infracciones (id_Infraccion)
;

ALTER TABLE Jugadores ADD CONSTRAINT FK_Jugadores_Criticas 
	FOREIGN KEY (Criticas) REFERENCES Criticas (id_Critica)
;

ALTER TABLE Partidos ADD CONSTRAINT FK_Partidos_Denegaciones 
	FOREIGN KEY (Denegaciones) REFERENCES Denegaciones (id_Denegacion)
;

ALTER TABLE Partidos ADD CONSTRAINT FK_Partidos_Posibles_Jugadores_Por_Partido 
	FOREIGN KEY (id_Partido) REFERENCES Posibles_Jugadores_Por_Partido (id_Partido, id_Inscripcion)
;

ALTER TABLE Partidos ADD CONSTRAINT FK_Partidos_Formaciones 
	FOREIGN KEY (Formaciones_Tentativas) REFERENCES Formaciones (id_Formacion)
;

ALTER TABLE Partidos ADD CONSTRAINT FK_Partidos_Formacion_Confirmada 
	FOREIGN KEY (Formacion_Confirmada) REFERENCES Formaciones (id_Formacion)
;

ALTER TABLE Posibles_Jugadores_Por_Partido ADD CONSTRAINT FK_Posibles_Jugadores_Por_Partido_Inscripciones 
	FOREIGN KEY (id_Inscripcion) REFERENCES Inscripciones (id_Inscripcion)
;
