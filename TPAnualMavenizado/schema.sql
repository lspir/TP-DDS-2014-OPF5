
    create table Amigos (
        id bigint not null auto_increment,
        direccion varchar(255),
        primary key (id)
    )

    create table Condiciones (
        id bigint not null auto_increment,
        condicion varchar(255),
        primary key (id)
    )

    create table Criticas (
        id bigint not null auto_increment,
        nota integer not null,
        texto varchar(255),
        partido_id bigint,
        id_jugador bigint,
        primary key (id)
    )

    create table Denegaciones (
        id bigint not null auto_increment,
        fecha tinyblob,
        motivo varchar(255),
        inscripcion_id bigint,
        id_partido bigint,
        primary key (id)
    )

    create table Formaciones (
        id bigint not null auto_increment,
        index_formacion integer,
        id_partido bigint,
        primary key (id)
    )

    create table Formaciones_equipoA (
        id_Formacion bigint not null,
        id_inscripcion bigint not null
    )

    create table Formaciones_equipoB (
        id_Formacion bigint not null,
        id_inscripcion bigint not null
    )

    create table Infracciones (
        id bigint not null auto_increment,
        momento varchar(255),
        numero integer not null,
        id_jugador bigint,
        primary key (id)
    )

    create table Inscripciones (
        id bigint not null auto_increment,
        nombreTipoDeInscripcion varchar(255),
        condicion_id bigint,
        jugador_id bigint,
        primary key (id)
    )

    create table Jugadores (
        id bigint not null auto_increment,
        edad integer not null,
        handicap integer not null,
        nombre varchar(255),
        primary key (id)
    )

    create table Jugadores_Amigos (
        Jugadores_id bigint not null,
        amigos_id bigint not null
    )

    create table Partidos (
        id bigint not null auto_increment,
        fecha tinyblob,
        horario tinyblob,
        lugar varchar(255),
        nombreEstado varchar(255),
        formacionConfirmada_id bigint,
        primary key (id)
    )

    create table inscripciones_por_partido (
        partido_id bigint not null,
        inscripcion_id bigint not null
    )

    create table posbilesJugadores_por_partido (
        partido_id bigint not null,
        inscripcion_id bigint not null
    )

    alter table Criticas 
        add index FK_jndv8xpyanb721ivfevylredn (partido_id), 
        add constraint FK_jndv8xpyanb721ivfevylredn 
        foreign key (partido_id) 
        references Partidos (id)

    alter table Criticas 
        add index FK_m29a348akqw30ej3nhjhqtjr4 (id_jugador), 
        add constraint FK_m29a348akqw30ej3nhjhqtjr4 
        foreign key (id_jugador) 
        references Jugadores (id)

    alter table Denegaciones 
        add index FK_hsrxci1fhrsega9d2t9or62pe (inscripcion_id), 
        add constraint FK_hsrxci1fhrsega9d2t9or62pe 
        foreign key (inscripcion_id) 
        references Inscripciones (id)

    alter table Denegaciones 
        add index FK_ps99bsjy4faas66j4etc61jl0 (id_partido), 
        add constraint FK_ps99bsjy4faas66j4etc61jl0 
        foreign key (id_partido) 
        references Partidos (id)

    alter table Formaciones 
        add index FK_3y4sq433wqcj7hqua22kkmpkx (id_partido), 
        add constraint FK_3y4sq433wqcj7hqua22kkmpkx 
        foreign key (id_partido) 
        references Partidos (id)

    alter table Formaciones_equipoA 
        add index FK_fxdjbp1aw8vjv0ks4cfr70gt6 (id_inscripcion), 
        add constraint FK_fxdjbp1aw8vjv0ks4cfr70gt6 
        foreign key (id_inscripcion) 
        references Inscripciones (id)

    alter table Formaciones_equipoA 
        add index FK_tbwybm8ayuxyc0v3cxluv1a4e (id_Formacion), 
        add constraint FK_tbwybm8ayuxyc0v3cxluv1a4e 
        foreign key (id_Formacion) 
        references Formaciones (id)

    alter table Formaciones_equipoB 
        add index FK_nf9q7di5siqykvbgvnwnt684l (id_inscripcion), 
        add constraint FK_nf9q7di5siqykvbgvnwnt684l 
        foreign key (id_inscripcion) 
        references Inscripciones (id)

    alter table Formaciones_equipoB 
        add index FK_2jmbv577i0k1xeu4yf45ovb91 (id_Formacion), 
        add constraint FK_2jmbv577i0k1xeu4yf45ovb91 
        foreign key (id_Formacion) 
        references Formaciones (id)

    alter table Infracciones 
        add index FK_ik0s795mo9829fkm6pljl9c9 (id_jugador), 
        add constraint FK_ik0s795mo9829fkm6pljl9c9 
        foreign key (id_jugador) 
        references Jugadores (id)

    alter table Inscripciones 
        add index FK_4327ydo9b9jyparu4byfdib7d (condicion_id), 
        add constraint FK_4327ydo9b9jyparu4byfdib7d 
        foreign key (condicion_id) 
        references Condiciones (id)

    alter table Inscripciones 
        add index FK_4rluraq5gtrdtd39xcdxlr2kr (jugador_id), 
        add constraint FK_4rluraq5gtrdtd39xcdxlr2kr 
        foreign key (jugador_id) 
        references Jugadores (id)

    alter table Jugadores_Amigos 
        add index FK_91o8bwu6s0x88ukw0yuq4uqs8 (amigos_id), 
        add constraint FK_91o8bwu6s0x88ukw0yuq4uqs8 
        foreign key (amigos_id) 
        references Amigos (id)

    alter table Jugadores_Amigos 
        add index FK_n6ek6xf0hdrpufvumar7dikmt (Jugadores_id), 
        add constraint FK_n6ek6xf0hdrpufvumar7dikmt 
        foreign key (Jugadores_id) 
        references Jugadores (id)

    alter table Partidos 
        add index FK_jwjtetdaxgjkkdmuryxgoaoo6 (formacionConfirmada_id), 
        add constraint FK_jwjtetdaxgjkkdmuryxgoaoo6 
        foreign key (formacionConfirmada_id) 
        references Formaciones (id)

    alter table inscripciones_por_partido 
        add index FK_6ogfxtxdn7s4x1tmbik5hds1x (inscripcion_id), 
        add constraint FK_6ogfxtxdn7s4x1tmbik5hds1x 
        foreign key (inscripcion_id) 
        references Inscripciones (id)

    alter table inscripciones_por_partido 
        add index FK_cuq2yu9ihjhopyfcusl30goyn (partido_id), 
        add constraint FK_cuq2yu9ihjhopyfcusl30goyn 
        foreign key (partido_id) 
        references Partidos (id)

    alter table posbilesJugadores_por_partido 
        add index FK_m96p94j6u0y1lp2v1eiwlstbm (inscripcion_id), 
        add constraint FK_m96p94j6u0y1lp2v1eiwlstbm 
        foreign key (inscripcion_id) 
        references Inscripciones (id)

    alter table posbilesJugadores_por_partido 
        add index FK_ki1b9eyc4nfnadefgksfwgpb0 (partido_id), 
        add constraint FK_ki1b9eyc4nfnadefgksfwgpb0 
        foreign key (partido_id) 
        references Partidos (id)
